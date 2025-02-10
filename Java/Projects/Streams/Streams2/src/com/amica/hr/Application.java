package com.amica.hr;

import java.util.stream.Stream;

/**
 * Application that drives some queries to the HR "database."
 *
 * @author Will Provost
 */
public class Application {

	/**
	 * Helper method that produces a heading and then an indented list,
	 * based on the given stream of values.
	 */
	public static void report(String heading, Stream<?> values) {
		System.out.println();
		System.out.println(heading + ":");
		values.map(v -> "  " + v).forEach(System.out::println);
	}

	/**
	 * Instantiates the in-memory database of jobs, departments, and employees,
	 * and makes a few queries. Then uses a stream of employees actually
	 * to modify the data where it lives.
	 */
	public static void main(String[] args) {
		Data DB = new Data();

		// Employees who are Testers
		report("Employees who are testers", DB.employees.values().stream()
				.filter(e -> e.getJob().getName().equals("Tester")));

		// Employees who make in a certain salary range
		report("Employees who earn between $30,000 and $50,000",
			DB.employees.values().stream()
				.filter(e -> e.getSalary() >= 30000)
				.filter(e -> e.getSalary() <= 50000));

		// All departments, with total payroll for each
		report("Payroll by department", DB.departments.values().stream()
			.map(d -> String.format("%-20s%,10d", d.getName(),
				d.getEmployees().stream().mapToInt(Employee::getSalary).sum())));

		// Total payroll
		System.out.format("%nTotal payroll: %,d%n",
			DB.employees.values().stream()
				.mapToInt(Employee::getSalary)
				.sum());

		// Employees who are out of salary range for their job
		report("Employees with salaries out of range for their jobs",
			DB.employees.values().stream()
				.filter(e -> e.getSalary() < e.getJob().getMinimumSalary() ||
						e.getSalary() > e.getJob().getMaximumSalary()));

		// Impact on payroll if we normalize salaries
		System.out.format("%nPayroll impact of normalizing salaries: %,d%n",
			DB.employees.values().stream()
				.mapToInt(e -> e.getSalary() < e.getJob().getMinimumSalary()
						? e.getJob().getMinimumSalary() - e.getSalary()
						: (e.getSalary() > e.getJob().getMaximumSalary()
								? e.getJob().getMaximumSalary() - e.getSalary()
								: 0))
				.sum());

		// Now let's actually normalize salaries, and confirm payroll
		System.out.format("%nTotal payroll after normalizing: %,d%n",
				DB.employees.values().stream()
					.peek(e -> {
							if (e.getSalary() < e.getJob().getMinimumSalary()) {
								e.setSalary(e.getJob().getMinimumSalary());
						}
							if (e.getSalary() > e.getJob().getMaximumSalary()) {
								e.setSalary(e.getJob().getMaximumSalary());
						}
					})
					.mapToInt(Employee::getSalary)
					.sum());
		
		// Highest-paid employee in each department
		report("Highest-paid per department", DB.departments.values().stream()
				.map(d -> d.getEmployees().stream()
						.max((x,y) -> Integer.compare(y.getSalary(), x.getSalary()))
						.get())
				.map(e -> String.format("$%6d %-20s %s", e.getSalary(), 
						e.getDepartment().getName(), e.toString())));
		System.out.println();
		
		// Form a softball team from the first 10 members of each department,
		// skipping departments that are too small completely, and ignoring
		// the remaining members of departments with 11 or more employees
		System.out.println("As many single-department softball teams as we can make:");
		DB.departments.values().stream().filter(d -> d.getEmployees().size() > 9)
				.forEach(d -> report(d.getName(), d.getEmployees().stream().limit(10)));
	}
}

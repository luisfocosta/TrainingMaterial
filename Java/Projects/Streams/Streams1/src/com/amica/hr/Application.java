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

		//TODO Employees who make in a certain salary range

		//TODO All departments, with total payroll for each

		//TODO Total payroll

		//TODO Employees who are out of salary range for their job

		//TODO Impact on payroll if we normalize salaries

		//TODO Now let's actually normalize salaries, and confirm payroll
		
		//TODO Highest-paid employee in each department
		
		//TODO Form a softball team from the first 10 members of each department,
		// skipping departments that are too small completely, and ignoring
		// the remaining members of departments with 11 or more employees
	}
}

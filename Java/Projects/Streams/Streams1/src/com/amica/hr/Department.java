package com.amica.hr;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Represents a department in a human-resources system.
 *
 * @author Will Provost
 */
public class Department {
	private String name;
	private List<Employee> employees = new ArrayList<>();

	/**
	 * 
	 */
	public Department(String name) {
		this.name = name;
	}

	/**
	 * Get the name of the department.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set a name for this Department.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Return employees in this department.
	 */
	public Collection<Employee> getEmployees() {
		return this.employees;
	}

	/**
	 * Set employees in this department.
	 */
	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	/**
	 * Returns the department name.
	 */
	@Override
	public String toString() {
		return this.name;
	}
}

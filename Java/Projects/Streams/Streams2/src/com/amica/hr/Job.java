package com.amica.hr;

/**
 * Represents a job in a human-resources system.
 *
 * @author Will Provost
 */
public class Job {
	private String name;
	private int minimumSalary;
	private int maximumSalary;

	public Job(String name, int minimumSalary, int maximumSalary) {
		this.name = name;
		this.minimumSalary = minimumSalary;
		this.maximumSalary = maximumSalary;
	}

	/**
	 * Return a name for this job.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Set a name for this job.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Return a minimum salary for this job.
	 */
	public int getMinimumSalary() {
		return this.minimumSalary;
	}

	/**
	 * Set a minimum salary for this job.
	 */
	public void setMinimumSalary(int minimumSalary) {
		this.minimumSalary = minimumSalary;
	}

	/**
	 * Return a maximum salary for this job.
	 */
	public int getMaximumSalary() {
		return this.maximumSalary;
	}

	/**
	 * Set a maximum salary for this job. Maximum Salary.
	 */
	public void setMaximumSalary(int maximumSalary) {
		this.maximumSalary = maximumSalary;
	}

	/**
	 * Returns the name of this job.
	 */
	@Override
	public String toString() {
		return this.name;
	}
}

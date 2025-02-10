package com.amica.hr;

/**
 * Represents an employee in a human-resources system.
 *
 * @author Will Provost
 */
public class Employee
{
    private String firstName;
    private String lastName;
    private Department department;
    private Job job;
    private int salary;

    public Employee(String firstName, String lastName, Department department, 
    		Job job, int salary)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.job = job;
        this.salary = salary;
    }

    /**
     * Return a first Name for this employee.
     */
    public String getFirstName()
    {
        return this.firstName;
    }

    /**
     * Set a first name for this employee.
     */
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    /**
     * Return a last name for this employee.
     */
    public String getLastName()
    {
        return this.lastName;
    }

    /**
     * Set a last name for this employee.
     */
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    /**
     * Return a salary for this employee.
     */
    public int getSalary()
    {
        return this.salary;
    }

    /**
     * Set a salary for this employee.
     */
    public void setSalary(int salary)
    {
        this.salary = salary;
    }

    /**
     * Return a department of this employee.
     */
    public Department getDepartment()
    {
        return this.department;
    }

    /**
     * Set a department of this employee.
     */
    public void setDepartment(Department department)
    {
        this.department = department;
    }

    /**
     * Return a job for this employee.
     */
    public Job getJob()
    {
        return this.job;
    }

    /**
     * Set a job for this employee.
     */
    public void setJob(Job job)
    {
        this.job = job;
    }
    
    /**
     * Returns the employee's full name.
     */
    @Override
    public String toString()
    {
        return firstName + " " + lastName;
    }
}

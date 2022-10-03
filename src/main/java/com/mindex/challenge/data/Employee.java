package com.mindex.challenge.data;

import java.util.List;

public class Employee {
    private String employeeId;
    private String firstName;
    private String lastName;
    private String position;
    private String department;
    private List<Employee> directReports;

    public Employee() {
    }

    /**
     * Get the employeeId of an Employee
     * @return String containing the employeeId of the Employee
     */
    public String getEmployeeId() {
        return employeeId;
    }

    /**
     * Set the employeeId for an Employee
     * @param employeeId String employeeId to set
     */
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    /**
     * Get the first name of the Employee
     * @return String containing the first name of an Employee
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set the first name of an employee
     * @param firstName String first name to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Get the last name of an employee
     * @return String last name of the employee
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set the last name of an employee
     * @param lastName The last name to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Get the position of an employee
     * @return The position of the employee
     */
    public String getPosition() {
        return position;
    }

    /**
     * Set the position of an employee
     * @param position The position to set
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * Get the department of an employee
     * @return The department of the employee
     */
    public String getDepartment() {
        return department;
    }

    /**
     * Set the department of an employee
     * @param department String department to set
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * Get the employeeId's of this Employees subordinates
     * @return A List containing the employeeId's of the Employee's subordinates
     */
    public List<Employee> getDirectReports() {
        return directReports;
    }

    /**
     * Set the directReports of this Employee
     * @param directReports List containing employeeIds of this Employees subordinate's
     */
    public void setDirectReports(List<Employee> directReports) {
        this.directReports = directReports;
    }
}

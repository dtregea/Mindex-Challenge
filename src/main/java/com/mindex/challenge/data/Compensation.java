package com.mindex.challenge.data;

import java.util.Date;

public class Compensation {

    private String compensationId;
    private Employee employee;
    private int salary;
    private Date effectiveDate;

    public Compensation(){ }

    /**
     * Get the compensationId of the Compensation
     * @return String compensationId of this Compensation
     */
    public String getCompensationId() {
        return compensationId;
    }

    /**
     * Set the Id of the Compensation
     * @param compensationId New Id value of Compensation
     */
    public void setCompensationId(String compensationId) {
        this.compensationId = compensationId;
    }

    /**
     * Get the Compensation's Employee
     * @return Employee belonging to this Compensation
     */
    public Employee getEmployee() {
        return employee;
    }

    /**
     * Set the Compensation's Employee
     * @param employee Compensation's new Employee
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    /**
     * Get the Id of the Compensation's Employee
     * @return Id of Employee belonging to this Compensation
     */
    public String getEmployeeId(){
        if (employee != null) {
            return employee.getEmployeeId();
        }
        return null;
    }

    /**
     * Get the salary of the Compensation
     * @return Salary of this Compensation
     */
    public int getSalary() {
        return salary;
    }

    /**
     * Set the Compensation's new salary
     * @param salary Compensation's new salary
     */
    public void setSalary(int salary) {
        this.salary = salary;
    }

    /**
     * Get the effectiveDate of the Compensation
     * @return effectiveDate of this Compensation
     */
    public Date getEffectiveDate() {
        return effectiveDate;
    }

    /**
     * Set the Compensation's new effectiveDate
     * @param effectiveDate Compensation's new effectiveDate
     */
    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }
}

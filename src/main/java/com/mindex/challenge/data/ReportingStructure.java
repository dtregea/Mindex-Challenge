package com.mindex.challenge.data;

public class ReportingStructure {
    private Employee employee;
    private int numberOfReports;

    public ReportingStructure(){}

    /**
     * Get the Employee of the ReportingStructure
     * @return Employee of the ReportingStructure
     */
    public Employee getEmployee() {
        return employee;
    }

    /**
     * Set the Employee of the ReportingStructure
     * @param employee Employee to set
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    /**
     * Get the Number of Reports of a ReportingStructure
     * @return Number containing the number of subordinates for the Employee
     */
    public int getNumberOfReports() {
        return numberOfReports;
    }

    /**
     * Set the Number of Reports of a ReportingStructure
     * @param numberOfReports Number of subordinates to set
     */
    public void setNumberOfReports(int numberOfReports) {
        this.numberOfReports = numberOfReports;
    }
}

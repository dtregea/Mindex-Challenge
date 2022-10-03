package com.mindex.challenge.controller;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {
    private static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    /**
     * Create a Employee on POST
     * @param employee Employee in request body
     * @return Response with created Employee
     */
    @PostMapping("/employee")
    public Employee create(@RequestBody Employee employee) {
        LOG.debug("Received employee create request for [{}]", employee);
        return employeeService.create(employee);
    }

    /**
     * Retrieve a Employee on GET
     * @param id The employeeId of the Employee to retrieve
     * @return  Response with the Employee with the specified employeeId
     */
    @GetMapping("/employee/{id}")
    public Employee read(@PathVariable String id) {
        LOG.debug("Received employee get request for id [{}]", id);
        return employeeService.read(id);
    }

    /**
     * Update a Employee on PUT
     * @param id The employeeId of the Employee to update
     * @param employee The Employee object to replace with
     * @return Response with the Employee containing updated fields
     */
    @PutMapping("/employee/{id}")
    public Employee update(@PathVariable String id, @RequestBody Employee employee) {
        LOG.debug("Received employee update request for id [{}] and employee [{}]", id, employee);
        employee.setEmployeeId(id);
        return employeeService.update(employee);
    }

    /**
     * Generate a ReportingStructure for a specified Employee
     * @param id The employeeId of the Employee to generate a ReportingStructure
     * @return  ReportingStructure containing the Employee and count of subordinates
     */
    @GetMapping("/employee/{id}/reportingStructure")
    public ReportingStructure getReportingStructure(@PathVariable String id) {
        LOG.debug("Received employee create request for id [{}]", id);
        return employeeService.getReportingStructure(id);
    }
}

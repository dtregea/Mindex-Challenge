package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * Create an Employee
     * @param employee Employee to create
     * @return The saved Employee
     */
    @Override
    public Employee create(Employee employee) {
        LOG.debug("Creating employee [{}]", employee);
        employee.setEmployeeId(UUID.randomUUID().toString());
        employeeRepository.insert(employee);
        return employee;
    }

    /**
     * Get an Employee by employeeId
     * @param id employeeId of the Employee to retrieve
     * @throws ResponseStatusException Indicates that there is no employee of the provided Id
     * @return Employee document with the specified employeeId
     */
    @Override
    public Employee read(String id) {
        LOG.debug("Reading employee with id [{}]", id);
        return employeeRepository.findByEmployeeId(id);
    }

    /**
     * Update an Employee
     * @param employee Employee object to update
     * @return The saved Employee
     */
    @Override
    public Employee update(Employee employee) {
        LOG.debug("Updating employee [{}]", employee);
        return employeeRepository.save(employee);
    }

    /**
     * Create a Reporting Structure containing number of all Employee descendents
     * @param id employeeId of Employee to count descendents
     * @return Reporting Structure with count of descendents of specified Employee
     */
    @Override
    public ReportingStructure getReportingStructure(String id) {
        Employee rootEmployee = employeeRepository.findByEmployeeId(id);
        HashSet<String> uniqueEmployees = new HashSet<>();
        ReportingStructure result = new ReportingStructure();
        result.setEmployee(rootEmployee);
        List<String> toEvaluate = new LinkedList<>();
        toEvaluate.add(id);
        int numberOfReports = 0;

        // Count all subordinates
        while (!toEvaluate.isEmpty()) {
            Employee currentEmployee = employeeRepository.findByEmployeeId(toEvaluate.remove(0));
            List<Employee> subordinates = currentEmployee.getDirectReports();

            if (subordinates == null) {
                continue;
            }
            for (Employee subordinate: subordinates) {
                if(!uniqueEmployees.contains(subordinate.getEmployeeId())) {
                    numberOfReports++;
                    uniqueEmployees.add(subordinate.getEmployeeId());
                    toEvaluate.add(subordinate.getEmployeeId());
                }
            }
        }
        result.setNumberOfReports(numberOfReports);
        return result;
    }
}

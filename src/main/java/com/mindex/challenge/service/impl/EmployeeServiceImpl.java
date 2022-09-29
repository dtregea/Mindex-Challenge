package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee create(Employee employee) {
        LOG.debug("Creating employee [{}]", employee);

        employee.setEmployeeId(UUID.randomUUID().toString());
        employeeRepository.insert(employee);

        return employee;
    }

    @Override
    public Employee read(String id) {
        LOG.debug("Creating employee with id [{}]", id);

        Employee employee = employeeRepository.findByEmployeeId(id);

        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }

        return employee;
    }

    @Override
    public Employee update(Employee employee) {
        LOG.debug("Updating employee [{}]", employee);

        return employeeRepository.save(employee);
    }

    @Override
    public ReportingStructure getReportingStructure(String id) {
        Employee rootEmployee = employeeRepository.findByEmployeeId(id);
        HashSet<String> uniqueEmployees = new HashSet<>();
        ReportingStructure result = new ReportingStructure();
        result.setEmployee(rootEmployee);
        List<String> toEvaluate = new LinkedList<>();
        toEvaluate.add(id);
        int numberOfReports = 0;

        // Count all descendants
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

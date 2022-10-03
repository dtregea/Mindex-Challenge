package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.CompensationService;
import com.mindex.challenge.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class CompensationServiceImpl implements CompensationService{
    private static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private CompensationRepository compensationRepository;
    @Autowired
    private EmployeeService employeeService;

    /**
     * Insert a Compensation document
     * @param compensation Compensation document to insert
     * @throws ResponseStatusException Indicates that there is no employee of the provided Id
     * @return Inserted Compensation
     */
    @Override
    public Compensation create(Compensation compensation) {
        LOG.debug("Creating compensation [{}]", compensation);
        Employee employee = employeeService.read(compensation.getEmployeeId());
        if (employee == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "No Employee of Id: " + compensation.getEmployeeId()
            );
        }
        compensation.setCompensationId(UUID.randomUUID().toString());
        return compensationRepository.insert(compensation);
    }

    /**
     * Retrieve a Compensation document
     * @param id compensationId of the Compensation to retrieve
     * @throws ResponseStatusException Indicates that there is no Compensation of the provided Id
     * @return Compensation matching the specified Id
     */
    @Override
    public Compensation read(String id) {
        LOG.debug("Reading compensation with id [{}]", id);
        return compensationRepository.findByCompensationId(id);
    }
}

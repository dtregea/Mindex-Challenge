package com.mindex.challenge.controller;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class CompensationController {
    private static final Logger LOG = LoggerFactory.getLogger(CompensationController.class);

    @Autowired
    private CompensationService compensationService;

    /**
     * Create a compensation on POST
     * @param compensation Compensation in request body
     * @throws ResponseStatusException Indicates an invalid employeeId
     * @return Response with created Compensation
     */
    @PostMapping("/compensation")
    public Compensation create(@RequestBody Compensation compensation) {
        LOG.debug("Received compensation create request for [{}]", compensation);
        return compensationService.create(compensation);
    }

    /**
     * Retrieve a compensation on GET
     * @param id The compensationId of the compensation to retrieve
     * @throws ResponseStatusException Indicates an invalid compensationId
     * @return  Response with the Compensation with the specified compensationId
     */
    @GetMapping("/compensation/{id}")
    public Compensation read(@PathVariable String id) {
        LOG.debug("Received compensation get request for id [{}]", id);
        Compensation compensation = compensationService.read(id);
        if (compensation == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "No Compensation of Id: " + id
            );
        }
        return compensation;
    }
}

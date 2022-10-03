package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.EmployeeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Date;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompensationServiceImplTest {

    private String compensationUrl;
    private String compensationIdUrl;

    @Autowired
    private EmployeeService employeeService;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
        compensationUrl = "http://localhost:" + port + "/compensation";
        compensationIdUrl = "http://localhost:" + port + "/compensation/{id}";
    }

    /**
     * Verify GET and POST routes successfully create and retrieve a compensation
     */
    @Test
    public void testCreateRead() {
        Compensation testCompensation = new Compensation();
        testCompensation.setEffectiveDate(new Date());
        testCompensation.setSalary(1000);
        testCompensation.setEmployee(employeeService.create(new Employee()));

        // Create checks
        Compensation createdCompensation = restTemplate.postForEntity(compensationUrl, testCompensation, Compensation.class).getBody();

        assertNotNull(createdCompensation.getCompensationId());
        assertCompensationEquivalence(testCompensation, createdCompensation);

        // Read checks
        Compensation readCompensation = restTemplate.getForEntity(compensationIdUrl, Compensation.class, createdCompensation.getCompensationId()).getBody();
        assertEquals(createdCompensation.getCompensationId(), readCompensation.getCompensationId());
        assertCompensationEquivalence(createdCompensation, readCompensation);
    }

    /**
     * Verify that on GET a Compensation won't be created with a non-existent Employee
     */
    @Test
    public void testCreateWithInvalidEmployee() {
        Employee invalidEmployee = new Employee();
        invalidEmployee.setEmployeeId("Invalid Id");
        Compensation testCompensation = new Compensation();
        testCompensation.setEffectiveDate(new Date());
        testCompensation.setSalary(1000);
        testCompensation.setEmployee(invalidEmployee);

        // Create checks
        Compensation createdCompensation = restTemplate.postForEntity(compensationUrl, testCompensation, Compensation.class).getBody();
        assertNull(createdCompensation.getCompensationId());
    }

    /**
     * Verify that GET no Compensation's are returned on an invalid specified Id
     */
    @Test
    public void testReadWithInvalidCompensationId() {
        Compensation testCompensation = new Compensation();
        testCompensation.setEffectiveDate(new Date());
        testCompensation.setSalary(1000);
        testCompensation.setEmployee(employeeService.create(new Employee()));

        // Create checks
        Compensation createdCompensation = restTemplate.postForEntity(compensationUrl, testCompensation, Compensation.class).getBody();
        assertNotNull(createdCompensation.getCompensationId());

        // Read checks
        Compensation readCompensation = restTemplate.getForEntity(compensationIdUrl, Compensation.class, "Invalid Id").getBody();
        assertNull(readCompensation.getCompensationId());
    }

    private static void assertCompensationEquivalence(Compensation expected, Compensation actual) {
        assertEquals(expected.getSalary(), actual.getSalary());
        assertEquals(expected.getEffectiveDate(), actual.getEffectiveDate());
        assertEquals(expected.getEmployeeId(), actual.getEmployeeId());
    }
}

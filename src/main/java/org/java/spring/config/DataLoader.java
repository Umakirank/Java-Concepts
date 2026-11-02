package org.java.spring.config;

import org.java.spring.dto.EmployeeRequest;
import org.java.spring.service.EmployeeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/** Loads sample data at startup so the REST API has something to return. */
@Component
@Order(2)
public class DataLoader implements CommandLineRunner {

    private final EmployeeService service;

    public DataLoader(EmployeeService service) { this.service = service; }

    @Override
    public void run(String... args) {
        service.create(new EmployeeRequest("Asha Rao", "asha@corp.com", 90000, "IT"));
        service.create(new EmployeeRequest("Bharat Kumar", "bharat@corp.com", 60000, "IT"));
        service.create(new EmployeeRequest("Chitra Iyer", "chitra@corp.com", 75000, "HR"));
        service.create(new EmployeeRequest("Dev Patel", "dev@corp.com", 50000, "FIN"));
        System.out.println("[DATA] Sample employees loaded. Try GET http://localhost:8080/api/employees");
    }
}

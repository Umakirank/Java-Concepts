package org.java.spring.service;

import org.java.spring.dto.EmployeeRequest;
import org.java.spring.dto.EmployeeResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/** Service contract. Controllers depend on this INTERFACE (Dependency Inversion Principle). */
public interface EmployeeService {
    EmployeeResponse create(EmployeeRequest request);
    EmployeeResponse getById(Long id);
    List<EmployeeResponse> getAll();
    Page<EmployeeResponse> search(String text, Pageable pageable);
    EmployeeResponse update(Long id, EmployeeRequest request);
    void delete(Long id);
    List<EmployeeResponse> highEarners(double minSalary);
}

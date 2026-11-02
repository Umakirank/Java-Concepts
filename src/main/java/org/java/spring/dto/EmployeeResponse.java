package org.java.spring.dto;

import org.java.spring.entity.Employee;

/** Response DTO returned to API clients. Static factory converts entity -> DTO. */
public record EmployeeResponse(Long id, String name, String email, double salary, String department) {
    public static EmployeeResponse from(Employee e) {
        return new EmployeeResponse(e.getId(), e.getName(), e.getEmail(), e.getSalary(),
                e.getDepartment() == null ? null : e.getDepartment().getName());
    }
}

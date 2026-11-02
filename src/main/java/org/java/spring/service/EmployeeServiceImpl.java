package org.java.spring.service;

import org.java.spring.dto.EmployeeRequest;
import org.java.spring.dto.EmployeeResponse;
import org.java.spring.entity.Department;
import org.java.spring.entity.Employee;
import org.java.spring.exception.ResourceNotFoundException;
import org.java.spring.repository.DepartmentRepository;
import org.java.spring.repository.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Service + @Transactional + CONSTRUCTOR INJECTION
 *
 * @Service : business-logic layer bean (a specialised @Component).
 * CONSTRUCTOR INJECTION (recommended): fields are final, easy to test, no reflection hacks, and it fails
 *   at startup if a dependency is missing. Single constructor => @Autowired is optional.
 *
 * @Transactional : wraps the method in a DB transaction (done via an AOP proxy).
 *   ACID = Atomicity, Consistency, Isolation, Durability.
 *   Rolls back on RuntimeException/Error by default (NOT on checked exceptions unless rollbackFor).
 *   readOnly = true : optimisation for queries.
 *   Propagation: REQUIRED (default, join or create), REQUIRES_NEW (always new), SUPPORTS, MANDATORY,
 *                NOT_SUPPORTED, NEVER, NESTED.
 *   Isolation: READ_UNCOMMITTED, READ_COMMITTED, REPEATABLE_READ, SERIALIZABLE
 *     (dirty read, non-repeatable read, phantom read problems).
 *   PITFALL: self-invocation (this.method()) bypasses the proxy, so @Transactional is ignored.
 */
@Service
@Transactional(readOnly = true)
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final NotificationService notificationService;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository,
                               DepartmentRepository departmentRepository,
                               NotificationService notificationService) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.notificationService = notificationService;
    }

    @Override
    @Transactional                                       // overrides class-level readOnly
    public EmployeeResponse create(EmployeeRequest r) {
        if (employeeRepository.existsByEmail(r.email()))
            throw new IllegalArgumentException("Email already exists: " + r.email());
        Department dept = departmentRepository.findByName(r.department())
                .orElseGet(() -> departmentRepository.save(new Department(r.department())));
        Employee saved = employeeRepository.save(new Employee(r.name(), r.email(), r.salary(), dept));
        notificationService.send("Welcome " + saved.getName());
        return EmployeeResponse.from(saved);
    }

    @Override
    public EmployeeResponse getById(Long id) { return EmployeeResponse.from(find(id)); }

    @Override
    public List<EmployeeResponse> getAll() {
        return employeeRepository.findAll().stream().map(EmployeeResponse::from).toList();
    }

    @Override
    public Page<EmployeeResponse> search(String text, Pageable pageable) {
        return employeeRepository.search(text, pageable).map(EmployeeResponse::from);
    }

    @Override
    @Transactional
    public EmployeeResponse update(Long id, EmployeeRequest r) {
        Employee e = find(id);                           // MANAGED entity
        e.setName(r.name());
        e.setEmail(r.email());
        e.setSalary(r.salary());
        e.setDepartment(departmentRepository.findByName(r.department())
                .orElseGet(() -> departmentRepository.save(new Department(r.department()))));
        return EmployeeResponse.from(e);                 // no save() needed: dirty checking flushes at commit
    }

    @Override
    @Transactional
    public void delete(Long id) { employeeRepository.delete(find(id)); }

    @Override
    public List<EmployeeResponse> highEarners(double minSalary) {
        return employeeRepository.findBySalaryGreaterThan(minSalary).stream().map(EmployeeResponse::from).toList();
    }

    private Employee find(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id " + id));
    }
}

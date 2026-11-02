package org.java.spring.repository;

import org.java.spring.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/** Simple repository: Spring Data generates the implementation at runtime from the method name. */
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Optional<Department> findByName(String name);
}

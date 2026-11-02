package org.java.spring.repository;

import org.java.spring.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * SPRING DATA JPA
 *
 * WHAT: write ONLY an interface; Spring creates the implementation (a proxy) at startup.
 * HIERARCHY: Repository -> CrudRepository -> PagingAndSortingRepository -> JpaRepository.
 *   JpaRepository gives save, findById, findAll, deleteById, count, existsById, flush, paging, sorting.
 *
 * DERIVED QUERIES: method name becomes the query (findByEmail, findBySalaryGreaterThan,
 *   findByDepartmentNameOrderBySalaryDesc, existsByEmail, countBy..., deleteBy...).
 * @Query : custom JPQL (object based) or native SQL (nativeQuery = true).
 * Pageable : pagination + sorting (page, size, sort).
 * @Repository : marks the persistence layer and translates SQL exceptions to Spring's DataAccessException.
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByEmail(String email);

    boolean existsByEmail(String email);

    List<Employee> findBySalaryGreaterThan(double salary);

    List<Employee> findByDepartmentNameOrderBySalaryDesc(String departmentName);

    @Query("select e from Employee e join fetch e.department where e.salary between :min and :max")
    List<Employee> findInSalaryRange(@Param("min") double min, @Param("max") double max);

    @Query("select e from Employee e where lower(e.name) like lower(concat('%', :text, '%'))")
    Page<Employee> search(@Param("text") String text, Pageable pageable);

    @Query("select e.department.name, avg(e.salary) from Employee e group by e.department.name")
    List<Object[]> averageSalaryByDepartment();
}

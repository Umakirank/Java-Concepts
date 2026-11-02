package org.java.spring.entity;

import jakarta.persistence.*;

/**
 * ENTITY with relationship.  Table: employees (department_id is a foreign key).
 *
 * @ManyToOne(fetch = LAZY) : many employees belong to one department.
 * Entity states (JPA lifecycle): NEW/transient -> MANAGED (persistent) -> DETACHED -> REMOVED.
 * Dirty checking: changes to a MANAGED entity inside a transaction are flushed automatically.
 */
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    private double salary;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    protected Employee() {}
    public Employee(String name, String email, double salary, Department department) {
        this.name = name; this.email = email; this.salary = salary; this.department = department;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }
    public Department getDepartment() { return department; }
    public void setDepartment(Department department) { this.department = department; }
}

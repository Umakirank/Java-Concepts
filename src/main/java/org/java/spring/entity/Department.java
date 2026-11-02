package org.java.spring.entity;

import jakarta.persistence.*;

/**
 * JPA / HIBERNATE CONCEPTS
 *
 * JPA  = specification (jakarta.persistence). HIBERNATE = the most popular implementation (ORM).
 * ORM  = Object Relational Mapping: map Java classes to DB tables so you work with objects, not SQL.
 *
 * @Entity            : class mapped to a table.   @Table(name=..) customises table name.
 * @Id                : primary key.               @GeneratedValue : auto-generate (IDENTITY, SEQUENCE, ...).
 * @Column            : column settings (nullable, unique, length).
 * @ManyToOne / @OneToMany / @OneToOne / @ManyToMany : relationships.
 *   mappedBy = inverse side (no FK column here). @JoinColumn = owning side holds the FK.
 * FetchType.LAZY (load when used, default for collections) vs EAGER (load immediately).
 * N+1 problem: 1 query for parents + N queries for children. Fix with JOIN FETCH or @EntityGraph.
 */
@Entity
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String name;

    protected Department() {}                 // JPA requires a no-arg constructor
    public Department(String name) { this.name = name; }

    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}

# Java Learning & Interview Project (Core Java 21 + Spring Boot 3)

Every class is a **runnable lesson**. The top of each file has a Javadoc block with:
**CONCEPT -> DEFINITION -> TYPES / RULES -> INTERVIEW POINTS**, and the code below proves it with output.

## Requirements
- JDK 21, Maven 3.9+ (only needed for the Spring part and tests)

## How to run
```bash
# 1) Any core Java demo (no Spring needed). Example:
mvn compile
java -cp target/classes org.java.collections.MapDemo

# or in IntelliJ / VS Code: open a class and press the green Run button next to main()

# 2) Spring Boot app
mvn spring-boot:run
#   http://localhost:8080/            -> app info
#   http://localhost:8080/api/employees
#   http://localhost:8080/h2-console  (JDBC URL: jdbc:h2:mem:learningdb, user: sa, empty password)

# 3) Tests
mvn test
```
Note: `JvmMemoryAndGcDemo` deliberately triggers OutOfMemoryError; run it with `-Xmx64m`.

## Suggested learning order

| # | Package | What you learn |
|---|---------|----------------|
| 1 | `org.java.fundamentals` | Data types, casting, operators, control flow, arrays, Strings (pool, immutability), methods (overloading, varargs, pass-by-value, recursion) |
| 2 | `org.java.oops` | Class/object, constructors, static/final/this, 4 pillars, equals/hashCode, immutable class, inner classes, enum, records, sealed |
| 3 | `org.java.exceptions` | Checked vs unchecked, try/catch/finally, try-with-resources, custom exceptions |
| 4 | `org.java.generics` | Generic classes/methods, bounded types, wildcards (PECS), type erasure |
| 5 | `org.java.collections` | List, Set, Map, Queue/Deque, HashMap internals, Comparable vs Comparator, fail-fast vs fail-safe |
| 6 | `org.java.functional` | Lambdas, functional interfaces, Stream API, Optional, java.time |
| 7 | `org.java.multithreading` | Threads, synchronization, volatile, atomic, locks, executors, CompletableFuture, deadlock, virtual threads |
| 8 | `org.java.io` | File I/O, NIO.2, serialization |
| 9 | `org.java.advanced` | Reflection, annotations, JVM memory & GC, design patterns, SOLID |
| 10 | `org.java.modern` | Java 9 to 21 features: var, switch expressions, text blocks, pattern matching, sealed, sequenced collections |
| 11 | `org.java.interview` | 19 classic coding problems + tricky "predict the output" questions |
| 12 | `org.java.spring` | Spring Boot: IoC/DI, beans, scopes, lifecycle, REST, JPA/Hibernate, validation, exception handling, AOP, transactions, testing |

Also read **INTERVIEW_QA.md** for concise question-and-answer revision notes.

## Spring package map
```
org.java.spring
 ├─ JavaLearningApplication   @SpringBootApplication, auto-config, component scan
 ├─ controller/               @RestController, REST verbs, @PathVariable/@RequestParam/@RequestBody
 ├─ service/                  @Service, @Transactional, constructor DI, @Primary/@Qualifier
 ├─ repository/               Spring Data JPA, derived queries, @Query, paging
 ├─ entity/                   @Entity, @ManyToOne, lazy loading, N+1
 ├─ dto/                      Records + Bean Validation
 ├─ exception/                @RestControllerAdvice global handler
 ├─ aop/                      @Aspect, @Before/@Around/@AfterThrowing, pointcuts
 └─ config/                   @Configuration/@Bean, scopes, @Value, lifecycle, CommandLineRunner
```

## Try the REST API
```bash
curl localhost:8080/api/employees
curl -X POST localhost:8080/api/employees -H "Content-Type: application/json" \
  -d '{"name":"Eve","email":"eve@corp.com","salary":65000,"department":"IT"}'
curl "localhost:8080/api/employees/search?text=a&page=0&size=2&sort=salary"
curl -X DELETE localhost:8080/api/employees/1
```

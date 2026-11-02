package org.java.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SPRING BOOT - ENTRY POINT
 *
 * WHAT IS SPRING?
 *   A Java framework built around Inversion of Control (IoC) and Dependency Injection (DI): you stop
 *   creating objects with 'new'; the Spring container creates, wires and manages them (beans).
 *
 * WHAT IS SPRING BOOT?
 *   Spring + auto-configuration + starter dependencies + embedded server (Tomcat) + no XML.
 *   Run a jar with: java -jar app.jar.
 *
 * @SpringBootApplication = three annotations in one:
 *   1. @Configuration           : this class can declare @Bean methods.
 *   2. @EnableAutoConfiguration : configure beans automatically from the classpath
 *                                 (H2 on classpath -> DataSource; spring-web -> DispatcherServlet).
 *   3. @ComponentScan           : scan this package and sub-packages for @Component, @Service,
 *                                 @Repository, @Controller. THIS is why this class sits at the TOP package.
 *
 * STARTUP FLOW: SpringApplication.run -> create ApplicationContext -> scan -> create beans ->
 *   inject dependencies -> start embedded Tomcat -> app ready.
 *
 * RUN:  mvn spring-boot:run     then open http://localhost:8080/api/employees
 */
@SpringBootApplication
public class JavaLearningApplication {
    public static void main(String[] args) {
        SpringApplication.run(JavaLearningApplication.class, args);
    }
}

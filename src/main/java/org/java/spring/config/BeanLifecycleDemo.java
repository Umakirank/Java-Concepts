package org.java.spring.config;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * BEAN LIFECYCLE CALLBACKS + CommandLineRunner (runs once after startup).
 *
 * @PostConstruct : runs after dependency injection is complete (initialise resources).
 * @PreDestroy    : runs just before the bean is destroyed (cleanup).
 * @Lazy          : create the bean on first use instead of at startup.
 * CommandLineRunner / ApplicationRunner : code to run after the context starts (data loading, checks).
 */
@Component
public class BeanLifecycleDemo implements CommandLineRunner {

    @PostConstruct
    public void init() { System.out.println("[LIFECYCLE] @PostConstruct - bean ready"); }

    @PreDestroy
    public void cleanup() { System.out.println("[LIFECYCLE] @PreDestroy - shutting down"); }

    @Override
    public void run(String... args) {
        System.out.println("[LIFECYCLE] CommandLineRunner executed after startup");
    }
}

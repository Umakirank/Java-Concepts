package org.java.spring.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * DEPENDENCY INJECTION - TWO IMPLEMENTATIONS OF ONE INTERFACE
 *
 * Problem: Spring finds 2 beans of type NotificationService -> NoUniqueBeanDefinitionException.
 * Solutions:
 *   @Primary   : this bean wins by default (used here).
 *   @Qualifier("smsNotificationService") : pick explicitly by bean name at the injection point.
 *   Inject List<NotificationService> or Map<String, NotificationService> to receive all of them.
 *
 * STEREOTYPE ANNOTATIONS (all register a bean): @Component (generic), @Service (business),
 *   @Repository (persistence + exception translation), @Controller / @RestController (web).
 *
 * DI TYPES: constructor (best), setter (optional deps), field @Autowired (avoid: hard to test).
 * Bean name defaults to class name with lower-case first letter.
 */
@Component
@Primary
public class EmailNotificationService implements NotificationService {
    @Override
    public void send(String message) { System.out.println("[EMAIL] " + message); }
}

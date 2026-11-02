package org.java.spring.service;

/** One interface, many implementations: used to explain @Primary, @Qualifier and List injection. */
public interface NotificationService {
    void send(String message);
}

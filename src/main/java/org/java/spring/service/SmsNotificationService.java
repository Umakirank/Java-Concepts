package org.java.spring.service;

import org.springframework.stereotype.Component;

/**
 * Second implementation. Use it with:
 *   public MyService(@Qualifier("smsNotificationService") NotificationService n) { ... }
 */
@Component
public class SmsNotificationService implements NotificationService {
    @Override
    public void send(String message) { System.out.println("[SMS] " + message); }
}

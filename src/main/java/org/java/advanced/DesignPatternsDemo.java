package org.java.advanced;

import java.util.ArrayList;
import java.util.List;

/**
 * CONCEPT: Design Patterns (reusable solutions to common design problems)
 *
 * CREATIONAL : Singleton, Factory, Builder, Prototype
 * STRUCTURAL : Adapter, Decorator, Proxy (Spring AOP uses it), Facade
 * BEHAVIORAL : Observer, Strategy, Template Method, Command
 *
 * SOLID PRINCIPLES (interview favourite):
 *   S - Single Responsibility : one class, one reason to change.
 *   O - Open/Closed           : open for extension, closed for modification.
 *   L - Liskov Substitution   : subclass must be usable wherever parent is expected.
 *   I - Interface Segregation : many small interfaces beat one fat interface.
 *   D - Dependency Inversion  : depend on abstractions, not concretions (Spring DI!).
 * OTHER: DRY, KISS, YAGNI, high cohesion & low coupling.
 */
public class DesignPatternsDemo {

    // 1. SINGLETON (thread-safe, lazy, bill-pugh holder). Enum singleton is the safest.
    static class Singleton {
        private Singleton() {}
        private static class Holder { static final Singleton INSTANCE = new Singleton(); }
        static Singleton getInstance() { return Holder.INSTANCE; }
    }
    enum EnumSingleton { INSTANCE; void hello() { System.out.println("enum singleton"); } }

    // 2. FACTORY
    interface Notification { void send(String msg); }
    static class Email implements Notification { public void send(String m) { System.out.println("Email: " + m); } }
    static class Sms implements Notification { public void send(String m) { System.out.println("SMS: " + m); } }
    static class NotificationFactory {
        static Notification create(String type) {
            return switch (type.toLowerCase()) {
                case "email" -> new Email();
                case "sms" -> new Sms();
                default -> throw new IllegalArgumentException("Unknown " + type);
            };
        }
    }

    // 3. BUILDER
    static class Pizza {
        private final String size; private final boolean cheese; private final boolean olives;
        private Pizza(Builder b) { size = b.size; cheese = b.cheese; olives = b.olives; }
        static Builder builder() { return new Builder(); }
        static class Builder {
            private String size = "M"; private boolean cheese; private boolean olives;
            Builder size(String s) { size = s; return this; }
            Builder cheese(boolean c) { cheese = c; return this; }
            Builder olives(boolean o) { olives = o; return this; }
            Pizza build() { return new Pizza(this); }
        }
        @Override public String toString() { return "Pizza[" + size + ", cheese=" + cheese + ", olives=" + olives + "]"; }
    }

    // 4. STRATEGY
    interface DiscountStrategy { double apply(double price); }

    // 5. OBSERVER
    interface Observer { void update(String news); }
    static class NewsChannel {
        private final List<Observer> observers = new ArrayList<>();
        void subscribe(Observer o) { observers.add(o); }
        void publish(String news) { observers.forEach(o -> o.update(news)); }
    }

    // 6. DECORATOR
    interface Coffee { double cost(); }
    static class SimpleCoffee implements Coffee { public double cost() { return 50; } }
    static class MilkDecorator implements Coffee {
        private final Coffee base; MilkDecorator(Coffee c) { base = c; }
        public double cost() { return base.cost() + 15; }
    }

    public static void main(String[] args) {
        System.out.println(Singleton.getInstance() == Singleton.getInstance());
        EnumSingleton.INSTANCE.hello();

        NotificationFactory.create("email").send("Hello via factory");
        System.out.println(Pizza.builder().size("L").cheese(true).build());

        DiscountStrategy festival = p -> p * 0.8;
        DiscountStrategy none = p -> p;
        System.out.println(festival.apply(1000) + " " + none.apply(1000));

        NewsChannel ch = new NewsChannel();
        ch.subscribe(n -> System.out.println("Subscriber 1 got: " + n));
        ch.subscribe(n -> System.out.println("Subscriber 2 got: " + n));
        ch.publish("Java 21 released");

        System.out.println(new MilkDecorator(new SimpleCoffee()).cost());
    }
}

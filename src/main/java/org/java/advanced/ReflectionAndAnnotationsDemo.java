package org.java.advanced;

import java.lang.annotation.*;
import java.lang.reflect.*;

/**
 * CONCEPT 1: Reflection
 *   Inspect and modify classes, methods, fields and constructors AT RUNTIME (java.lang.reflect).
 *   Entry point: Class object -> obj.getClass() | Foo.class | Class.forName("pkg.Foo").
 *   Used by frameworks: Spring (DI), Hibernate, JUnit, Jackson.
 *   Downsides: slower, breaks encapsulation (setAccessible), no compile-time safety.
 *
 * CONCEPT 2: Annotations
 *   Metadata attached to code. Built-in: @Override, @Deprecated, @FunctionalInterface, @SuppressWarnings.
 *   Custom: declare with @interface plus meta-annotations
 *     @Retention (SOURCE / CLASS / RUNTIME) and @Target (TYPE, METHOD, FIELD ...).
 *   Only RUNTIME retention can be read through reflection.
 *   This is exactly how Spring's @Component, @Autowired, @GetMapping work.
 */
public class ReflectionAndAnnotationsDemo {

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    @interface RunMe { String description() default "no description"; }

    static class Tasks {
        private String secret = "hidden";
        @RunMe(description = "first task") public void one() { System.out.println("task one executed"); }
        @RunMe public void two() { System.out.println("task two executed"); }
        public void ignored() { System.out.println("never called"); }
    }

    public static void main(String[] args) throws Exception {
        Class<?> clazz = Tasks.class;
        System.out.println("Class: " + clazz.getSimpleName());
        for (Method m : clazz.getDeclaredMethods())
            if (m.isAnnotationPresent(RunMe.class)) System.out.println("annotated: " + m.getName());

        Object obj = clazz.getDeclaredConstructor().newInstance();       // create instance dynamically
        for (Method m : clazz.getDeclaredMethods()) {                    // mini "framework"
            RunMe a = m.getAnnotation(RunMe.class);
            if (a != null) { System.out.println("[" + a.description() + "]"); m.invoke(obj); }
        }

        Field f = clazz.getDeclaredField("secret");                      // break encapsulation
        f.setAccessible(true);
        System.out.println("private field read via reflection: " + f.get(obj));
        f.set(obj, "changed");
        System.out.println(f.get(obj));
    }
}

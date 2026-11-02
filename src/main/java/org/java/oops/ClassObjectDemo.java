package org.java.oops;

/**
 * CONCEPT: Class, Object, Constructor, this, static, final
 *
 * DEFINITION:
 *   Class  = blueprint/template (fields + methods).
 *   Object = real instance of a class created with 'new' (lives in heap).
 *   Constructor = special method (same name as class, no return type) that initializes an object.
 *
 * KEYWORDS:
 *   this    -> current object. this(...) calls another constructor of same class.
 *   static  -> belongs to the CLASS, shared by all objects (static variable, method, block).
 *   final   -> variable: constant | method: cannot be overridden | class: cannot be extended.
 *
 * EXECUTION ORDER (interview): static block -> instance block -> constructor.
 */
public class ClassObjectDemo {

    static class Student {
        static int count = 0;              // shared by all students
        static final String SCHOOL = "ABC School";   // constant
        private final int id;
        private String name;
        private int age;

        static { System.out.println("[static block] runs once when class loads"); }
        { System.out.println("[instance block] runs before every constructor"); }

        Student() { this("Unknown", 0); }  // constructor chaining
        Student(String name, int age) {
            this.name = name;              // 'this' resolves name clash
            this.age = age;
            this.id = ++count;
            System.out.println("[constructor] " + name);
        }

        void show() { System.out.println(id + " " + name + " " + age + " " + SCHOOL); }
        static int getCount() { return count; }   // static method: no 'this'
    }

    public static void main(String[] args) {
        Student s1 = new Student("Asha", 20);
        Student s2 = new Student();
        s1.show();
        s2.show();
        System.out.println("Total students: " + Student.getCount());
    }
}

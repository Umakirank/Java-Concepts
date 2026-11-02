package org.java.io;

import java.io.*;
import java.nio.file.*;
import java.util.List;

/**
 * CONCEPT: File I/O and Serialization
 *
 * STREAMS: byte streams (InputStream/OutputStream) for binary; character streams (Reader/Writer) for text.
 *   BufferedReader/BufferedWriter add buffering for speed.
 * NIO.2 (java.nio.file): Path, Paths, Files - modern, simple API (Files.readAllLines, Files.write, Files.walk).
 *
 * SERIALIZATION: converting an object to a byte stream (ObjectOutputStream) and back (ObjectInputStream).
 *   - Class must implement Serializable (marker interface).
 *   - serialVersionUID versions the class; mismatch -> InvalidClassException.
 *   - transient fields and static fields are NOT serialized (e.g. passwords).
 *   - Parent class must be Serializable or have a no-arg constructor.
 *
 * Always close resources: use try-with-resources.
 */
public class FileIoAndSerializationDemo {

    static class User implements Serializable {
        private static final long serialVersionUID = 1L;
        String name;
        transient String password;      // skipped
        User(String n, String p) { name = n; password = p; }
    }

    public static void main(String[] args) throws Exception {
        Path dir = Files.createTempDirectory("javademo");
        Path file = dir.resolve("notes.txt");

        Files.write(file, List.of("line one", "line two", "line three"));   // NIO write
        System.out.println(Files.readAllLines(file));
        Files.writeString(file, "appended\n", StandardOpenOption.APPEND);
        System.out.println("exists=" + Files.exists(file) + " size=" + Files.size(file));

        try (BufferedReader br = new BufferedReader(new FileReader(file.toFile()))) {   // classic IO
            String line; int n = 0;
            while ((line = br.readLine()) != null) n++;
            System.out.println("lines read: " + n);
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(dir.resolve("out.txt").toFile()))) {
            bw.write("hello writer"); bw.newLine();
        }

        // Serialization
        Path ser = dir.resolve("user.ser");
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ser.toFile()))) {
            oos.writeObject(new User("Kiran", "secret123"));
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ser.toFile()))) {
            User u = (User) ois.readObject();
            System.out.println("deserialized name=" + u.name + " password=" + u.password);   // password null
        }

        try (var paths = Files.list(dir)) { paths.forEach(p -> System.out.println(p.getFileName())); }
    }
}

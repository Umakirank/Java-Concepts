package org.java.functional;

import java.util.*;
import java.util.stream.*;

/**
 * CONCEPT: Stream API (Java 8)
 *
 * DEFINITION:
 *   A pipeline to process collections declaratively. A stream does NOT store data and cannot be reused.
 *   Source -> Intermediate operations (lazy) -> Terminal operation (triggers execution).
 *
 * INTERMEDIATE: filter, map, flatMap, distinct, sorted, limit, skip, peek
 * TERMINAL    : collect, forEach, reduce, count, min, max, anyMatch, allMatch, noneMatch, findFirst, toList
 * COLLECTORS  : toList, toSet, toMap, groupingBy, partitioningBy, joining, counting, summingInt, averagingInt
 *
 * map vs flatMap: map = 1-to-1 transform. flatMap = 1-to-many and flattens nested streams.
 * parallelStream(): uses ForkJoinPool; use only for CPU heavy, large, independent work.
 */
public class StreamApiDemo {

    record Employee(String name, String dept, double salary, int age) {}

    public static void main(String[] args) {
        List<Employee> emps = List.of(
                new Employee("Asha", "IT", 90000, 30),
                new Employee("Bob", "IT", 60000, 25),
                new Employee("Chen", "HR", 50000, 40),
                new Employee("Dina", "HR", 70000, 35),
                new Employee("Eli", "FIN", 80000, 28));

        List<Integer> nums = List.of(5, 2, 8, 2, 9, 1);
        System.out.println(nums.stream().filter(n -> n > 2).map(n -> n * n).toList());
        System.out.println(nums.stream().distinct().sorted(Comparator.reverseOrder()).toList());
        System.out.println(nums.stream().mapToInt(Integer::intValue).sum() + " "
                + nums.stream().reduce(Integer::max).get() + " " + IntStream.rangeClosed(1, 5).sum());

        System.out.println(emps.stream().map(Employee::name).collect(Collectors.joining(", ", "[", "]")));

        // Grouping
        Map<String, List<String>> byDept = emps.stream()
                .collect(Collectors.groupingBy(Employee::dept, TreeMap::new,
                        Collectors.mapping(Employee::name, Collectors.toList())));
        System.out.println(byDept);
        Map<String, Long> countByDept = emps.stream()
                .collect(Collectors.groupingBy(Employee::dept, TreeMap::new, Collectors.counting()));
        Map<String, Double> avgByDept = emps.stream()
                .collect(Collectors.groupingBy(Employee::dept, TreeMap::new, Collectors.averagingDouble(Employee::salary)));
        System.out.println(countByDept + " " + avgByDept);
        System.out.println(emps.stream().collect(Collectors.partitioningBy(e -> e.age() > 30, Collectors.counting())));

        // Highest salary / 2nd highest salary (very common)
        System.out.println(emps.stream().max(Comparator.comparingDouble(Employee::salary)).get().name());
        System.out.println(emps.stream().map(Employee::salary).sorted(Comparator.reverseOrder()).skip(1).findFirst().get());

        // Highest paid in each dept
        Map<String, Optional<Employee>> top = emps.stream().collect(
                Collectors.groupingBy(Employee::dept, TreeMap::new, Collectors.maxBy(Comparator.comparingDouble(Employee::salary))));
        top.forEach((d, e) -> System.out.println(d + " -> " + e.get().name()));

        // flatMap
        List<List<Integer>> nested = List.of(List.of(1, 2), List.of(3), List.of(4, 5));
        System.out.println(nested.stream().flatMap(List::stream).toList());

        // toMap, matchers, statistics
        System.out.println(emps.stream().collect(Collectors.toMap(Employee::name, Employee::age)).get("Chen"));
        System.out.println(emps.stream().anyMatch(e -> e.salary() > 85000) + " " + emps.stream().allMatch(e -> e.age() > 18));
        System.out.println(emps.stream().mapToDouble(Employee::salary).summaryStatistics());

        // Character frequency, first non-repeating char
        String s = "swiss";
        Map<Character, Long> freq = s.chars().mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(c -> c, LinkedHashMap::new, Collectors.counting()));
        System.out.println(freq + " first unique=" + freq.entrySet().stream().filter(e -> e.getValue() == 1).findFirst().get().getKey());

        // Lazy evaluation
        Stream.of(1, 2, 3).peek(x -> System.out.print("peek" + x + " ")).filter(x -> x > 1).findFirst();
        System.out.println();
    }
}

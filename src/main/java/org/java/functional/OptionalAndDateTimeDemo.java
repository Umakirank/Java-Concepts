package org.java.functional;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

/**
 * CONCEPT 1: Optional (Java 8)
 *   A container that may or may not hold a non-null value; avoids NullPointerException.
 *   Create: of(v) (v must be non-null), ofNullable(v), empty().
 *   Use: isPresent, ifPresent, orElse (always evaluated), orElseGet (lazy), orElseThrow, map, flatMap, filter.
 *   Do NOT use Optional for fields, method parameters, or collections.
 *
 * CONCEPT 2: Date & Time API (java.time, Java 8) - immutable and thread-safe
 *   LocalDate, LocalTime, LocalDateTime, ZonedDateTime, Instant, Duration (time), Period (date),
 *   DateTimeFormatter. Replaces the old mutable Date / Calendar / SimpleDateFormat.
 */
public class OptionalAndDateTimeDemo {

    static Optional<String> findUser(int id) { return id == 1 ? Optional.of("Alice") : Optional.empty(); }

    public static void main(String[] args) {
        System.out.println(findUser(1).map(String::toUpperCase).orElse("NONE"));
        System.out.println(findUser(2).orElse("Guest"));
        findUser(1).ifPresent(u -> System.out.println("Found " + u));
        System.out.println(findUser(2).isPresent());
        try { findUser(2).orElseThrow(() -> new IllegalStateException("not found")); }
        catch (IllegalStateException e) { System.out.println(e.getMessage()); }

        // Date & Time
        LocalDate date = LocalDate.of(2024, 2, 28);
        System.out.println(date.plusDays(2) + " leap=" + date.isLeapYear() + " day=" + date.getDayOfWeek());
        LocalDateTime dt = LocalDateTime.of(2024, 5, 17, 14, 30);
        System.out.println(dt.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm")));
        System.out.println(LocalDate.parse("2024-01-15").getMonth());
        System.out.println(Period.between(LocalDate.of(2000, 1, 1), LocalDate.of(2024, 6, 15)));
        System.out.println(ChronoUnit.DAYS.between(LocalDate.of(2024, 1, 1), LocalDate.of(2024, 12, 31)));
        System.out.println(Duration.between(LocalTime.of(9, 0), LocalTime.of(17, 30)).toMinutes() + " minutes");
        System.out.println(ZonedDateTime.of(dt, ZoneId.of("Asia/Kolkata")).withZoneSameInstant(ZoneId.of("UTC")));
    }
}

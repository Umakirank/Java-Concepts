package org.java.fundamentals;

/**
 * CONCEPT: Control Flow Statements
 *
 * DEFINITION:
 *   Statements that decide the order in which code executes.
 *
 * TYPES:
 *   Decision : if, if-else, else-if ladder, switch (old and new arrow style)
 *   Loops    : for, while, do-while (runs at least once), enhanced for-each
 *   Jump     : break, continue, return, labeled break
 *
 * INTERVIEW:
 *   - switch works on byte, short, int, char, String, enum (not long/float/double/boolean).
 *   - Old switch falls through if 'break' is missing. The arrow switch does not.
 */
public class ControlFlowDemo {
    public static void main(String[] args) {
        int marks = 82;
        if (marks >= 90) System.out.println("Grade A+");
        else if (marks >= 75) System.out.println("Grade A");
        else System.out.println("Grade B");

        int day = 2;
        switch (day) {
            case 1: System.out.println("Mon"); break;
            case 2: System.out.println("Tue");   // no break -> falls through
            case 3: System.out.println("Wed (fall-through)"); break;
            default: System.out.println("Other");
        }

        String type = switch (day) {             // Java 14+
            case 1, 7 -> "Weekend-ish";
            case 2, 3, 4 -> "Weekday";
            default -> "Unknown";
        };
        System.out.println(type);

        for (int i = 1; i <= 3; i++) System.out.print("for:" + i + " ");
        System.out.println();
        int w = 3;
        while (w > 0) System.out.print("while:" + w-- + " ");
        System.out.println();
        int d = 10;
        do { System.out.println("do-while runs once even if false, d=" + d); } while (d < 5);

        for (String s : new String[]{"a", "b", "c"}) System.out.print(s + " ");
        System.out.println();

        for (int i = 1; i <= 5; i++) {
            if (i == 2) continue;
            if (i == 4) break;
            System.out.print("i=" + i + " ");
        }
        System.out.println();

        outer:
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == 1 && j == 1) break outer;
                System.out.print("(" + i + "," + j + ") ");
            }
        }
        System.out.println();
    }
}

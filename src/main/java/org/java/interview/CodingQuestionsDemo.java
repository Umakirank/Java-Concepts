package org.java.interview;

import java.util.*;
import java.util.stream.Collectors;

/**
 * CONCEPT: Most-asked Java coding interview problems
 *
 * Each method is small, with its idea and complexity in the comment.
 * Topics: strings, arrays, numbers, collections, two pointers, hashing.
 */
public class CodingQuestionsDemo {

    // 1. Reverse a string - O(n)
    static String reverse(String s) { return new StringBuilder(s).reverse().toString(); }

    // 2. Palindrome - two pointers, O(n)
    static boolean isPalindrome(String s) {
        int i = 0, j = s.length() - 1;
        while (i < j) if (s.charAt(i++) != s.charAt(j--)) return false;
        return true;
    }

    // 3. Anagram - sort both or count letters, O(n)
    static boolean isAnagram(String a, String b) {
        if (a.length() != b.length()) return false;
        int[] count = new int[26];
        for (int i = 0; i < a.length(); i++) { count[a.charAt(i) - 'a']++; count[b.charAt(i) - 'a']--; }
        return Arrays.stream(count).allMatch(c -> c == 0);
    }

    // 4. First non-repeating character - LinkedHashMap, O(n)
    static Character firstUnique(String s) {
        Map<Character, Integer> m = new LinkedHashMap<>();
        for (char c : s.toCharArray()) m.merge(c, 1, Integer::sum);
        return m.entrySet().stream().filter(e -> e.getValue() == 1).map(Map.Entry::getKey).findFirst().orElse(null);
    }

    // 5. Fibonacci - iterative O(n), O(1) space
    static long fib(int n) {
        long a = 0, b = 1;
        for (int i = 0; i < n; i++) { long t = a + b; a = b; b = t; }
        return a;
    }

    // 6. Prime check - up to sqrt(n)
    static boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i = 2; (long) i * i <= n; i++) if (n % i == 0) return false;
        return true;
    }

    // 7. Two Sum - HashMap, O(n)
    static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> seen = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer j = seen.get(target - nums[i]);
            if (j != null) return new int[]{j, i};
            seen.put(nums[i], i);
        }
        return new int[0];
    }

    // 8. Find duplicates - HashSet, O(n)
    static Set<Integer> duplicates(int[] nums) {
        Set<Integer> seen = new HashSet<>(), dup = new TreeSet<>();
        for (int n : nums) if (!seen.add(n)) dup.add(n);
        return dup;
    }

    // 9. Missing number in 1..n - sum formula, O(n)
    static int missing(int[] nums, int n) {
        int expected = n * (n + 1) / 2, actual = 0;
        for (int x : nums) actual += x;
        return expected - actual;
    }

    // 10. Binary search - O(log n), array must be sorted
    static int binarySearch(int[] a, int key) {
        int lo = 0, hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;                 // avoids overflow
            if (a[mid] == key) return mid;
            if (a[mid] < key) lo = mid + 1; else hi = mid - 1;
        }
        return -1;
    }

    // 11. Max subarray sum (Kadane) - O(n)
    static int kadane(int[] a) {
        int best = a[0], cur = a[0];
        for (int i = 1; i < a.length; i++) { cur = Math.max(a[i], cur + a[i]); best = Math.max(best, cur); }
        return best;
    }

    // 12. Move zeros to end - two pointers, O(n)
    static int[] moveZeros(int[] a) {
        int pos = 0;
        for (int x : a) if (x != 0) a[pos++] = x;
        while (pos < a.length) a[pos++] = 0;
        return a;
    }

    // 13. Armstrong, GCD, factorial digits
    static boolean isArmstrong(int n) {
        int sum = 0, t = n, digits = String.valueOf(n).length();
        while (t > 0) { sum += (int) Math.pow(t % 10, digits); t /= 10; }
        return sum == n;
    }
    static int gcd(int a, int b) { return b == 0 ? a : gcd(b, a % b); }

    // 14. Bubble sort and simple sorting idea - O(n^2)
    static int[] bubbleSort(int[] a) {
        for (int i = 0; i < a.length - 1; i++)
            for (int j = 0; j < a.length - 1 - i; j++)
                if (a[j] > a[j + 1]) { int t = a[j]; a[j] = a[j + 1]; a[j + 1] = t; }
        return a;
    }

    // 15. Count vowels, word count, longest word via streams
    static long vowels(String s) { return s.toLowerCase().chars().filter(c -> "aeiou".indexOf(c) >= 0).count(); }
    static String longestWord(String s) { return Arrays.stream(s.split("\\s+")).max(Comparator.comparingInt(String::length)).orElse(""); }

    // 16. Second largest without sorting - O(n)
    static int secondLargest(int[] a) {
        int max = Integer.MIN_VALUE, second = Integer.MIN_VALUE;
        for (int x : a) {
            if (x > max) { second = max; max = x; }
            else if (x > second && x != max) second = x;
        }
        return second;
    }

    // 17. Reverse words, 18. Remove duplicate chars
    static String reverseWords(String s) {
        List<String> w = Arrays.asList(s.trim().split("\\s+"));
        Collections.reverse(w);
        return String.join(" ", w);
    }
    static String removeDuplicateChars(String s) {
        return s.chars().mapToObj(c -> String.valueOf((char) c)).distinct().collect(Collectors.joining());
    }

    // 19. FizzBuzz
    static List<String> fizzBuzz(int n) {
        List<String> r = new ArrayList<>();
        for (int i = 1; i <= n; i++) r.add(i % 15 == 0 ? "FizzBuzz" : i % 3 == 0 ? "Fizz" : i % 5 == 0 ? "Buzz" : "" + i);
        return r;
    }

    public static void main(String[] args) {
        System.out.println(reverse("interview") + " | " + isPalindrome("madam") + " | " + isAnagram("listen", "silent"));
        System.out.println(firstUnique("swiss") + " | fib(10)=" + fib(10) + " | prime(97)=" + isPrime(97));
        System.out.println(Arrays.toString(twoSum(new int[]{2, 7, 11, 15}, 9)) + " | dup=" + duplicates(new int[]{1, 2, 3, 2, 1}));
        System.out.println("missing=" + missing(new int[]{1, 2, 4, 5}, 5) + " | bs=" + binarySearch(new int[]{1, 3, 5, 7, 9}, 7));
        System.out.println("kadane=" + kadane(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}) + " | zeros=" + Arrays.toString(moveZeros(new int[]{0, 1, 0, 3, 12})));
        System.out.println("armstrong(153)=" + isArmstrong(153) + " | gcd=" + gcd(48, 18) + " | sort=" + Arrays.toString(bubbleSort(new int[]{5, 1, 4, 2})));
        System.out.println("vowels=" + vowels("Education") + " | longest=" + longestWord("java is awesome") + " | 2nd=" + secondLargest(new int[]{4, 9, 2, 9, 7}));
        System.out.println(reverseWords("java is fun") + " | " + removeDuplicateChars("programming"));
        System.out.println(fizzBuzz(15));
    }
}

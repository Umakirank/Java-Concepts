package org.java.fundamentals;

import java.util.Arrays;

/**
 * CONCEPT: Arrays
 *
 * DEFINITION:
 *   A fixed-size, ordered container holding elements of the SAME type, accessed by index (from 0).
 *   Arrays are objects stored in the heap. Size cannot change after creation.
 *
 * TYPES: 1D, 2D (array of arrays), jagged (rows with different length).
 *
 * INTERVIEW:
 *   - Out-of-range index -> ArrayIndexOutOfBoundsException.
 *   - arr.length is a field; String.length() is a method.
 *   - Arrays.equals compares content; == compares reference.
 *   - Use ArrayList when size must grow.
 */
public class ArraysDemo {
    public static void main(String[] args) {
        int[] nums = {5, 3, 9, 1};
        int[] empty = new int[3];                    // default 0
        System.out.println("length=" + nums.length + " empty=" + Arrays.toString(empty));

        Arrays.sort(nums);
        System.out.println("sorted=" + Arrays.toString(nums));
        System.out.println("binarySearch(5)=" + Arrays.binarySearch(nums, 5));
        System.out.println("copyOf=" + Arrays.toString(Arrays.copyOf(nums, 6)));
        System.out.println("equals=" + Arrays.equals(nums, new int[]{1, 3, 5, 9}));

        int[][] matrix = {{1, 2, 3}, {4, 5, 6}};
        System.out.println(Arrays.deepToString(matrix));

        int[][] jagged = new int[3][];
        jagged[0] = new int[]{1};
        jagged[1] = new int[]{1, 2};
        jagged[2] = new int[]{1, 2, 3};
        System.out.println(Arrays.deepToString(jagged));

        int[] a = {1, 2};
        int[] b = a;           // same object!
        b[0] = 99;
        System.out.println("a[0]=" + a[0] + " (changed through b)");

        try {
            System.out.println(nums[10]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Caught: " + e.getMessage());
        }
    }
}

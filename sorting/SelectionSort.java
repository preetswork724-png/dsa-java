/**
 * Algorithm:
 * <Selection Sort>
 *
 * Pattern:
 * <Sorting>
 *
 * Intuition:
 * - Divide the array into two parts:
 *   1. Sorted part
 *   2. Unsorted part
 *
 * - In every iteration:
 *   - Find the minimum element from the unsorted part.
 *   - Place it at its correct position in the sorted part.
 *
 * - The sorted portion grows from left to right.
 *
 * Working:
 * - Start from index 0.
 * - Find the smallest element in the remaining array.
 * - Swap it with the current index.
 * - Repeat the process for all indices.
 *
 * Time Complexity:
 * - Best Case    : O(N^2)
 * - Average Case : O(N^2)
 * - Worst Case   : O(N^2)
 *
 * - Why?
 * - Because even if the array is already sorted,
 *   we still scan the remaining array to find the minimum element.
 *
 * Space Complexity:
 * - O(1)
 * - In-place sorting algorithm.
 *
 * Stable Sorting?
 * - No
 * - Swapping may change the relative order of equal elements.
 *
 * In-place Sorting?
 * - Yes
 * - No extra data structure is used.
 *
 * Notes:
 * - Number of swaps is minimal compared to Bubble Sort.
 * - Performs at most (N - 1) swaps.
 * - Useful when memory writes/swaps are costly.
 * - Inefficient for large datasets because of O(N^2) comparisons.
 *
 * Key Observation:
 * - After every pass, one element reaches its correct position.
 * - After ith iteration:
 *   - Elements from [0 ... i] are sorted.
 *
 * Real-Life Analogy:
 * - Similar to arranging cards:
 * - Find the smallest card from the unsorted pile
 *   and place it in the correct position.
 */

package sorting;

import java.util.Arrays;

public class SelectionSort {
    public static void selectionSortAsc(int[] arr)
    {
        int index = 0 ;
        for(int i=0 ; i<arr.length ; i++) {
            index = i;
            int minElement = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < minElement) {
                    minElement = arr[j];
                    index = j;
                }
            }
            if (index != i) {
                int temp = arr[i];
                arr[i] = arr[index];
                arr[index] = temp;
            }
        }
        System.out.println(Arrays.toString(arr));
    }
}

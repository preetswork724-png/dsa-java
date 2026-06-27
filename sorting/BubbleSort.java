/**
 * Algorithm:
 * <Bubble Sort>
 *
 * Pattern:
 * <Sorting>
 *
 * Intuition:
 * - Repeatedly compare adjacent elements.
 * - If the current element is greater than the next element, swap them.
 * - After every pass, the largest unsorted element automatically moves to its correct position.
 * - This is why it is called "Bubble Sort" because larger elements bubble toward the end.
 *
 * Optimization Used:
 * - Use a boolean flag `swapped`.
 * - If in any pass no swapping happens, the array is already sorted.
 * - Break early to avoid unnecessary iterations.
 *
 * Time Complexity:
 * - Best Case  : O(N)
 *   - When the array is already sorted.
 *   - Due to the swapped optimization.
 *
 * - Average Case : O(N^2)
 *
 * - Worst Case : O(N^2)
 *   - When the array is sorted in reverse order.
 *
 * Space Complexity:
 * - O(1)
 * - In-place sorting algorithm.
 *
 * Stable Sorting?
 * - Yes
 * - Equal elements maintain their relative order.
 *
 * In-place Sorting?
 * - Yes
 * - No extra data structure is used.
 *
 * Notes:
 * - After every iteration, the largest element settles at the end.
 * - Inner loop runs till `arr.length - i - 1`
 *   because the last `i` elements are already sorted.
 * - Useful mainly for learning purposes.
 * - Rarely used in real-world systems due to poor efficiency.
 *
 * Real-Life Analogy:
 * - Imagine heavier bubbles rising to the surface in water.
 * - Similarly, larger elements move toward the end after every pass.
 */

package sorting;

public class BubbleSort {
    public static void bubbleSort(int[] arr)
    {
        boolean swapped ;
        int temp=0;
        for(int i = 0; i<arr.length-1;i++) {
            swapped = false;
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }
}

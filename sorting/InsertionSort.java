/**
 * Algorithm:
 * <Insertion Sort>
 *
 * Pattern:
 * <Sorting>
 *
 * Intuition:
 * - Divide the array into:
 *   1. Sorted part
 *   2. Unsorted part
 *
 * - Assume the first element is already sorted.
 * - Take one element from the unsorted part
 *   and insert it into its correct position
 *   in the sorted part.
 *
 * - Similar to how we arrange playing cards in hand.
 *
 * Working:
 * - Start from index 1.
 * - Compare the current element with previous elements.
 * - Shift all larger elements one step to the right.
 * - Insert the current element at its correct position.
 *
 * Time Complexity:
 * - Best Case    : O(N)
 *   - When the array is already sorted.
 *
 * - Average Case : O(N^2)
 *
 * - Worst Case   : O(N^2)
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
 * - Efficient for small datasets.
 * - Performs very well for nearly sorted arrays.
 * - Online Algorithm:
 *   - Can sort elements as they arrive.
 * - Reduces unnecessary swaps by shifting elements instead.
 *
 * Key Observation:
 * - After ith iteration:
 *   - Elements from [0 ... i] are sorted.
 *
 * Real-Life Analogy:
 * - Like arranging cards in your hand:
 * - Pick a card and place it at the correct position
 *   among already arranged cards.
 */

package sorting;

public class InsertionSort {
    public static void insertionSort(int[] arr) {

        for (int i = 1; i < arr.length; i++) {

            int current = arr[i];
            int j = i - 1;

            // Shift larger elements to the right
            while (j >= 0 && arr[j] > current) {
                arr[j + 1] = arr[j];
                j--;
            }

            // Place current element at correct position
            arr[j + 1] = current;
        }
    }
}

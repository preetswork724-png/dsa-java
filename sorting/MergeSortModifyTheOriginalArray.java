/**
 * Algorithm:
 * <Merge Sort>
 *
 * Pattern:
 * <Divide and Conquer>
 *
 * Intuition:
 * - Divide the array into smaller halves repeatedly.
 * - Continue dividing until each subarray contains only one element.
 * - A single element is always sorted.
 * - Then merge the smaller sorted arrays
 *   to form larger sorted arrays.
 *
 * Core Idea:
 * - Divide
 * - Conquer
 * - Combine
 *
 * Working:
 * - Find the middle index.
 * - Recursively divide the left half.
 * - Recursively divide the right half.
 * - Merge both sorted halves.
 *
 * Time Complexity:
 * - Best Case    : O(N log N)
 * - Average Case : O(N log N)
 * - Worst Case   : O(N log N)
 *
 * - Why?
 * - The array is divided log N times.
 * - At every level, merging takes O(N).
 *
 * Space Complexity:
 * - O(N)
 * - Extra temporary array is required during merging.
 *
 * Stable Sorting?
 * - Yes
 * - Equal elements maintain their relative order.
 *
 * In-place Sorting?
 * - No
 * - Extra auxiliary array is used.
 *
 * Notes:
 * - Very efficient for large datasets.
 * - Performs consistently in all cases.
 * - Preferred when stable sorting is required.
 * - Widely used in external sorting systems.
 * - Works very well on Linked Lists.
 *
 * Key Observation:
 * - The merge step is the most important part.
 * - Two sorted arrays can be merged efficiently
 *   using the two-pointer technique.
 *
 * Recursive Breakdown:
 * - Keep dividing until:
 *   - Subarray size becomes 1.
 *
 * Base Condition:
 * - If(end - start == 1) return;
 *
 * Why Merge Sort is Powerful?
 * - Predictable performance.
 * - Guaranteed O(N log N) complexity.
 * - Much faster than O(N^2) algorithms
 *   for large inputs.
 *
 * Drawback:
 * - Requires extra memory.
 * - Recursive calls add stack overhead.
 *
 * Real-Life Analogy:
 * - Imagine sorting a huge deck of cards:
 * - Split the deck into smaller piles,
 * - Sort each pile,
 * - Then combine the sorted piles together.
 */

package sorting;

public class MergeSortModifyTheOriginalArray {
    static void partition(int[] arr, int start, int end) {

        // Base Condition
        // If subarray size becomes 1, it is already sorted
        if (end - start == 1) {
            return;
        }

        // Find Mid
        int mid = start + (end - start) / 2;

        // Divide Left Half
        partition(arr, start, mid);

        // Divide Right Half
        partition(arr, mid, end);

        // Merge Both Sorted Halves
        merge(arr, start, mid, end);
    }

    static void merge(int[] arr, int start, int mid, int end) {

        // Temporary array to store merged result
        int[] merged = new int[end - start];

        int i = start; // Pointer for left half
        int j = mid;   // Pointer for right half
        int k = 0;     // Pointer for merged array

        // Merge both halves using two pointers
        while (i < mid && j < end) {

            if (arr[i] < arr[j]) {
                merged[k++] = arr[i++];
            } else {
                merged[k++] = arr[j++];
            }
        }

        // Copy remaining elements from left half
        while (i < mid) {
            merged[k++] = arr[i++];
        }

        // Copy remaining elements from right half
        while (j < end) {
            merged[k++] = arr[j++];
        }

        // Copy merged elements back to original array
        for (int l = 0; l < merged.length; l++) {
            arr[start + l] = merged[l];
        }
    }
}

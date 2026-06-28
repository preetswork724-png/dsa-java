/**
 * Algorithm:
 * <Counting Sort>
 *
 * Pattern:
 * <Non-Comparison Sorting>
 *
 * Intuition:
 * - Instead of directly comparing elements,
 *   count the frequency of every element.
 *
 * - Once frequencies are known,
 *   reconstruct the sorted array using counts.
 *
 * Core Idea:
 * - Count occurrences.
 * - Use frequency information
 *   to place elements in sorted order.
 *
 * Working:
 * - Find the maximum element.
 * - Create a count array of size (max + 1).
 * - Store frequency of every element.
 * - Traverse the count array
 *   and rebuild the sorted array.
 *
 * Time Complexity:
 * - Best Case    : O(N + K)
 * - Average Case : O(N + K)
 * - Worst Case   : O(N + K)
 *
 * Where:
 * - N = number of elements
 * - K = range of elements
 *
 * Space Complexity:
 * - O(K)
 * - Extra frequency array is required.
 *
 * Stable Sorting?
 * - Basic implementation → No
 * - Optimized prefix-sum version → Yes
 *
 * In-place Sorting?
 * - No
 * - Extra count array is required.
 *
 * Notes:
 * - Extremely fast for small ranges.
 * - Does not use element comparisons.
 * - Inefficient when element range is huge.
 *
 * Important Limitation:
 * - Works efficiently only when:
 *
 *   maxElement - minElement is small.
 *
 * - Poor choice for very large numbers.
 *
 * Example:
 * - Good:
 *   - 10^6 elements ranging from 0 to 100
 *
 * - Bad:
 *   - 10 elements ranging from 1 to 10^9
 *
 * Key Observation:
 * - Frequency array index itself
 *   represents the value.
 *
 * Why Counting Sort is Powerful?
 * - Linear time sorting.
 * - Faster than O(N log N) sorts
 *   for bounded integer ranges.
 *
 * Drawback:
 * - Cannot directly sort generic objects.
 * - Memory usage depends on range,
 *   not number of elements.
 *
 * Real-Life Analogy:
 * - Like counting votes:
 * - Instead of arranging votes individually,
 *   count how many votes each candidate received.
 */

package sorting;

public class CoutingSort {
    static void countingSort(int[] arr) {

        int min = arr[0], max = arr[0];

        // Find min and max

        for(int num : arr){
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        // Calculate the range

        int range = max - min + 1;

        // Create the count array

        int[] count = new int[range];

        // Store frequencies

        for(int num : arr){
            count[num - min]++;
        }

        // Build sorted array

        int index = 0;

        for(int i = 0; i < count.length; i++){

            while(count[i] > 0){
                arr[index++] = i + min;
                count[i]--;
            }
        }
    }
}

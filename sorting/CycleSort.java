/**
 * Algorithm:
 * <Cycle Sort>
 *
 * Pattern:
 * <Index Placement / Cyclic Sorting>
 *
 * Intuition:
 * - Every element belongs to a specific index.
 * - Place each element directly at its correct position.
 *
 * - Example:
 *   - Value 1 should be at index 0
 *   - Value 2 should be at index 1
 *   - Value N should be at index N-1
 *
 * Core Idea:
 * - Swap elements until every element reaches
 *   its correct index.
 *
 * Working:
 * - Traverse the array.
 * - For every element:
 *   - Find its correct index.
 *   - If element is not at correct position:
 *       swap it with the element at its correct index.
 *   - Else move ahead.
 *
 * Time Complexity:
 * - Best Case    : O(N)
 * - Average Case : O(N)
 * - Worst Case   : O(N)
 *
 * Why?
 * - Every element is placed at its correct position
 *   in at most one swap.
 *
 * Space Complexity:
 * - O(1)
 * - In-place sorting algorithm.
 *
 * Stable Sorting?
 * - No
 * - Relative order of equal elements may change.
 *
 * In-place Sorting?
 * - Yes
 * - Uses constant extra memory.
 *
 * Notes:
 * - Extremely useful for arrays containing:
 *   - numbers from 1 to N
 *   - numbers from 0 to N
 *
 * - Forms the foundation of many interview problems:
 *   - Missing Number
 *   - Find Duplicate
 *   - First Missing Positive
 *   - Set Mismatch
 *   - Find All Missing Numbers
 *
 * Key Observation:
 * - Correct index can usually be derived directly:
 *
 *   For values from 1 to N:
 *   - correctIndex = arr[i] - 1
 *
 *   For values from 0 to N:
 *   - correctIndex = arr[i]
 *
 * Why Cycle Sort is Powerful?
 * - Eliminates unnecessary comparisons.
 * - Avoids O(N log N) sorting.
 * - Many problems become linear-time solvable.
 *
 * Important Assumption:
 * - Works efficiently only when values
 *   have a known bounded range/index mapping.
 *
 * Drawback:
 * - Not suitable for arbitrary values.
 * - Limited use as a general-purpose sorting algorithm.
 *
 * Real-Life Analogy:
 * - Imagine students standing at wrong roll numbers.
 * - Each student directly swaps into
 *   their correct roll number position.
 */

package sorting;

public class CycleSort {

    public void sort(int[] arr){

        int i = 0, n = arr.length;

        while(i < n){

            int correctIndex = arr[i] - 1;

            if(arr[i] != arr[correctIndex]){
                int temp = arr[i];
                arr[i] = arr[correctIndex];
                arr[correctIndex] = temp;
            }
            else{
                i++;
            }
        }
    }
}


/**
 * Algorithm:
 * <Quick Sort>
 *
 * Pattern:
 * <Divide and Conquer>
 *
 * Intuition:
 * - Pick a pivot element.
 * - Place the pivot at its correct sorted position.
 * - Elements smaller than the pivot go to the left side.
 * - Elements greater than the pivot go to the right side.
 *
 * - Once the pivot reaches its correct position,
 *   recursively sort the left and right halves.
 *
 * Core Idea:
 * - Partition the array around a pivot.
 * - Recursively solve smaller subproblems.
 *
 * Working:
 * - Choose a pivot.
 * - Rearrange the array such that:
 *   - Left side contains smaller elements.
 *   - Right side contains larger elements.
 * - Pivot gets fixed at its correct position.
 * - Recursively apply the same process
 *   on left and right subarrays.
 *
 * Time Complexity:
 * - Best Case    : O(N log N)
 * - Average Case : O(N log N)
 * - Worst Case   : O(N^2)
 *
 * Worst Case Occurs When:
 * - Pivot selection is poor.
 * - Example:
 *   - Already sorted array
 *   - Reverse sorted array
 *   - Smallest/largest element always chosen as pivot
 *
 * Space Complexity:
 * - O(log N)
 * - Due to recursive stack space.
 *
 * Stable Sorting?
 * - No
 * - Relative order of equal elements may change.
 *
 * In-place Sorting?
 * - Yes
 * - No extra auxiliary array is required.
 *
 * Notes:
 * - Faster than Merge Sort in practice for arrays.
 * - Cache friendly due to contiguous memory access.
 * - Widely used in real-world sorting libraries.
 * - Pivot selection heavily affects performance.
 *
 * Common Pivot Choices:
 * - First element
 * - Last element
 * - Middle element
 * - Random pivot
 * - Median of three
 *
 * Key Observation:
 * - After partitioning:
 *   - Pivot is always at the correct sorted position.
 * - Only the left and right halves remain unsorted.
 *
 * Why Quick Sort is Popular?
 * - Excellent average-case performance.
 * - Very low constant factors.
 * - In-place sorting.
 *
 * Drawback:
 * - Worst-case complexity can degrade to O(N^2).
 * - Recursive depth may become large.
 *
 * Real-Life Analogy:
 * - Imagine arranging students around a chosen student:
 * - Shorter students move left,
 * - Taller students move right,
 * - The chosen student automatically reaches
 *   the correct position.
 */
package sorting;
import java.util.Random;

public class QuickSort {

    static Random random;

    static void quickSort(int[] arr, int low, int high) {

        // Base Condition
        if (low >= high) {
            return;
        }

        // Partition and get pivot index
        int pivotIndex = partitionLastEleAsPivot(arr, low, high);

        // Sort left half
        quickSort(arr, low, pivotIndex - 1);

        // Sort right half
        quickSort(arr, pivotIndex + 1, high);
    }

    static int partitionLastEleAsPivot(int[] arr, int low, int high){
        // Choosing last element as pivot
        int pivot = arr[high];

        int i = low - 1;

        // Place smaller elements on left side
        for (int j = low; j < high; j++) {

            if (arr[j] < pivot) {

                i++;

                // Swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // Place pivot at correct position
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    static int partitionFirstEleAsPivot(int[] arr, int low, int high){
        // Choose first element as pivot
        int pivot = arr[low];

        int i = low + 1;

        for (int j = low + 1; j <= high; j++) {

            if (arr[j] < pivot) {

                // Swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;

                i++;
            }
        }

        // Place pivot at correct position
        int temp = arr[low];
        arr[low] = arr[i - 1];
        arr[i - 1] = temp;

        return i - 1;
    }

    static int partitionMidEleAsPivot(int[] arr, int low, int high){
        // Find middle index
        int mid = low + (high - low) / 2;

        // Move middle element to end
        int temp = arr[mid];
        arr[mid] = arr[high];
        arr[high] = temp;

        // Now use standard Lomuto partition
        int pivot = arr[high];

        int i = low - 1;

        for (int j = low; j < high; j++) {

            if (arr[j] < pivot) {

                i++;

                // Swap arr[i] and arr[j]
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // Place pivot at correct position
        temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    static int partitionRandomEleAsPivot(int[] arr, int low, int high){

        int randomIndex = low + random.nextInt(high - low + 1);

        int temp = arr[high];
        arr[high] = arr[randomIndex];
        arr[randomIndex] = high;

        int pivot = arr[high];
        int i = low - 1;

        for(int j = low; j < high; j++){

            if(arr[j] < pivot) {
                i++;
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

}

/**
 * Problem:
 * <Find Minimum in Rotated Sorted Array>
 *
 * Link:
 * <https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/description/>
 *
 * Pattern:
 * <Rotated Sorted Array>
 *
 * Brute Force Intuition:
 * - Linearly traverse the array and the find minimum.
 *
 * - Why it is inefficient?
 * - Brute force requires O(N) time to find the minimum.
 * - When the array is large or when many queries are performed, the total time becomes O(Q * N).
 * - Inefficient compared to O(Q * log N) using binary search.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(1)
 *
 * Better and Optimal Approach (Used Below):
 * - Since, the array is sorted and rotated:
 * - A rotated sorted array always look like - [big sorted part][small sorted part].
 * - The array consists of two sorted chunks.
 * - There is exactly one break point: nums[pivot] < nums[pivot+1].
 * - And that's where the minimum lies.
 * - Why minimum lies in the "unsorted half"?
 * - A fully sorted half can never contain a minimum (except as boundary).
 * - Because, in a sorted segment: first element = smallest of that segment.
 * - So if a segment is sorted and its first element is > than anything else, then it cannot contain the global minimum.
 * - We compare, nums[mid] vs nums[high].
 * - Case A: nums[mid] > nums[high]
 * - The mid lies in the left sorted portion and the pivot must be on the right.
 * - Case B: nums[mid] <= nums[high]
 * - mid is in right sorted chunk, minimum could be mid or in the left.
 *
 * Why better approach is the optimal one?
 * - The array is sorted and rotated, so any comparison-based search has a lower bound of Ω(log N).
 * - Binary Search achieves this bound by eliminating half of the search space at every step.
 * - Each element is considered at most once in the decision path, and no linear scan is required.
 * - Therefore, the time complexity is O(log N), which matches the theoretical lower bound for searching in a sorted array, making this approach optimal.
 *
 * Time Complexity:
 * - O(log N)
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - Minimum always lies where order breaks.
 * - In rotated sorted array, minimum is always on the side where order breaks-compare mid with high to break that order.
 */

package binary_search;

public class FindMinimumInRotatedSortedArray {

    public static void main(String[] args) {
        System.out.println(findMin(new int[]{3,4,5,1,2}));
    }

    public static int findMin(int[] nums) {
        int n = nums.length,  low = 0, high = n-1;

        while(low < high){
            int mid = low + (high - low) / 2;

            if(nums[mid] > nums[high]){
                low = mid + 1;
            }
            else{
                high = mid;
            }
        }
        return nums[low];
    }
}

/**
 * Problem:
 * <Search in Rotated Sorted Array>
 *
 * Link:
 * <https://leetcode.com/problems/search-in-rotated-sorted-array/description/>
 *
 * Pattern:
 * <Mountain / Bitonic array>
 *
 * Brute Force Intuition:
 * - Linearly traverse the array to find the target.
 * - Return the index if the target is found otherwise return -1.
 *
 * - Why it is inefficient?
 * - Brute force requires O(N) time to find the target in the array.
 * - Loss of information as rotating the array leaves some sorted part behind.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(1)
 *
 * Better and Optimal Approach (Used below):
 * - Even though the whole array is not sorted, the array is always sorted around the mid.
 * - We first identify the sorted part and then check if the target lies in it.
 * - If yes, we search there; otherwise , we discard it and search the other half.
 *
 * Why better approach is the optimal one?
 * - The array is sorted around the mid, so any comparison-based search for an element has a lower bound of Ω(log N).
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
 * - nums[low] <= nums[mid] means that the left half is sorted from low to mid.
 * - nums[low] > nums[high] means that the right half is sorted from mid to high.
 */
package binary_search;

public class SearchInRotatedSortedArray {

    public static void main(String[] args) {
        System.out.println(search(new int[]{4,5,6,7,0,1,2}, 0));
    }

    public static int search(int[] nums, int target) {
        int n = nums.length, low = 0, high = n - 1;

        while (low <= high) {

            int mid = low + (high - low) / 2;

            if (nums[mid] == target) return mid;

            if (nums[low] <= nums[mid]) {

                if (nums[low] <= target && target < nums[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }

            } else {

                if (target <= nums[high] && target > nums[mid]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }

            }
        }
        return -1;
    }
}

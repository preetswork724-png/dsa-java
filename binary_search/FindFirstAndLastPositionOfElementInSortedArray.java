/**
 * Problem:
 * <Find First and Last Position of Element in Sorted Array>
 *
 * Link:
 * <https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/description/>
 *
 * Pattern:
 * <Core Binary Search>
 *
 * Brute Force Intuition:
 * - Linearly traverse the array from left to right to find the first position of target.
 * - Linearly traverse the array from right to left to find the last position of target.
 *
 * - Why it is inefficient?
 * - Brute force requires O(N) time to find the answer.
 * - When the array is large or when many queries are performed, the total time becomes O(Q * N).
 * - Inefficient compared to O(Q * log N) using binary search.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(1)
 *
 * Better and Optimal Approach (Used Below):
 * - Since the array is sorted, apply Binary search to directly locate the first position and last position of target.
 * - Maintain a candidate answer and shrink the search space accordingly.
 * - To find the first position:
 * - Store the potential candidate, reduce the search space by updating high = mid - 1.
 * - To find the last position:
 * - Store the potential candidate, reduce the search space by updating low = mid + 1.
 *
 * Why better approach is the optimal one?
 * - The array is sorted, so any comparison-based search for a boundary element (first occurrence / last occurrence) has a lower bound of Ω(log N).
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
 * - Only store the potential candidate / index when the element is exactly equal to target.
 */
package binary_search;

import java.util.Arrays;

public class FindFirstAndLastPositionOfElementInSortedArray {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(searchRange(new int[]{5,7,7,8,8,10}, 8)));
    }

    public static int[] searchRange(int[] nums, int target) {
        int n = nums.length;
        int[] ans = new int[2];

        ans[0] = findFirstPos(nums, target, n);
        ans[1] = findLastPos(nums, target, n);

        return ans;
    }

    public static int findFirstPos(int[] nums, int target, int n){

        int low = 0, high = n - 1, ans = -1;

        while(low <= high){
            int mid = low + (high - low) / 2;

            if(nums[mid] == target){
                ans = mid;
                high = mid - 1;
            }
            else if(nums[mid] < target){
                low = mid + 1;
            }
            else{
                high = mid - 1;
            }
        }
        return ans;
    }

    public static int findLastPos(int[] nums, int target, int n){

        int low = 0, high = n - 1, ans = -1;

        while(low <= high){
            int mid = low + (high - low) / 2;

            if(nums[mid] == target){
                ans = mid;
                low = mid + 1;
            }
            else if(nums[mid] < target){
                low = mid + 1;
            }
            else{
                high = mid - 1;
            }
        }
        return ans;
    }
}

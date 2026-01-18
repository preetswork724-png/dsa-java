/**
 * Problem:
 * <Minimum Size Subarray Sum>
 *
 * Link:
 * <https://leetcode.com/problems/minimum-size-subarray-sum/description/>
 *
 * Pattern:
 * <Sliding Window>
 *
 * Brute Force Intuition:
 * - Generate all possible subarrays.
 * - Take the minimum of lengths of all the valid subarrays.
 *
 * - Why it is inefficient?
 * - For each index, you recalculate the entire sum.
 * - A lot of repeated work.
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(1)
 *
 * Better Approach Intuition:
 * - Precompute prefix sums.
 * - Perform binary search on the prefix array.
 *
 * - Why it is still not optimal?
 * - We only need previous sums, not the entire prefix array.
 * - Extra memory is unnecessary.
 *
 * Time Complexity:
 * - O(N log N)
 * Space Complexity:
 * - O(N)
 *
 * Optimal Approach (Used Below):
 * - Maintain a window between left and right pointers where sum is greater than target.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - Shrink when sum >= target.
 * - Expand when sum < target.
 */
package sliding_window;

public class MinimumSizeSubarraySum {
    public static void main(String[] args) {
        System.out.println(minSubArrayLen(7, new int[]{2,3,1,2,4,3}));
    }

    public static int minSubArrayLen(int target, int[] nums) {
        int n = nums.length, left = 0, ans = Integer.MAX_VALUE, sum = 0;

        for(int right = 0; right < n; right++){
            sum += nums[right];

            while(sum >= target){
                ans = Math.min(ans, right - left + 1);
                sum -= nums[left];
                left++;
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }
}

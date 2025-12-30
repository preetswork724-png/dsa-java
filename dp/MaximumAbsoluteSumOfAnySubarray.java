/**
 * Problem:
 * <Maximum Absolute Sum of Any Subarray>
 *
 * Link:
 * <https://leetcode.com/problems/maximum-absolute-sum-of-any-subarray/description/>
 *
 * Pattern:
 * <Kadane's Algorithm>
 *
 * Brute Force Intuition:
 * - Try all the subarrays.
 * - Calculate the sum of the elements in them.
 * - Track the maximum of the absolute sum of the array.
 *
 * - Why it is inefficient?
 * - Because the sum is being calculated repeatedly for the subarrays.
 * - Far too slow for large arrays.
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(1)
 *
 * Better Approach Intuition:
 * - Instead of recomputing sum for each subarray from scratch :
 * - We build a prefix sum [].
 * - The core insight is that the absolute sum flips the negative value.
 * - And the maximum absolute difference can be obtained by taking the difference of maximum sum subarray and minimum sum subarray.
 * - So, we track them and return their difference.
 *
 * - Why it is still not optimal?
 * - Computing the complete prefix sum array requires extra memory while the maximum sum and the minimum sum can be maintained on the go.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Optimal Approach (Used Below):
 * - Instead of recomputing the prefix sum array, we :
 * - We track the maximum sum subarray so far.
 * - And the minimum sum subarray so far.
 * - Take the maximum of maximum so far and absolute of minimum so far.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - Note that the maxSoFar and minSoFar do not represent any indices in the prefix sum.
 * - Instead they represent the complete subarrays.
 * - When using prefix sums to compute the max absolute subarray sum : Always consider the implicit prefix[-1] = 0.
 * - Due to the above reason, the algorithm works for :
 * - All negative values.
 * - All positive values.
 * - Mixed values.
 */
package dp;

public class MaximumAbsoluteSumOfAnySubarray {
    public static void main(String[] args) {
        System.out.println(maxAbsoluteSum(new int[]{1,-3,2,3,-4}));
    }

    public static int maxAbsoluteSum(int[] nums) {
        int maxEnding = 0, maxSoFar = 0, minEnding = 0, minSoFar = 0;

        for(int x : nums){
            maxEnding = Math.max(x, maxEnding + x);
            maxSoFar = Math.max(maxSoFar, maxEnding);
            minEnding = Math.min(x, minEnding + x);
            minSoFar = Math.min(minSoFar, minEnding);
        }

        return Math.max(maxSoFar, Math.abs(minSoFar));
    }
}

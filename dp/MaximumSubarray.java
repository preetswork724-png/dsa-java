/**
 * Problem:
 * <Maximum Subarray>
 *
 * Link:
 * <https://leetcode.com/problems/maximum-subarray/description/>
 *
 * Pattern:
 * <Kadane's Algorithm>
 *
 * Brute Force Intuition:
 * - Try all the subarrays.
 * - Sum up all the elements in the subarray.
 * - Track the maximum sum.
 *
 * - Why it is inefficient?
 * - Recomputes sum repeatedly for some subarrays.
 * - Very slow for large arrays.
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(1)
 *
 * Better Approach Intuition:
 * - Build a prefix sum array.
 * - Just building the prefix sum array is not enough because prefix sum start from 0 to the last element.
 * - But the maximum sum can lie anywhere between (0...i).
 * - A subarray's sum is sum of all the elements from left to right.
 * - And talking in terms of prefixSum, it would be sum(l, r) = prefixSum[r] - prefixSum[l-1].
 * - In order to obtain the maximum sum, prefix[r] should be maximum and prefix[l-1] should be minimum.
 *
 * - Why it is still not optimal?
 * - We don't need to compute the entire prefix Sum array for finding the maximum sum.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Optimal Approach (Used Below):
 * - Instead of computing the complete prefixSum[].
 * - At every index, we make a decision whether it is better to extend the previous subarray or start a fresh subarray.
 * - We maintain currSoFar and maxSoFar.
 * - currSoFar : represents the best sum ending at the current index i.
 * - maxSoFar : represents the maximum sum seen so far.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - If previous sum becomes negative or useless, drop it.
 * - Otherwise, keep extending.
 * - Always store globally best seen so far.
 */
package dp;

public class MaximumSubarray {

    public static void main(String[] args) {
        System.out.println(maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
    }

    public static int maxSubArray(int[] nums) {
        int n = nums.length, currSoFar = nums[0], maxSoFar = nums[0];

        for(int i = 1; i < n ; i++){
            currSoFar = Math.max(nums[i], currSoFar + nums[i]);
            maxSoFar = Math.max(maxSoFar, currSoFar);
        }

        return maxSoFar;
    }
}

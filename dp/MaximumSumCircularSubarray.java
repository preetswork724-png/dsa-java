/**
 * Problem:
 * <Maximum Sum Circular Subarray>
 *
 * Link:
 * <https://leetcode.com/problems/maximum-sum-circular-subarray/description/>
 *
 * Pattern:
 * <Kadane's Algorithm>
 *
 * Brute Force Intuition:
 * - Try all the circular subarrays.
 * - Calculate the sum of the elements in them.
 * - Track the maximum sum.
 * - For every index i, run an inner loop from 0 to n.
 * - To avoid going out of bounds, calculate the idx = (i+len)%n
 *
 * - Why it is inefficient?
 * - Because the sum is being calculated repeatedly for all the circular subarrays.
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
 * - The core insight is :
 * - When the array wraps around, the max sum might get reduced. Therefore, we need to track the totalSum and the minimum sum. This is wrong.
 * - Wrapping does not reduce the maximum sum - instead it produces a bigger sum by ignoring the worst middle segment.
 * - Wrapping means we take suffix + prefix.
 * - totalSum - minimum subarray = maximum wrap-around circular subarray.
 * - Therefore we compute maximum sum subarray using normal Kadane's.
 * - Then compute the total sum of the array.
 * - Also, compute the minimum sum subarray.
 * - In a circular array, a wrapped array is equivalent to taking the whole array and removing one middle segment from it.
 * - So, the best circular sum = totalSum - minimum sum subarray.
 * - Final Answer = Math.max(maxSum, circular sum).
 *
 * - Why it is still not optimal?
 * - Computing the complete prefix sum array requires extra memory while the maximum sum and the minimum sum can be maintained on the go.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(1)
 *
 * Optimal Approach (Used Below):
 * - Instead of recomputing the prefix sum:
 * - We track the maximum sum subarray so far.
 * - And the minimum sum subarray so far.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - If maxSum becomes negative then all the elements in the arr are negative.
 */
package dp;

public class MaximumSumCircularSubarray {
    public static void main(String[] args) {
        System.out.println(maxSubarraySumCircular(new int[]{1,-2,3,-2}));
    }

    public static int maxSubarraySumCircular(int[] nums) {
        int totalSum = 0, maxSum = Integer.MIN_VALUE, minSum = Integer.MAX_VALUE, currMin = 0, currMax = 0;

        for(int num : nums){
            totalSum += num;

            currMax = Math.max(num, currMax + num);
            maxSum = Math.max(currMax, maxSum);

            currMin = Math.min(num, currMin + num);
            minSum = Math.min(currMin, minSum);
        }

        return (maxSum > 0) ? Math.max(maxSum, totalSum - minSum) : maxSum;
    }
}

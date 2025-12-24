/**
 * Problem:
 * <Max sum subarray of size k >
 *
 * Link:
 * <https://www.geeksforgeeks.org/problems/max-sum-subarray-of-size-k5313/1>
 *
 * Pattern:
 * <Sliding Window>
 *
 * Brute Force Intuition:
 * - Generate all possible subarrays of size k.
 * - For each index, calculate the sum of the next k elements.
 * - Take the maximum of all the sums.
 *
 * - Why it is inefficient?
 * - For each index, you recalculate the entire sum.
 * - A lot of repeated work.
 *
 * Time Complexity:
 * - O(N*K)
 * Space Complexity:
 * - O(1)
 *
 * Better Approach Intuition:
 * - Precompute prefix sums.
 * - Subarray sum can be calculated in O(1).
 * - Build a prefix sum array such that prefix[i] = prefix[i-1] + arr[i].
 * - For each subarray of size k, sum = prefix[i] - prefix[i-k].
 * - Track the maximum.
 *
 * - Why it is still not optimal?
 * - We only need previous sums, not the entire prefix array.
 * - Extra memory is unnecessary.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Optimal Approach (Used Below):
 * - Compute the sum of window of size k.
 * - Now just add the element ot the window by adding its sum.
 * - And remove the last element by subtracting its sum.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - Important to maintain a window of size k.
 */
package sliding_window;

public class MaxSumSubarrayOfSizeK {
    public static void main(String[] args) {
        System.out.println(maxSubarraySum(new int[]{100, 200, 300, 400}, 2));
    }

    public static int maxSubarraySum(int[] arr, int k) {
        int windowSum = 0, n = arr.length;

        for(int i = 0; i < k; i++) windowSum += arr[i];

        int maxSum = windowSum;

        for(int i = k; i < n; i++){
            windowSum += arr[i] - arr[i-k];
            maxSum = Math.max(maxSum, windowSum);
        }

        return maxSum;
    }
}

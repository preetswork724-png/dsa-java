/**
 * Problem:
 * <Smallest sum contiguous subarray>
 *
 * Link:
 * <https://www.geeksforgeeks.org/problems/smallest-sum-contiguous-subarray/1>
 *
 * Pattern:
 * <Kadane's Algorithm>
 *
 * Brute Force Intuition:
 * - Try all the subarrays.
 * - Sum up all the elements in the subarray.
 * - Track the minimum sum.
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
 * - But the minimum sum can lie anywhere between (0...i).
 * - A subarray's sum is sum of all the elements from left to right.
 * - And talking in terms of prefixSum, it would be sum(l, r) = prefixSum[r] - prefixSum[l-1].
 * - In order to obtain the maximum sum, prefix[r] should be minimum and prefix[l-1] should be maximum.
 *
 * - Why it is still not optimal?
 * - We don't need to compute the entire prefix Sum array for finding the minimum sum.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Optimal Approach (Used Below):
 * - Instead of computing the complete prefixSum[].
 * - At every index, we make a decision whether it is better to extend the previous subarray or start a fresh subarray.
 * - We maintain currSum and minSum.
 * - currSum : represents the best sum minimum ending at the current index i.
 * - minSum : represents the minimum sum seen so far.
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

public class SmallestSumContiguousSubarray {
    public static void main(String[] args) {
        int[] a  = {3,-4, 2,-3,-1, 7,-5};
        System.out.println(smallestSumSubarray(a, a.length));
    }

    static int smallestSumSubarray(int a[], int size) {
        int currSum = a[0], minSum = a[0];

        for(int i = 1; i < size; i++){
            currSum = Math.min(a[i], currSum + a[i]);
            minSum = Math.min(minSum, currSum);
        }

        return minSum;
    }
}

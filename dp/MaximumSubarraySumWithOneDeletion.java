/**
 * Problem:
 * <Maximum Subarray Sum with One Deletion>
 *
 * Link:
 * <https://leetcode.com/problems/maximum-subarray-sum-with-one-deletion/description/>
 *
 * Pattern:
 * <Kadane's Algorithm>
 *
 * Brute Force Intuition:
 * - Try all the subarrays.
 * - Calculate the sum of the subarray without deletion.
 * - Update the maximum sum.
 * - Then simulate deletion element by element.
 * - Take the max between sum before deletion and maximum sum after one deletion.
 *
 * - Why it is inefficient?
 * - Far too slow due to generation of all the subarrays.
 * - And checking sum after deletion per subarray.
 *
 * Time Complexity:
 * - O(N^3)
 * Space Complexity:
 * - O(1)
 *
 * Better Approach Intuition:
 * - Instead of recomputing every time, we precompute :
 * - If we delete arr[i], the best result is :
 * - best subarray ending just before i + best subarray ending at index after i.
 * - Compute Kadane's in forward direction for best sum subarray ending just before i.
 * - Compute Kadane's in backward direction for best sum subarray starting just after i.
 *
 * - Why it is still not optimal?
 * - We don't need to use extra space for computing the best subarray ending just before i and best subarray starting just after i.
 * - Feels like two Kadane's stuck together.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Optimal Approach (Used Below):
 * - At every index i there are two possibilities :
 * - Best subarray ending at i without deleting anything.
 * - Best subarray ending at i after already deleting exactly ine element.
 * - Two ways to delete an element :
 * - Delete current element.
 * - Already deleted before, extend previous.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - noDel[i] represents the world before deletion.
 * - oneDel represents the world after deletion.
 * - The transition from noDel to oneDel happens exactly when you decide to delete the current element.
 * - Therefore noDel must always be an option when updating oneDel.
 */
package dp;

public class MaximumSubarraySumWithOneDeletion {
    public static void main(String[] args) {
        System.out.println(maximumSum(new int[]{1,-2, 0, 3}));
    }

    public static int maximumSum(int[] arr) {
        int n = arr.length, oneDel = Integer.MAX_VALUE/2, noDel = arr[0], ans = arr[0];

        for(int i = 1; i < n ; i++){
            oneDel = Math.max(noDel, oneDel + arr[i]);
            noDel = Math.max(arr[i], noDel + arr[i]);
            ans = Math.max(ans, Math.max(oneDel, noDel));
        }

        return ans;
    }
}

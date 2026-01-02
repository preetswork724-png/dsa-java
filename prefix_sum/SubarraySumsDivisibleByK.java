/**
 * Problem:
 * <Subarray Sums Divisible By K>
 *
 * Link:
 * <https://leetcode.com/problems/subarray-sums-divisible-by-k/>
 *
 * Pattern:
 * <Prefix Sum>
 *
 * Brute Force Intuition:
 * - Try all the subarrays.
 * - Calculate the sum and check if it's divisible by k.
 *
 * - Why it is inefficient?
 * - Calculating sum for each element takes O(N) time.
 * - Very slow for large arrays.
 *
 * Time Complexity:
 * - O(N^3)
 * Space Complexity:
 * - O(1)
 *
 * Better Approach Intuition:
 * - Use prefix sums so any subarray sum can be computed in O(1) instead of recomputing.
 * - Build the prefix sum array.
 * - Check every pair (i, j) using prefix math.
 * - Removing the innermost summing loop.
 *
 * - Why it is still not optimal?
 * - Computing the arrays take extra memory.
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(N)
 *
 * Optimal Approach (Used Below):
 * - If two prefix sums have the remainder mod k, their difference (subarray between them) is divisible by k.
 * - So, we count frequencies of each remainder.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(k)
 *
 * Notes:
 * - Handle negative numbers :- (sum % k + k) % k
 */
package prefix_sum;

public class SubarraySumsDivisibleByK {
    public static void main(String[] args) {
        System.out.println(subarraysDivByK(new int[]{4,5,0,-2,-3,1}, 5));
    }

    public static int subarraysDivByK(int[] nums, int k) {
        int[] mod = new int[k];
        mod[0] = 1;

        int sum = 0, count = 0;

        for(int num : nums) {
            sum += num;
            int rem = (sum % k + k) % k;
            count += mod[rem];
            mod[rem]++;
        }

        return count;
    }
}

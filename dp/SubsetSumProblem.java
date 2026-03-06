/**
 * Problem:
 * <Subset Sum Problem>
 *
 * Link:
 * <https://www.geeksforgeeks.org/problems/subset-sum-problem-1611555638/1>
 *
 * Pattern:
 * <Bottom-Up Dp or KnapSack-Style Problem>
 *
 * Brute Force Intuition:
 * - For each element in the array, we have 2 choices:
 * - Either subtract it from the target sum.
 * - Or skip it.
 * - Base case:
 *   If sum == 0 -> return true.
 *   If index == n || sum < 0 return false.
 *
 * - Why it is inefficient?
 * - Re-computes already computed branches.
 * - Time complexity is exponential.
 *
 * Time Complexity:
 * - O(2^N)
 * Space Complexity:
 * - O(N) {Recursion Depth}
 *
 * Better Approach intuition:
 * - Memoization.
 * - Instead of re-computing the same branches, we store result.
 * - memo[index][remainingSum] :- represents whether it is possible to make sum from index onward.
 * - Why not just dp[sum]?
 * - Because the other elements matter.
 * - And we use the other elements using index.
 *
 * Time Complexity:
 * - O(1) {Single-pass over all the indices}
 * Space Complexity:
 * - O(1)
 *
 * Why this is still not optimal?
 * - Hashing adds overhead.
 *
 * Optimal Approach (Used below):
 * - Bottom-Up DP or KnapSack-style problem.
 * - The remaining sum only depends on the two choices:
 * - Either we skip the j or we decide to take the j.
 * - dp[i][j] = dp[i-1][j] || dp[i-1][j - arr[i-1]] if arr[i-1] <= j.
 * - dp[i][j] depends only on dp[i-1].
 * - Therefore, for each element in the array:
 * - We try to build the sum iteratively from right to left as the sum only depends on previous states.
 * - dp[j] = dp[j] || dp[j-num].
 *
 * Time Complexity:
 * - O(N * sum).
 * Space Complexity:
 * - O(sum)
 *
 * Notes:
 * - Whenever you see:
 * - Pick / not pick.
 * - Subset.
 * - Target Sum.
 * - This is 0/1 KnapSack Pattern
 */

package dp;

public class SubsetSumProblem {

    public static void main(String[] args) {
        System.out.println(isSubsetSum(new int[]{3, 34, 4, 12, 5, 2}, 9));
    }

    static Boolean isSubsetSum(int arr[], int sum) {
        boolean[] dp = new boolean[sum + 1];

        dp[0] = true;

        for(int num : arr){
            for(int j = sum; j >= num; j--){
                dp[j] = dp[j] || dp[j - num];
            }
        }

        return dp[sum];
    }
}

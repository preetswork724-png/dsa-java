/**
 * Problem:
 * <Unique Paths>
 *
 * Link:
 * <https://leetcode.com/problems/unique-paths/description/>
 *
 * Pattern:
 * <1D Dp>
 *
 * Brute Force Intuition:
 * - From each cell, we have two choices:
 * - Move right.
 * - Move down.
 * - Explore all possible paths using recursion.
 * - Increment count when reaching destination (m - 1, j - 1).
 * - Recursive structure:
 * - if(i < m) helper(n, m, i + 1, j).
 * - if(j < m) helper(n, m, i, j + 1).
 * - Update count in the base case.
 * - But update count globally.
 *
 * - Why it is inefficient?
 * - Exponential branching.
 * - Recomputes same (i, j) states repeatedly.
 * - Overlapping sub problems.
 *
 * Time Complexity:
 * - O(2^(M + N))
 * Space Complexity:
 * - O(M + N) {Recursion stack depth}
 *
 * Better Approach intuition:
 * - Use memoized recursion (Top-down dp).
 * - Let helper(i, j) = number of ways to reach destination from (i, j).
 * - If out of bounds -> return 0.
 * - If at destination -> return 1.
 * - Store result in memo[i][j].
 * - Recurrence:
 * - helper(i, j) = helper(i+1, j) + helper(i, j+1).
 *
 * Time Complexity:
 * - O(M * N)
 * Space Complexity:
 * - O(M * N) {Memo table} + O(M + N) {recursion depth}
 *
 * Why this is still not optimal?
 * - Extra recursion stack space.
 * - 2D memo table can be reduced.
 *
 * Optimal Approach (Used below):
 * - Bottom-Up DP.
 * - Observe recurrence.
 * - dp[i][j] = dp[i-1][j] + dp[i][j-1].
 * - First row and first column are all 1.
 * - Since current row only depends on previous row and left cell.
 * - Compress 2D DP into 1D dp.
 * - Let dp[j] = number of ways to reach column j in current row.
 * - For each row:
 * - dp[j] = dp[j] + dp[j-1].
 *
 * Time Complexity:
 * - O(M * N)
 * Space Complexity:
 * - O(N)
 *
 * Most Optimal Approach:
 * - Total moves = (m - 1) down + (n - 1) right.
 * - Total steps = m + n - 2.
 * - We just choose positions of (m - 1) downs.
 * - Answer = C(m + n - 2, m - 1).
 *
 * Time Complexity:
 * - O(min(M, N))
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - Brute force fails due to overlapping subproblems.
 * - Memoization converts exponential to polynomial.
 * - 1D DP works because transition depends only on:
 *   - top (previous row)
 *   - left (current row previous column)
 * - Combinatorics works only because:
 *   - No obstacles.
 *   - Fixed number of total moves.
 *   - Order of moves defines path.
 * - If obstacles are introduced → revert to DP.
 */
package dp;

public class UniquePaths {
    public static void main(String[] args) {
        System.out.println(uniquePaths(3, 7));
    }

    public static int uniquePaths(int m, int n) {
        int[] dp = new int[n];

        // Fill the 0th row with 1s

        for(int j = 0; j < n; j++){
            dp[j] = 1;
        }

        // Use the array row by row.

        for(int i = 1; i < m ; i++){
            for(int j = 1; j < n; j++){
                dp[j] = dp[j] + dp[j-1];
            }
        }

        return dp[n-1];
        // TC :- O(M * N)
        // SC :- O(N)
    }
}

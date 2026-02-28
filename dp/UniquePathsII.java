/**
 * Problem:
 * <Unique Paths II>
 *
 * Link:
 * <https://leetcode.com/problems/unique-paths-ii/description/>
 *
 * Pattern:
 * <1D Dp>
 *
 * Brute Force Intuition:
 * - From each cell, we have 2 choices:
 * - Move right.
 * - Move down.
 * - Base case:
 *   If out of bounds -> return 0.
 *   If obstacle -> return 0.
 *   If destination -> return 1 or increment count.
 * - Recursive structure:
 *   if(i < m - 1) helper(m, n, i + 1, j);
 *   if(j < n - 1) helper(m, n, i, j + 1);
 *
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
 * - If obstacle -> return 0;
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
 * - Recurrence stays ways(i + 1, j) + ways(i, j + 1) even though there are obstacles.
 * - dp[i][j] = dp[i-1][j] + dp[i][j-1].
 * - If obstacleGrid[0][0] != 1, then we set the dp[0] = 1.
 * - Since current row only depends on previous row and left cell and the obstacle.
 * - Compress 2D DP into 1D dp.
 * - Let dp[j] = number of ways to reach column j in current row.
 * - For each cell in the grid:
 * - if obstacleGrid[i][j] == 1 then dp[j] = 0.
 * - else if j > 0, dp[j] = dp[j] + dp[j-1].
 *
 * Time Complexity:
 * - O(M * N)
 * Space Complexity:
 * - O(N)
 *
 * Notes:
 * - Base cases:
 * - If the start of te grid or the destination is blocked, you can never reach the destination.
 * - If obstacles appear in the first row, all the cells to the right becomes unreachable.
 * - If obstacles appear in the first column, all the cells below it becomes unreachable.
 */

package dp;

public class UniquePathsII {

    public static void main(String[] args) {
        System.out.println(uniquePathsWithObstacles(new int[][]{{0,0,0}, {0,1,0}, {0,0,0}}));
    }

    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length, n = obstacleGrid[0].length;

        if(obstacleGrid[0][0] == 1 || obstacleGrid[m-1][n-1] == 1) return 0;

        int[] dp = new int[n];

        dp[0] = 1;

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){

                if(obstacleGrid[i][j] == 1){
                    dp[j] = 0;
                }
                else if(j > 0){
                    dp[j] = dp[j] + dp[j-1];
                }

            }
        }
        return dp[n-1];
        // TC :- O(M * N)
        // SC :- O(N)
    }

}

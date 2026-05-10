/**
 * Problem:
 * <Grid Game>
 *
 * Link:
 * <https://leetcode.com/problems/grid-game/description/>
 *
 * Pattern:
 * <Prefix Sum / Minimax Game Theory>
 *
 * Brute Force Intuition:
 * - The grid contains only 2 rows.
 * - Therefore, every valid path of the first robot is determined by one turning point.
 * - The first robot starts from (0,0), moves right on the top row,
 *   then moves down once and continues right on the bottom row.
 *
 * - Try every possible turning point i.
 * - Simulate the path of the first robot.
 * - Mark all visited cells as 0.
 * - Then compute the best possible path for the second robot.
 * - The second robot chooses the path with maximum points.
 * - The first robot chooses the path minimizing the second robot's score.
 *
 * - Why it is inefficient?
 * - For every turning point, we recompute the remaining sums again.
 * - This leads to repeated calculations of overlapping regions.
 * - Simulating every path separately becomes expensive.
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(1)
 *
 * Better Approach Intuition:
 * - Instead of recalculating the remaining sums every time:
 * - We observe that after the first robot turns at column i:
 *
 *   Destroyed Regions:
 *   - Top row from 0 → i
 *   - Bottom row from i → n-1
 *
 *   Remaining Regions:
 *   - Top-right region
 *   - Bottom-left region
 *
 * - The second robot can NEVER collect both regions fully.
 * - Because it can only move down once.
 *
 * - Therefore:
 *
 *   secondRobotScore =
 *   max(top-right remaining sum,
 *       bottom-left remaining sum)
 *
 * - Build prefix/suffix sums:
 * - Compute remaining top-right sum quickly.
 * - Compute bottom-left sum quickly.
 *
 * - Why it is still not optimal?
 * - Maintaining extra prefix/suffix arrays requires additional space.
 * - We can track both sums dynamically while traversing once.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Optimal Approach (Used Below):
 * - Instead of storing prefix/suffix arrays:
 * - Maintain:
 *
 *   topSum    -> remaining top-right region
 *   bottomSum -> remaining bottom-left region
 *
 * - Initially:
 *   topSum = total sum of top row
 *   bottomSum = 0
 *
 * - For every column i:
 *
 *   1. Remove current top cell from topSum
 *      because first robot destroys it.
 *
 *   2. Compute:
 *
 *      secondRobot =
 *      max(topSum, bottomSum)
 *
 *   3. Minimize this value across all turning points.
 *
 *   4. Add current bottom cell into bottomSum
 *      for future turning points.
 *
 * - We do NOT physically zero out cells.
 * - We only track the sums of the two surviving regions.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - The first robot is NOT trying to maximize its own score.
 * - The first robot is trying to minimize the BEST possible score
 *   achievable by the second robot.
 *
 * - This is a classic minimax problem:
 *
 *   minimize(
 *      maximum gain of opponent
 *   )
 *
 * - Important:
 * - bottomSum is updated AFTER calculating the answer for current i.
 * - Because grid[1][i] is still destroyed at current turning point.
 *
 * - Common Mistake:
 * - Using:
 *      int n = grid.length;
 *   which gives number of rows (=2).
 *
 * - Correct:
 *      int n = grid[0].length;
 *   because we need number of columns.
 */

package dp.Kadane;

public class GridGame {
    public long gridGame(int[][] grid) {

        long topSum = 0, bottomSum = 0;
        int n = grid[0].length;

        for(int score : grid[0]){
            topSum += score;
        }

        long ans = Long.MAX_VALUE;

        for(int i = 0; i < n; i++){

            topSum -= grid[0][i];

            long secondRobotPoints = Math.max(topSum, bottomSum);

            ans = Math.min(ans, secondRobotPoints);

            bottomSum += grid[1][i];
        }
        return ans;
    }
}

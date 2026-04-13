/**
 * Problem:
 * <Rotting Oranges>
 *
 * Link:
 * <https://leetcode.com/problems/rotting-oranges/>
 *
 * Pattern:
 * <Multi-Source BFS / Grid Traversal>
 *
 *
 * Brute Force Intuition:
 * - Start from each rotten orange and try to spread rot using DFS.
 * - For every rotten orange:
 *     - Explore all possible paths and simulate rotting.
 * - Track minimum time required to rot all oranges.
 *
 * - Why it is inefficient?
 * - Repeated traversal of same cells.
 * - Does not model simultaneous spreading correctly.
 * - DFS explores paths sequentially, while rotting happens in parallel.
 *
 * Time Complexity:
 * - Exponential (due to repeated DFS paths)
 *
 * Space Complexity:
 * - O(N * M) recursion stack
 *
 *
 * Brute Force Bugs (common mistakes):
 *
 * BUG 1 → Treating spread as sequential instead of parallel:
 * - Rotting happens simultaneously from all sources ❌
 *
 * BUG 2 → Revisiting cells:
 * - Not marking visited properly → infinite loops / TLE
 *
 * BUG 3 → Incorrect time calculation:
 * - Incrementing time per node instead of per level
 *
 *
 * Better Approach Intuition (BFS):
 * - Observation:
 *   Rot spreads level-by-level (like wave expansion).
 *
 * - Use BFS to simulate this spread:
 *   - Treat all rotten oranges as initial sources.
 *   - Push all rotten oranges into queue at time = 0.
 *
 * - For each step:
 *   - Process current rotten oranges.
 *   - Infect adjacent fresh oranges.
 *   - Push them into queue with time + 1.
 *
 * - Track remaining fresh oranges.
 *
 * - Why it is better?
 * - Models simultaneous spread correctly.
 * - Avoids repeated work.
 *
 * - Why it is still not optimal?
 * - Uses extra visited[][] space.
 *
 * Time Complexity:
 * - O(N * M)
 *
 * Space Complexity:
 * - O(N * M) (queue + visited array)
 *
 *
 * Optimal Approach (In-place BFS without visited[][]):
 * - Observation:
 * - No need for visited array → grid itself can be used.
 *
 * - Define:
 * - Queue stores (row, col, time)
 *
 * - Initialization:
 * - Push all rotten oranges (value = 2) into queue with time = 0
 * - Count total fresh oranges
 *
 * - BFS Traversal:
 * - While queue is not empty:
 *     - Pop current cell
 *     - Update max time
 *     - Check 4 directions:
 *         - If fresh orange (grid == 1):
 *             - Mark it rotten (grid = 2)
 *             - Decrease fresh count
 *             - Push into queue with time + 1
 *
 * - Final Check:
 * - If fresh > 0 → return -1
 * - Else → return total time
 *
 * - Why it is optimal?
 * - Each cell processed once
 * - No extra visited array
 * - Space optimized using input grid
 *
 * Time Complexity:
 * - O(N * M)
 *
 * Space Complexity:
 * - O(N * M) (queue)
 *
 *
 * Key Insight:
 * - This is a Multi-Source BFS problem.
 * - All rotten oranges act as simultaneous sources.
 * - Time = maximum level reached in BFS.
 *
 *
 * Notes:
 * - Always mark visited at insertion time (enqueue).
 * - Do NOT increment time per node → use level/time tracking.
 * - Use grid itself instead of visited[][] for optimization.
 *
 * - Common mistakes:
 * - Using both grid and visited inconsistently ❌
 * - Not marking cell immediately → duplicates in queue ❌
 * - Incrementing time per node instead of per level ❌
 * - Not checking remaining fresh oranges ❌
 *
 * - Mental model:
 * - "Rot spreads like a wave from multiple sources simultaneously."
 *
 *
 * Edge cases:
 * - No fresh oranges → return 0
 * - Fresh oranges but no rotten → return -1
 * - Isolated fresh oranges → return -1
 *
 *
 * Key takeaway:
 * - Classic Multi-Source BFS.
 * - Replace visited[][] with in-place grid updates.
 * - BFS level = time → NOT number of nodes processed.
 */

package graphs;

import java.util.Queue;
import java.util.LinkedList;

class Pair {
    int row, col, time = 0;

    public Pair(int row, int col, int time) {
        this.row = row;
        this.col = col;
        this.time = time;
    }
}

public class RottingOranges {
    public int orangesRotting(int[][] grid) {
        int n = grid.length, m = grid[0].length;

        Queue<Pair> q = new LinkedList<>();
        int fresh = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 2) {
                    q.offer(new Pair(i, j, 0));
                } else if (grid[i][j] == 1) {
                    fresh++;
                }
            }
        }

        int minTime = 0;

        while (!q.isEmpty()) {
            Pair front = q.poll();

            int row = front.row, col = front.col, time = front.time;
            minTime = Math.max(minTime, time);

            if (row + 1 < n && grid[row + 1][col] == 1) {
                grid[row + 1][col] = 2;
                q.offer(new Pair(row + 1, col, time + 1));
                fresh--;
            }

            if (row - 1 >= 0 && grid[row - 1][col] == 1) {
                grid[row - 1][col] = 2;
                q.offer(new Pair(row - 1, col, time + 1));
                fresh--;
            }

            if (col + 1 < m && grid[row][col + 1] == 1) {
                grid[row][col + 1] = 2;
                q.offer(new Pair(row, col + 1, time + 1));
                fresh--;
            }

            if (col - 1 >= 0 && grid[row][col - 1] == 1) {
                grid[row][col - 1] = 2;
                q.offer(new Pair(row, col - 1, time + 1));
                fresh--;
            }
        }
        return fresh == 0 ? minTime : -1;
    }
}

/**
 * Problem:
 * Brick Wall
 * (Find the minimum number of bricks a vertical line can cross)
 *
 * Link:
 * https://leetcode.com/problems/brick-wall/
 *
 * Pattern:
 * Hash Map — Edge / Gap Frequency Count
 *
 * Brute Force Intuition:
 * - Try every possible vertical position x (1 to totalWidth - 1).
 * - For each position x, count how many rows have a brick edge at x.
 * - Bricks crossed at x = wall.size() - edgesAt(x).
 * - Track the minimum bricks crossed across all positions.
 * - Why it is inefficient?
 * - For each of the O(totalWidth) positions, scanning all rows costs
 *   O(N * M) where N = rows, M = bricks per row.
 * - Total: O(totalWidth * N * M) — far too slow for large walls.
 *
 * Time Complexity:
 * - O(totalWidth * N * M) {Scan all rows at every possible x position}
 * Space Complexity:
 * - O(1)
 *
 * Optimal Approach (Used below):
 * - Key Insight: instead of counting bricks crossed, count edges NOT crossed.
 *   - Bricks crossed at position x = wall.size() - edgesAt(x).
 *   - Minimizing bricks crossed = maximizing edges at any single position.
 * - Use a HashMap to count how many rows have a brick edge at each
 *   cumulative width position.
 * - For each row, compute prefix sums of brick widths (excluding the
 *   last brick — the right wall boundary is never a valid cut line).
 * - Each prefix sum value is an edge position — increment its count.
 * - Answer = wall.size() - max frequency across all edge positions.
 *
 * Time Complexity:
 * - O(N * M) {One pass over every brick in every row}
 * Space Complexity:
 * - O(N) {At most N * M unique edge positions stored in the map,
 *          bounded by total number of bricks}
 *
 * Notes:
 * - The core reframe is the entire insight:
 *   - Wrong direction : minimize bricks crossed -> scan every x position.
 *   - Right direction : maximize edges aligned  -> one pass, count frequencies.
 *   - Both compute the same answer; only one is efficient.
 * - The last brick in each row must be excluded from prefix sums:
 *   - The right boundary of the wall is at the same position for every row.
 *   - Counting it inflates the edge frequency at totalWidth by wall.size(),
 *     making the answer always 0 — silent wrong answer.
 *   - Loop runs i < row.size() - 1, NOT i < row.size().
 * - If no edges exist at any interior position (e.g. all rows are one
 *   solid brick), the map stays empty, maxEdges stays 0, and the answer
 *   is correctly wall.size() — every row must be crossed.
 * - The prefix sum resets to 0 at the start of each row — do not carry
 *   it across rows. Each row's edge positions are independent.
 * - Do NOT store absolute brick widths in the map — store cumulative
 *   prefix sums. Two bricks of width 3 in different rows only share an
 *   edge if they start at the same cumulative offset, not just the same width.
 */

package hashing;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BrickWall {
    public int leastBricks(List<List<Integer>> wall) {

        Map<Integer, Integer> map = new HashMap<>();

        for (List<Integer> row : wall) {

            int sum = 0;

            for (int i = 0; i < row.size() - 1; i++) {
                sum += row.get(i);
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
        }

        int maxEdges = 0;

        for (int val : map.values()) {
            maxEdges = Math.max(maxEdges, val);
        }

        return wall.size() - maxEdges;
    }
}

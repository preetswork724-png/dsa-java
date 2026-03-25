/**
 * Problem:
 * <Bottom View of Binary Tree>
 *
 * Link:
 * <https://www.geeksforgeeks.org/problems/bottom-view-of-binary-tree/1>
 *
 * Pattern:
 * <BFS/DFS + Horizontal Distance>
 *
 * Brute Force Intuition:
 * - Traverse the entire tree using DFS.
 * - Store nodes as (row, col, value).
 * - Use:
 *  - TreeMap<col, TreeMap<row, List<Integer>>
 *  - TreeMap col -> To maintain left to right order.
 *  - TreeMap row -> To maintain top to bottom order.
 * - After traversal:
 *  - Iterate over the map column-wise.
 *  - For each column:
 *    - Add the value of the node with the maximum row.
 *
 * - Why it is inefficient?
 * - Unnecessarily stores all the nodes.
 * - Using complex data structure increases the overhead.
 *
 * Time Complexity:
 * - O(N log N)
 * Space Complexity:
 * - O(N)
 *
 * Better Approach Intuition:
 * - Traverse using BFS.
 * - Nodes are processed level by level.
 * - Use:
 *  - TreeMap<col, List<row, value>>.
 * - Traverse using BFS with (col, row, value).
 * - Store all the nodes.
 * - After complete traversal:
 *  - Iterate over the map column-wise:
 *  - For each column:
 *   - Store the value of the node with the maximum row in the final list.
 *
 * - Why it is better?
 * - Nodes are processed level by level, so we don't need to explicitly maintain the rows in a sorted order.
 *
 * Why it is still not optimal?
 * - Still stores all the nodes.
 *
 * Time Complexity:
 * - O(N log N)
 * Space Complexity:
 * - O(N)
 *
 * Optimal Approach (Used Below):
 * - Use BFS (level order traversal).
 * - Traverse level by level, so that the row order is maintained.
 * - Use:
 *  - TreeMap<col, value>
 * - Instead of storing all the nodes, we:
 *  - track the most recent node.
 * - Initialize queue storing (node, col).
 * - Start with root at col = 0.
 * - Traverse using BFS
 * - For each node:
 *  - Update map[col] = node.val
 *    (overwrite previous states)
 * - After traversal:
 *  - Iterate over the map.
 *  - Add values to the result.
 *
 * Time Complexity:
 * - O(N log N) {Insertions into the map}
 * Space Complexity:
 * - O(N)
 *
 * Notes:
 * - Horizontal distance (column):
 *   - root → 0
 *   - left → col - 1
 *   - right → col + 1
 *
 * - Why BFS works:
 *   - BFS processes nodes level by level.
 *   - Deeper nodes overwrite earlier ones.
 *
 * - Why overwrite works:
 *   - Bottom view requires last visible node.
 *
 * - Difference from Top View:
 *   - Top View → store first node per column.
 *   - Bottom View → store last node per column.
 *
 * - Common mistakes:
 *   - Using DFS without tracking row properly.
 *   - Overcomplicating with nested maps.
 *   - Not overwriting values in map.
 *
 * - Mental model:
 *   - Imagine looking from bottom:
 *     - Last node in each vertical line survives.
 *
 * - Edge cases:
 *   - Single node → return [root]
 *   - Skewed tree → all nodes visible
 *
 * - Key takeaway:
 *   - This is a "last occurrence per column" problem.
 *   - BFS + overwrite = optimal pattern.
 */

package trees._8_Views;

import trees.Node;

import java.util.Map;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

public class BottomViewOfBinaryTree {
    public ArrayList<Integer> bottomView(Node root) {
        Map<Integer, Integer> map = new TreeMap<>();
        Queue<Object[]> queue = new LinkedList<>();
        queue.offer(new Object[]{root, 0});

        while (!queue.isEmpty()) {

            Object[] curr = queue.poll();
            Node node = (Node) curr[0];
            int col = (int) curr[1];

            map.put(col, node.data);

            if (node.left != null) {
                queue.offer(new Object[]{node.left, col - 1});
            }

            if (node.right != null) {
                queue.offer(new Object[]{node.right, col + 1});
            }
        }
        return new ArrayList<>(map.values());
    }
}

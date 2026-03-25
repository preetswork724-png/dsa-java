/**
 * Problem:
 * <Top View of Binary Tree>
 *
 * Link:
 * <https://www.geeksforgeeks.org/problems/top-view-of-binary-tree/1>
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
 *  - Add the value of the node with the smallest row to the final result.
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
 *   - Store the value of the node with the smallest row in the final list.
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
 *  - Check if that column is present in the map:
 *    - If yes:
 *      - Then the top most node for that column line is already stored.
 *    - Else:
 *      - Store (col, node's value) in the map.
 *      - We don't need to store the row explicitly as nodes are processed level by level.
 * - After traversal:
 *  - Iterate over the map.
 *  - Append all the value stored to the corresponding column.
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
 * - Why BFS works perfectly here:
 *   - BFS processes nodes level by level.
 *   - First node seen at a column is always topmost.
 *
 * - Why DFS is tricky:
 *   - DFS may visit deeper nodes before shallow ones.
 *   - Requires explicit row comparison.
 *
 * - Data structure choice:
 *   - TreeMap:
 *     - Keeps columns sorted (left → right).
 *
 * - Common mistakes:
 *   - Using DFS without tracking row properly.
 *   - Over-storing nodes like vertical traversal.
 *   - Forgetting to check if column already exists.
 *
 * - Mental model:
 *   - Imagine looking from top:
 *     - Only first visible node in each vertical line survives.
 *
 * - Edge cases:
 *   - Single node → return [root]
 *   - Skewed tree → all nodes visible
 *
 * - Key takeaway:
 *   - This is a "first occurrence per column" problem.
 *   - BFS + first insertion = optimal pattern.
 */

package trees._8_Views;

import trees.Node;

import java.util.Map;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

public class TopViewOfBinaryTree {
    public ArrayList<Integer> topView(Node root) {
        Map<Integer, Integer> map = new TreeMap<>();
        Queue<Object[]> queue = new LinkedList<>();
        queue.offer(new Object[]{root, 0, 0});

        while (!queue.isEmpty()) {

            Object[] curr = queue.poll();
            Node node = (Node) curr[0];
            int row = (int) curr[1];
            int col = (int) curr[2];

            if (!map.containsKey(col)) {
                map.put(col, node.data);
            }

            if (node.left != null) {
                queue.offer(new Object[]{node.left, row + 1, col - 1});
            }

            if (node.right != null) {
                queue.offer(new Object[]{node.right, row + 1, col + 1});
            }
        }
        return new ArrayList<>(map.values());
    }
}

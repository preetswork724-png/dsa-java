/**
 * Problem:
 * <Amount of Time for Binary Tree to Be Infected>
 *
 * Link:
 * <https://leetcode.com/problems/amount-of-time-for-binary-tree-to-be-infected/description/>
 *
 * Pattern:
 * <Tree -> Graph Conversion + BFS>
 *
 * Brute Force Intuition:
 * - For every node, calculate the time taken to infect entire tree if started from that node.
 * - Use DFS to simulate infection spread.
 * - Track time taken for each possibility.
 *
 * - Why it is inefficient?
 * - Repeated computation of infection spread.
 * - Repeated traversal of subtrees.
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(H)
 *
 * Better Approach Intuition:
 * - Use DFS to calculate distance and propagate infection time.
 * - At each node:
 *  - Compute height of left and right subtree.
 *  - Track distance from target node.
 * - Combine subtree heights and distances to compute max infection time.
 *
 * - Why it is better?
 * - Single traversal with calculated work.
 * - Avoids repeated work.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(H)
 *
 * Optimal Approach (Used Below):
 * - Use parent map + BFS.
 * - Convert tree into graph using parent map.
 * - Infection spreads like BFS (level by level).
 * - Steps:
 *  - 1. Find target(start) node.
 *  - 2. Build parent map.
 *      - Store parent of each node.
 *  - 3. BFS from target node:
 *      - Use queue.
 *      - Use visited set.
 *  - 4. Traverse level by level.
 *      - Each level = 1 minute.
 *      - Spread infection to:
 *          - left child.
 *          - right child.
 *          - parent
 *  - 5. Count levels until all levels are infected.
 * - Infection spreads simultaneously.
 * - BFS naturally models "time" (level = minute).
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Notes:
 * - Tree does not allow upward traversal → parent map required.
 *
 * - Why parent map is needed:
 *   - Infection spreads to parent as well.
 *
 * - Why BFS is used:
 *   - Models simultaneous spread.
 *   - Each level represents 1 minute.
 *
 * - Why visited set is required:
 *   - Prevents revisiting nodes (avoids infinite loops).
 *
 * - Difference from Distance K problem:
 *   - Distance K → stop at level k.
 *   - Infection → traverse entire tree and count levels.
 *
 * - Common mistakes:
 *   - Not marking visited nodes.
 *   - Forgetting parent traversal.
 *   - Incorrect minute calculation (off by one).
 *   - Incrementing time without checking spread.
 *
 * - Mental model:
 *   - Fire spreading in a tree.
 *   - Each minute → spreads to adjacent nodes.
 *
 * - Edge cases:
 *   - Single node → answer = 0.
 *   - Start node is root.
 *   - Skewed tree → behaves like linked list.
 *
 * - Key takeaway:
 *   - Multi-directional traversal = Graph problem.
 *   - BFS = best choice for shortest time / spread problems.
 */

package trees._9_Advanced;

import trees.TreeNode;

import java.util.Queue;
import java.util.ArrayDeque;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

public class AmountOfTimeForBinaryTreeToBeInfected {
    public int amountOfTime(TreeNode root, int start) {

        if (root == null || start < 1) return 0;

        TreeNode infected = dfs(root, start);
        int minutes = 0;

        Map<TreeNode, TreeNode> parent = new HashMap<>();
        buildMap(root, null, parent);

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(infected);

        Set<TreeNode> visited = new HashSet<>();
        visited.add(infected);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            boolean spread = false;

            for (int i = 0; i < levelSize; i++) {

                TreeNode curr = queue.poll();

                if (curr.left != null && !visited.contains(curr.left)) {
                    queue.offer(curr.left);
                    visited.add(curr.left);
                    spread = true;
                }

                if (curr.right != null && !visited.contains(curr.right)) {
                    queue.offer(curr.right);
                    visited.add(curr.right);
                    spread = true;
                }

                if (parent.get(curr) != null && !visited.contains(parent.get(curr))) {
                    queue.offer(parent.get(curr));
                    visited.add(parent.get(curr));
                    spread = true;
                }
            }
            if (spread) minutes++;
        }
        return minutes;
    }

    public TreeNode dfs(TreeNode root, int start) {

        if (root == null) return null;

        if (root.val == start) return root;

        TreeNode left = dfs(root.left, start);

        if (left != null) return left;

        return dfs(root.right, start);
    }

    public void buildMap(TreeNode node, TreeNode par, Map<TreeNode, TreeNode> parent) {

        if (node == null) return;

        parent.put(node, par);
        buildMap(node.left, node, parent);
        buildMap(node.right, node, parent);
    }
}

/**
 * Problem:
 * <All Nodes Distance K in Binary Tree>
 *
 * Link:
 * <https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/description/>
 *
 * Pattern:
 * <Tree -> Graph Conversion + BFS>
 *
 * Brute Force Intuition:
 * - Treat each node independently and compute distance from the target.
 * - Find path from root -> target.
 * - For each node in the path:
 *  - Calculate its distance from the target.
 *  - Explore subtree for remaining distance (k - dist).
 *  - Avoid revisiting nodes (block parent direction).
 *
 * - Why it is inefficient?
 * - Repeated traversal of subtrees.
 * - Complex blocking logic.
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(N)
 *
 * Better Approach Intuition:
 * - Parent Map + DFS
 * - Convert tree -> graph using parent references.
 * - child <-> parent <-> child.
 * - Build parent map using BFS / DFS.
 * - Start DFS from target.
 *  - Traverse left, right and upward.
 *  - Track visited nodes.
 *  - Stop when distance == k.
 *
 * - Why it is better?
 * - Covers all directions.
 * - Eliminates need for path tracking.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Optimal Approach (Used Below):
 * - Use parent map + BFS.
 * - Distance in tree = shortest path in graph (graph problem).
 * - BFS naturally explores level by level.
 * - Steps:
 * - 1. Build parent Map.
 * - 2. BFS from target node:
 *      - Use queue.
 *      - Use Set to track visited nodes.
 * - 3. Traverse level by level.
 *      - Level 0 -> target.
 *      - Level 1 -> required nodes.
 * - 4. Stop when distance == k:
 *      - Collect all nodes in queue.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Notes:
 * - Tree does not have parent pointers → must be created
 *
 * - Why parent map is needed:
 *   - To allow upward traversal (towards ancestors)
 *
 * - Why BFS is used:
 *   - Guarantees shortest distance (level-based traversal)
 *
 * - Why visited set is required:
 *   - Prevents revisiting nodes (avoids infinite loop)
 *
 * - Difference from Tree Traversal:
 *   - Normal traversal → top-down
 *   - This problem → multi-directional (graph-like)
 *
 * - Common mistakes:
 *   - Not building parent map
 *   - Forgetting visited set → infinite loop
 *   - Using DFS without proper distance control
 *   - Thinking in terms of coordinates (incorrect)
 *
 * - Mental model:
 *   - Convert tree into graph
 *   - Perform BFS from target node
 *   - Stop at level k
 *
 * - Edge cases:
 *   - k = 0 → return [target]
 *   - Target not present → return []
 *   - Tree with single node
 *
 * - Key takeaway:
 *   - Tree + distance problem = Graph BFS problem
 *   - Always think in terms of shortest path
 */

package trees._9_Advanced;

import trees.TreeNode;

import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

public class AllNodesDistanceKInBinaryTree {
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        List<Integer> res = new ArrayList<>();
        Map<TreeNode, TreeNode> parent = new HashMap<>();
        buildParent(root, null, parent);
        Set<TreeNode> visited = new HashSet<>();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(target);
        visited.add(target);
        int dist = 0;

        while (!queue.isEmpty()) {

            int size = queue.size();
            if (dist == k) break;

            for (int i = 0; i < size; i++) {

                TreeNode curr = queue.poll();

                if (curr.left != null && !visited.contains(curr.left)) {
                    queue.offer(curr.left);
                    visited.add(curr.left);
                }

                if (curr.right != null && !visited.contains(curr.right)) {
                    queue.offer(curr.right);
                    visited.add(curr.right);
                }

                if (parent.get(curr) != null && !visited.contains(parent.get(curr))) {
                    queue.offer(parent.get(curr));
                    visited.add(parent.get(curr));
                }
            }
            dist++;
        }

        while (!queue.isEmpty()) {
            res.add(queue.poll().val);
        }

        return res;
    }

    public void buildParent(TreeNode node, TreeNode par, Map<TreeNode, TreeNode> parent) {

        if (node == null) return;

        parent.put(node, par);

        buildParent(node.left, node, parent);
        buildParent(node.right, node, parent);
    }
}

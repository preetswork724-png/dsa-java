/**
 * Problem:
 * <Smallest Subtree with all the Deepest Nodes>
 *
 * Link:
 * <https://leetcode.com/problems/smallest-subtree-with-all-the-deepest-nodes/>
 *
 * Pattern:
 * <LCA + Post-order DFS>
 *
 * Brute Force Intuition:
 * - First, find maximum depth of the tree.
 * - For every node:
 *      Check whether all deepest nodes
 *      belong inside its subtree.
 * - The deepest such node is the answer.
 *
 * - Why it is inefficient?
 * - Every subtree gets traversed repeatedly.
 * - Lots of repeated checks.
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(N)
 *
 *
 * Better Approach Intuition:
 * - Use BFS to collect all deepest nodes.
 * - Store them in a list.
 * - Then reduce:
 *      Find LCA of all deepest nodes.
 *
 * - Why it is still not optimal?
 * - LCA gets computed multiple times.
 * - Repeated DFS traversals.
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(N)
 *
 *
 * Optimal Approach (Used Below):
 * - Use post-order DFS.
 *
 * - For every node:
 *      Get:
 *          left subtree depth
 *          right subtree depth
 *
 * - Decision:
 *      If left > right:
 *          deepest nodes are in left subtree
 *
 *      If right > left:
 *          deepest nodes are in right subtree
 *
 *      If equal:
 *          deepest nodes are split
 *          current node becomes answer
 *
 * - Return:
 *      Pair(depth, node)
 *
 *
 * Time Complexity:
 * - O(N)
 *
 * Space Complexity:
 * - O(H) {Recursion stack}
 *
 *
 * Notes:
 * - Post-order is important:
 *      decide after knowing both subtrees
 *
 * - Equal depth:
 *      current node is LCA
 *
 * - Greater depth:
 *      propagate deeper subtree upward
 *
 * - Final answer:
 *      dfs(root).node
 *
 */

package trees._7_LCA;

class Pair {
    int depth;
    TreeNode node;

    public Pair(int depth, TreeNode node) {
        this.depth = depth;
        this.node = node;
    }
}

public class SmallestSubtreeWithAllTheDeepestNodes {
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        return dfs(root).node;
    }

    public Pair dfs(TreeNode root) {

        if (root == null) return new Pair(0, null);

        Pair left = dfs(root.left);
        Pair right = dfs(root.right);

        if (left.depth > right.depth) {
            return new Pair(left.depth + 1, left.node);
        }

        if (left.depth < right.depth) {
            return new Pair(right.depth + 1, right.node);
        }
        return new Pair(left.depth + 1, root);
    }
}

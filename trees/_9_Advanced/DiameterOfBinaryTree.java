/**
 * Problem:
 * <Diameter of Binary Tree>
 *
 * Link:
 * <https://leetcode.com/problems/diameter-of-binary-tree/description/>
 *
 * Pattern:
 * <Tree DP / DFS (Postorder)>
 *
 * Brute Force Intuition:
 * - For every node:
 *   - Compute the height of the left subtree.
 *   - Compute the height of the right subtree.
 *   - Compute diameter passing through that node i.e current diameter = left + right.
 *   - Recursively compute diameter for left subtree.
 *   - Recursively compute diameter for right subtree.
 *   - Take maximum over all.
 *
 * - Why it is inefficient?
 * - Height function is called for each node.
 * - Same subtrees are computed multiple times.
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(H)
 *
 * Better Approach Intuition:
 * - Use early comparison while computing diameter.
 * - Reduce redundant calculations by avoiding unnecessary recursion.
 *
 * - Why it is still not optimal?
 * - Still recomputes height for each node.
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(H)
 *
 * Optimal Approach (Used Below):
 * - Use DFS (Postorder Traversal).
 * - Combine height calculation and diameter update in one recursion.
 * - At each node:
 *   - Get height of the left subtree.
 *   - Get height of the right subtree.
 *   - Update diameter = max(diameter, left + right).
 *   - Return height = 1 + max(left, right).
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(H)
 *
 * Notes:
 * - Diameter is defined as the number of edges between 2 nodes.
 * - For each node:
 *   diameter through node = leftHeight + rightHeight.
 * - Global variable is used to store maximum diameter.
 * - Postorder traversal is required (Left -> Right -> Node).
 * - This is a classic Tree DP problem:
 *   - Return height.
 *   - Update global answer.
 */

package trees._9_Advanced;

import trees.TreeNode;

public class DiameterOfBinaryTree {
    int diameter = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        helper(root);
        return diameter;
    }

    public int helper(TreeNode root) {
        if (root == null) return 0;

        int left = helper(root.left);
        int right = helper(root.right);

        diameter = Math.max(diameter, left + right);
        return 1 + Math.max(left, right);
    }
}

/**
 * Problem:
 * <Validate Binary Search Tree>
 *
 * Link:
 * <https://leetcode.com/problems/validate-binary-search-tree/description/>
 *
 * Pattern:
 * <BST>
 *
 * Brute Force Intuition:
 * - For every node:
 * - Find the maximum in the left subtree.
 * - Find the minimum in the right subtree.
 * - Check:
 *   - max(left) < node.val < min(right)
 * - Recursively check for each node.
 *
 * - Why it is inefficient?
 * - For every node, we traverse its entire subtree again.
 * - Repeated computations of min / max for subtrees.
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(H)
 *
 * Better Approach Intuition:
 * - Perform inorder traversal of the tree.
 * - Store values in a list.
 * - Check if the list is strictly increasing.
 *
 * - Why it is still not optimal?
 * - Uses extra space to store the values of the nodes.
 * - Two-pass approach.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Optimal Approach (Used Below):
 * - Use DFS traversal with (min, max) constraints.
 * - For every node:
 *   - Ensure node.val lies within (min, max) range.
 *   - Recursively:
 *   - left(min, node.val).
 *   - right(node.val, max).
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(H)
 *
 * Notes:
 * - BST validity is a global constraint, not local.
 * - Parent-child comparison is not enough.
 */

package trees._6_BST;

import trees.TreeNode;

public class ValidateBinarySearchTree {
    public boolean isValidBST(TreeNode root) {
        return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean validate(TreeNode node, long min, long max) {

        if (node == null) return true;

        if (node.val <= min || node.val >= max) return false;

        return validate(node.left, min, node.val) &&
                validate(node.right, node.val, max);
    }
}

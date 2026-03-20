/**
 * Problem:
 * <Balanced Binary Tree>
 *
 * Link:
 * <https://leetcode.com/problems/balanced-binary-tree/description/>
 *
 * Pattern:
 * <Tree DP / DFS (Postorder)>
 *
 * Brute Force Intuition:
 * - For every node:
 *   - Calculate the height of the left subtree.
 *   - Calculate the height of the right subtree.
 *   - Check if their difference <= 1.
 *   - Recursively check left and right subtrees.
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
 * - Early exit.
 * - Stop recursion when you find an unbalanced subtree.
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
 * - Combine height calculation and balance check in one recursion.
 * - Return height if subtree is balanced.
 * - Return -1 if subtree is unbalanced.
 * - Steps:
 *   - Get left subtree height.
 *   - Get right subtree height.
 *   - If any subtree returns -1 -> propagate -1.
 *   - If |left - right| > 1 -> return -1.
 *   - Else return 1 + max(left, right).
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(H)
 *
 * Notes:
 * - Worst case (skewed tree): O(N).
 * - Best case (balanced tree): O(log N).
 * - This is a classic Tree DP problem:
 * - Compute something from children and pass upward.
 */

package trees._9_Advanced;
import trees.TreeNode;
public class BalancedBinaryTree {
    public boolean isBalanced(TreeNode root) {
        return checkHeight(root) != -1;
    }

    public int checkHeight(TreeNode root){

        if(root == null) return 0;

        int left = checkHeight(root.left);
        if(left == -1) return -1;

        int right = checkHeight(root.right);
        if(right == -1) return -1;

        if(Math.abs(left - right) > 1) return -1;

        return 1 + Math.max(left, right);
    }
}

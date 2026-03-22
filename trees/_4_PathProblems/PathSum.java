/**
 * Problem:
 * <Path Sum>
 *
 * Link:
 * <https://leetcode.com/problems/path-sum/description/>
 *
 * Pattern:
 * <DFS>
 *
 * Brute Force Intuition:
 * - Generate all the root to leaf paths.
 * - For each path:
 *  - Compute the sum of node values.
 *  - Check if it equals targetSum.
 *
 * - Why it is inefficient?
 * - Stores all paths explicitly.
 * - Recomputes sum for each path.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Better Approach Intuition:
 * - DFS traversal.
 * - Maintain current path sum while traversing.
 * - At each node:
 *  - Subtract node.val from targetSum.
 * - When reaching a leaf node:
 *  - Check if remaining sum == node.val.
 *
 * - Why it is better?
 * - Computes sum on the fly.
 * - We don't need to store paths.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(H)
 *
 * Optimal Approach (Used Below):
 * - Same as better approach.
 * - DFS with running sum.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(H)
 *
 * Notes:
 * - Only root to leaf paths are valid.
 * - Do not check the sum at null node.
 * - Leaf condition is critical:
 *  - node.left == null && node.right == null
 */

package trees._4_PathProblems;

import trees.TreeNode;

public class PathSum {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        return helper(root, targetSum);
    }

    public boolean helper(TreeNode node, int targetSum) {

        if (node == null) return false;

        if (node.left == null && node.right == null) {
            return targetSum == node.val;
        }

        return helper(node.left, targetSum - node.val) ||
                helper(node.right, targetSum - node.val);
    }
}

/**
 * Problem:
 * <Binary Tree Maximum Path Sum>
 *
 * Link:
 * <https://leetcode.com/problems/binary-tree-maximum-path-sum/description/>
 *
 * Pattern:
 * <Tree DP + Postorder DFS>
 *
 * Brute Force Intuition:
 * - Generate all the paths between every pair of nodes.
 * - A path can start and end at any node.
 * - Store all the paths.
 * - For each path:
 *  - Compute the sum.
 *  - Track the maximum path sum globally.
 *
 * - Why it is inefficient?
 * - Stores all paths explicitly.
 * - Path generation takes O(N^2) time.
 * - Each path can take O(N) to compute sum.
 *
 * Time Complexity:
 * - O(N^3)
 * Space Complexity:
 * - O(N^2) {Storing all paths}
 *
 * Better Approach Intuition:
 * - Avoid generating all the paths explicitly.
 * - Instead ask:
 * - "What is the best possible path passing through a node?"
 * - For each node:
 *  - Compute max path sum from left subtree.
 *  - Compute max path sum from right subtree.
 * - Ignore negatives:
 *  - left = Math.max(0, dfs(left)).
 *  - right = Math.max(0, dfs(right)).
 * - Compute the current path:
 *  - left + node.val + right.
 * - Track max sum globally.
 * - Return the maximum path sum for the parent.
 *
 * - Why it is better?
 * - Computes sum on the fly.
 * - Tracks maximum sum globally.
 * - We don't need to store paths.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(H) {Recursion stack}
 *
 * Optimal Approach (Used Below):
 * - Same as better approach.
 * - Postorder DFS.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(H)
 *
 * Notes:
 * - Path can start and end at any node.
 * - Path does not have to pass through the node.
 * - A node can act as a "bridge" (left + node + right).
 * - While returning to parent:
 *  - Choose only one side (no split allowed).
 * - Ignore negative paths:
 *  - They reduce the overall path sum.
 * - This is not a path-generation problem.
 * - This is "compute best at each node" problem.
 * - Similar pattern used in:
 *  - Diameter of binary tree.
 *  - Longest path problem.
 */

package trees._4_PathProblems;

import trees.TreeNode;

public class BinaryTreeMaximumPathSum {
    int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        if (root == null) return 0;

        dfs(root);
        return maxSum;
    }

    public int dfs(TreeNode node) {

        if (node == null) return 0;

        int left = Math.max(0, dfs(node.left));
        int right = Math.max(0, dfs(node.right));

        int currPathSum = left + node.val + right;
        maxSum = Math.max(maxSum, currPathSum);

        return node.val + Math.max(left, right);
    }
}

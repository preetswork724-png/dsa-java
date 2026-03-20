/**
 * Problem:
 * <Binary Tree Tilt>
 *
 * Link:
 * <https://leetcode.com/problems/binary-tree-tilt/description/>
 *
 * Pattern:
 * <Tree DP / DFS (Postorder)>
 *
 * Brute Force Intuition:
 * - For every node:
 *   - Compute the sum of left subtree.
 *   - Compute the sum of right subtree.
 *   - Compute the current tilt = |leftSum - rightSum|.
 *   - Recursively compute the tilts for left subtree and right subtree.
 *   - Add all tilts.
 *
 * - Why it is inefficient?
 * - Subtree sum is recalculated multiple times.
 * - Same nodes are visited again and again for sum calculation.
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(H)
 *
 * Better Approach Intuition:
 * - Use memoization (HashMap) to store subtree sums.
 * - Avoids recalculating the subtree sum again and again.
 * - Use map to retrieve the sum for node.
 *
 * - Why it is still not optimal?
 * - Uses extra space (hashMap).
 * - Still conceptually separates sum calculation and tilt calculation.
 * - Not a single pass solution.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Optimal Approach (Used Below):
 * - Use DFS (Postorder Traversal).
 * - For every node:
 *   - Calculate the left subtree sum.
 *   - Calculate the right subtree sum.
 *   - Calculate the current tilt.
 *   - Update the global answer.
 *   - Return total sum = node.val + left + right.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(H)
 *
 * Notes:
 * - This is a classic Tree DP problem:
 *   - Return subtree sum.
 *   - Update global answer.
 */
package trees._9_Advanced;

import trees.TreeNode;

public class BinaryTreeTilt {

    int totalTilt = 0;

    public int findTilt(TreeNode root) {
        dfs(root);
        return totalTilt;
    }

    public int dfs(TreeNode node) {

        if (node == null) return 0;

        int left = dfs(node.left);
        int right = dfs(node.right);

        totalTilt += Math.abs(left - right);
        return node.val + left + right;
    }

}

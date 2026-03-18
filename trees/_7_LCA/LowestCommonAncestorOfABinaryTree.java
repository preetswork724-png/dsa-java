/**
 * Problem:
 * <Lowest Common Ancestor of a Binary Tree>
 *
 * Link:
 * <https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/description/>
 *
 * Pattern:
 * <LCA>
 *
 * Brute Force Intuition:
 * - Store paths in a set.
 * - Traverse from root -> p.
 * - Traverse from root -> q.
 * - Store paths in a list.
 * - The last common node in both the paths is LCA.
 *
 * - Why it is inefficient?
 * - Uses extra space to store the paths.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Better Approach Intuition:
 * - Parent Mapping.
 * - Store parent of every node using BFS / DFS.
 * - Move from p upwards -> store ancestors in a set.
 * - Move from q upwards -> first match = LCA.
 *
 * - Why it is still not optimal?
 * - Uses extra space
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Optimal Approach (Used Below):
 * - Recursive DFS Approach.
 * - For every node in the tree:
 *   Check if node p or node q exists in its left subtree or right subtree.
 *   We check this by asking each node whether it parents the two nodes or not.
 * - If a node parents the two given nodes and is the first node to do so then it is the LCA.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N) {Recursion stack}
 *
 * Notes:
 * - Store the number of nodes parented.
 * - Recursion returns from the leaf node -> root. Therefore the first node that parents the two nodes is the LCA.
 */

package trees._7_LCA;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

public class LowestCommonAncestorOfABinaryTree {
    TreeNode ans = null;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        helper(root, p, q);
        return ans;
    }

    public int helper(TreeNode node, TreeNode p, TreeNode q) {

        if (node == null) return 0;

        int left = helper(node.left, p, q);
        int right = helper(node.right, p, q);
        int self = 0;

        if (node == p || node == q) self = 1;
        int total = left + self + right;

        if (total == 2 && ans == null) ans = node;
        return total;
    }
}

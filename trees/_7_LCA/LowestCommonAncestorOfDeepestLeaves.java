/**
 * Problem:
 * <Lowest Common Ancestor of Deepest Leaves>
 *
 * Link:
 * <https://leetcode.com/problems/lowest-common-ancestor-of-deepest-leaves/description/>
 *
 * Pattern:
 * <LCA>
 *
 * Brute Force Intuition:
 * - First, find maximum depth of the tree.
 * - For every node in the tree:
 *      Collect all leaves in teh subtree.
 *      Check if all deepest leaves belong to this subtree.
 * - The deepest such node is the answer.
 *
 * - Why it is inefficient?
 * - For every node, we traverse its entire subtree again.
 * - Repeated computation of subtree nodes.
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(N)
 *
 * Better Approach Intuition:
 * - Use BFS to find all teh deepest nodes.
 * - Store all the deepest nodes in a list.
 * - Reduce the problem:
 * - Find LCA of all he deepest nodes.
 * - Use standard LCA (DFS), for two nodes repeatedly.
 *
 * - Why it is still not optimal?
 * - LCA is computed multiple times.
 * - Each LCA takes O(N), leading to O(N^2) in worst case.
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(N)
 *
 * Optimal Approach (Used Below):
 * - Post-order DFS.
 * - For every node:
 *   Get depth of left subtree.
 *   Get depth of right subtree.
 * - Decision:
 *   If left depth > right -> answer lies in the left subtree.
 *   If right depth > left -> answer lies in the right subtree.
 *   If left depth = right -> current node is LCA.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(H) {Recursion stack}
 *
 * Notes:
 * - Deepest leaves wil always lie in the subtree with maximum depth.
 * - If both sides have equal depth -> the deepest nodes are split -> current node is LCA.
 */

package trees._7_LCA;

public class LowestCommonAncestorOfDeepestLeaves {

    TreeNode lca = null;
    int maxDepth = -1;

    public TreeNode lcaDeepestLeaves(TreeNode root) {
        helper(root, 0);
        return lca;
    }

    public int helper(TreeNode node, int depth){
        if(node == null) return depth;

        int left = helper(node.left, depth + 1);
        int right = helper(node.right, depth + 1);

        if(left == right && left >= maxDepth){
            maxDepth = left;
            lca = node;
        }
        return Math.max(left, right);
    }
}

/**
 * Problem:
 * <Maximum Depth of Binary Tree>
 *
 * Link:
 * <https://leetcode.com/problems/maximum-depth-of-binary-tree/description/>
 *
 * Pattern:
 * <DFS + Longest Path>
 *
 * Brute Force Intuition:
 * - For every node in the tree:
 *   Compute depth of left subtree.
 *   Compute depth of right subtree.
 *   Take maximum of both.
 *
 * - Why it is inefficient?
 * - It treats null child as valid path.
 * - Lead ti wring answers in skewed trees.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(H)
 *
 * Better Approach Intuition:
 * - Use BFS (level order traversal)
 * - Traverse level by level.
 * - The last leaf node encountered gives maximum depth.
 *
 * - Why it is still not optimal?
 * - Explores all the nodes at the current level.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(W) {Width = Maximum width of tree}
 *
 * Optimal Approach (Used Below):
 * - Use DFS recursion properly.
 * - Key idea:
 *      - If one child id null -> ignore that side.
 *      - Only consider paths that lead to lead nodes.
 *
 * - Why this works?
 * - Explores the complete path.
 * - Perfect for longest path problems.
 *
 * Time Complexity:
 * - O(N)
 *
 * Space Complexity:
 * - O(H)
 *
 * Notes:
 * - Maximum depth = longest path from root to any leaf.
 * - DFS naturally solves longest path problems.
 *
 * Pattern Recognition:
 * - "Maximum / longest path in tree"
 *    Think BFS first
 *
 * Mistake Log:
 * - Using Math.max(left, right) without handling null children.
 */
package trees._2_DFS;

public class MaximumDepthOfBinaryTree {
    
    public int maxDepth(TreeNode root) {
        if(root == null) return 0;

        if(root.left == null){
            return 1 + maxDepth(root.right);
        }

        if(root.right == null){
            return 1 + maxDepth(root.left);
        }

        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
}

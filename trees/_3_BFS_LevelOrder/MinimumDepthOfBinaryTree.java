/**
 * Problem:
 * <Minimum Depth of Binary Tree>
 *
 * Link:
 * <https://leetcode.com/problems/minimum-depth-of-binary-tree/description/>
 *
 * Pattern:
 * <BFS + Shortest Path>
 *
 * Brute Force Intuition:
 * - For every node in the tree:
 *   Compute depth of left subtree.
 *   Compute depth of right subtree.
 *   Take minimum of both.
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
 * - Use DFS recursion properly.
 * - Key idea:
 *      - If one child is null -> ignore that side.
 *      - Only consider paths that lead to leaf nodes.
 *
 * - Why it is still not optimal?
 * - Correctly handles edge cases.
 * - Avoids invalid paths.
 * - Explore the complete height of the tree.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(H)
 *
 * Optimal Approach (Used Below):
 * - Use BFS (level order traversal)
 * - Traverse level by level.
 * - The first leaf node encountered gives minimum depth.
 *
 * - Why this works?
 * - Stops early (no full traversal required).
 * - Perfect for shortest path problems.
 *
 * Time Complexity:
 * - O(N)
 *
 * Space Complexity:
 * - O(W) {Width of tree}
 *
 * Notes:
 * - Minimum depth = shortest path from root to any leaf.
 * - BFS naturally solves shortest path problems.
 *
 * Pattern Recognition:
 * - "Minimum / shortest path in tree"
 *    Think BFS first
 *
 * Mistake Log:
 * - Using Math.min(left, right) without handling null children.
 */

package trees._3_BFS_LevelOrder;
import java.util.Queue;
import java.util.ArrayDeque;

public class MinimumDepthOfBinaryTree {
    public int minDepth(TreeNode root) {
        if (root == null) return 0;

        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        int depth = 1;

        while (!q.isEmpty()) {

            int levelSize = q.size();

            for (int i = 0; i < levelSize; i++) {

                TreeNode front = q.poll();

                if (front.left == null && front.right == null) return depth;

                if (front.left != null) q.offer(front.left);
                if (front.right != null) q.offer(front.right);
            }
            depth++;
        }
        return depth;
    }
}

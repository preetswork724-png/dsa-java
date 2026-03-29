/**
 * Problem:
 * <Largest BST>
 *
 * Link:
 * <https://www.geeksforgeeks.org/problems/largest-bst/1>
 *
 * Pattern:
 * <Binary Tree + Bottom-Up DP + BST Property>
 *
 * Brute Force Intuition:
 * - For every node:
 *  - Check if the subtree rooted at that node is a BST.
 *  - Compute the size.
 *  - Track maximum size globally.
 *
 * - To check BST:
 *   - Use min/max range validation.
 *
 * - Why it is inefficient?
 * - Recomputes BST validation for each node.
 *
 * Time Complexity:
 * - O(N^2)
 *
 * Space Complexity:
 * - O(H) {recursion stack}
 *
 * Better Approach Intuition:
 * - Combine BST validation and size calculation in one traversal.
 * - For each node, return:
 *     → min value in subtree
 *     → max value in subtree
 *     → size of subtree
 *     → isBST flag
 *
 * - At each node:
 *     → Check if left and right subtrees are BST
 *     → Check BST condition:
 *         node.val > left.max && node.val < right.min
 *
 * - Why it is better?
 * - Avoids recomputation.
 * - Processes each node once.
 *
 * Time Complexity:
 * - O(N)
 *
 * Space Complexity:
 * - O(H)
 *
 * Optimal Approach (Used Below):
 * - Bottom-up traversal (postorder).
 * - Instead of explicit isBST flag:
 *     → Use min/max trick to invalidate subtree.
 *
 * - For each node:
 *
 *   1. Get left and right subtree info.
 *
 *   2. If valid BST:
 *        → node.val > left.max AND node.val < right.min
 *
 *        → return:
 *            min = min(node.val, left.min)
 *            max = max(node.val, right.max)
 *            size = left.size + right.size + 1
 *
 *   3. If NOT BST:
 *        → return:
 *            min = -∞
 *            max = +∞
 *            size = max(left.size, right.size)
 *
 * - Why This Works?
 * - BST property:
 *     left < root < right
 *
 * - Invalid subtree propagates failure upward using extreme values.
 * - Ensures parent cannot form BST using invalid child.
 *
 * Time Complexity:
 * - O(N)
 *
 * Space Complexity:
 * - O(H)
 *
 * Notes:
 * - This is a bottom-up DP on trees.
 * - Each node decides:
 *     → "Am I root of a BST?"
 *
 * - No need for separate traversal.
 *
 *
 * Common mistakes:
 * - Using <= instead of < (duplicates not allowed)
 * - Not propagating min/max correctly
 * - Recomputing subtree size
 * - Forgetting base case values
 *
 *
 * Mental model:
 * - "Each node collects info from children and decides validity"
 *
 *
 * Edge cases:
 * - Empty tree → return 0
 * - Single node → valid BST of size 1
 * - Entire tree is BST
 * - No BST except leaf nodes
 *
 *
 * Key takeaway:
 * - Tree problems often require bottom-up thinking instead of top-down
 *
 *
 * Follow-up:
 * - Largest BST sum instead of size
 * - Print the largest BST subtree
 * - Handle duplicates (modify conditions)
 */

package trees._6_BST;

import trees.TreeNode;

public class LargestBST {

    class NodeInfo {
        int min, max, size;

        NodeInfo(int min, int max, int size) {
            this.min = min;
            this.max = max;
            this.size = size;
        }
    }

    public int largestBSTSubtree(TreeNode root) {
        return dfs(root).size;
    }

    private NodeInfo dfs(TreeNode node) {
        if (node == null) {
            return new NodeInfo(Integer.MAX_VALUE, Integer.MIN_VALUE, 0);
        }

        NodeInfo left = dfs(node.left);
        NodeInfo right = dfs(node.right);

        // Valid BST
        if (node.val > left.max && node.val < right.min) {
            return new NodeInfo(
                    Math.min(node.val, left.min),
                    Math.max(node.val, right.max),
                    left.size + right.size + 1
            );
        }

        // Invalid BST
        return new NodeInfo(
                Integer.MIN_VALUE,
                Integer.MAX_VALUE,
                Math.max(left.size, right.size)
        );
    }
}

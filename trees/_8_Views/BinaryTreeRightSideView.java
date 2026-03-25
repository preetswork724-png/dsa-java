/**
 * Problem:
 * <Binary Tree Right Side View>
 *
 * Link:
 * <https://leetcode.com/problems/binary-tree-right-side-view/description/>
 *
 * Pattern:
 * <BFS/DFS + Level Processing>
 *
 * Brute Force Intuition:
 * - Traverse the tree using DFS.
 * - Store nodes with (row, column, value).
 *
 * - Use:
 *   TreeMap<row, TreeMap<col, value>>
 *
 * - Steps:
 * - Traverse entire tree.
 * - For each row:
 *   - Select node with maximum column (rightmost).
 *
 * - Why it is inefficient?
 * - Column tracking is unnecessary.
 * - Overcomplicated data structure.
 * - Extra sorting overhead.
 *
 * Time Complexity:
 * - O(N log N)
 * Space Complexity:
 * - O(N)
 *
 * Better Approach Intuition:
 * - Use BFS (level order traversal).
 *
 * - Key Idea:
 * - Each level represents a row.
 * - The last node in each level is the rightmost node.
 *
 * - Use:
 *   Map<row, List<Integer>>
 *
 * - Steps:
 * - Traverse using BFS.
 * - For each level:
 *   - Store nodes in a list.
 * - After traversal:
 *   - For each row:
 *     - Pick last element of list.
 *
 * - Why it is better?
 * - No need for column tracking.
 * - Level order naturally groups rows.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Optimal Approach (Used Below):
 * - Use BFS (level order traversal).
 * - Traverse level by level, so that the row order is maintained.
 * - Use:
 *  - List<values>
 * - Capture the last node at each level.
 * - During level traversal, the last node processed is the right most node.
 * - Iterate through all the nodes in that level:
 *  - While processing the last node, add it to the result.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * DFS Approach (Alternative Optimal):
 * - Use Depth First Search (preorder style).
 * - Traverse in order:
 *   root → right → left
 *
 * - Key Idea:
 *   - Visit right subtree first.
 *   - The first node encountered at each depth
 *     will be the rightmost node.
 *
 * - Steps:
 * - Maintain a result list.
 * - Pass current depth in recursion.
 * - For each node:
 *   - If depth == res.size():
 *       → first time visiting this level
 *       → add node value to result
 *
 * - Why it works:
 *   - Right subtree is visited before left.
 *   - Ensures rightmost nodes are processed first.
 *
 * - Time Complexity:
 * - O(N)
 *
 * - Space Complexity:
 * - O(H)
 *   (H = height of tree, recursion stack)
 *
 * - Notes:
 * - depth == res.size():
 *   - Ensures only first node per level is added.
 *
 * - Traversal order is CRITICAL:
 *   - right → left (not left → right)
 *
 * - Common mistakes:
 *   - Using left-first traversal → gives left view.
 *   - Forgetting depth check → duplicates values.
 *
 * - Mental model:
 *   - "First node seen at each depth from right side"
 *
 * - Comparison with BFS:
 *   - BFS → last node per level
 *   - DFS → first node per level (if right-first)
 */

package trees._8_Views;

import trees.TreeNode;

import java.util.List;
import java.util.LinkedList;

public class BinaryTreeRightSideView {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        helper(root, result, 0);
        return result;
    }

    void helper(TreeNode node, List<Integer> result, int depth) {
        if (node == null) return;
        if (depth == result.size()) {
            result.add(node.val);
        }
        helper(node.right, result, depth + 1);
        helper(node.left, result, depth + 1);
    }
}

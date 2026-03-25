/**
 * Problem:
 * <Left View of Binary Tree>
 *
 * Link:
 * <https://www.geeksforgeeks.org/problems/left-view-of-binary-tree/1>
 *
 * Pattern:
 * <BFS/DFS + Level-wise Processing>
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
 *   - Select node with minimum column (leftmost).
 *
 * - Why it is inefficient?
 * - Column tracking is unnecessary.
 * - Overcomplicated data structures.
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
 * - The first node in each level is the leftmost node.
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
 *     - Pick first element of list.
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
 * - Use BFS and capture the first node at each level.
 *
 * - Key Idea:
 *   - During level traversal, the first node processed
 *     is the leftmost node.
 *
 * - Steps:
 * - Use queue for BFS.
 * - For each level:
 *   - Iterate through nodes.
 *   - When processing first node (i == 0):
 *       → add it to result
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(W)  (W = max width of tree)
 *
 * DFS Approach (Alternative Optimal):
 * - Use preorder DFS.
 * - Traverse in order:
 *   root → left → right
 *
 * - Key Idea:
 *   - First node encountered at each depth
 *     is the leftmost node.
 *
 * - Steps:
 * - Maintain result list.
 * - Pass depth in recursion.
 * - If depth == res.size():
 *     → add node value
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(H)
 *
 * Notes:
 * - No need for column tracking.
 *
 * - Why BFS works:
 *   - Processes nodes level by level.
 *   - First node at each level is leftmost.
 *
 * - Why DFS works:
 *   - Left subtree is visited before right.
 *   - Ensures leftmost node is visited first.
 *
 * - Difference from Right View:
 *   - Left View → first node per level
 *   - Right View → last node per level
 *
 * - Common mistakes:
 *   - Using column-based logic (unnecessary).
 *   - Taking last node instead of first in BFS.
 *   - Wrong DFS order (right before left).
 *
 * - Mental model:
 *   - Stand on left side → only outermost nodes visible.
 *
 * - Edge cases:
 *   - Empty tree → return []
 *   - Single node → return [root]
 *   - Right-skewed tree → all nodes visible
 *
 * - Key takeaway:
 *   - This is a "first node per level" problem.
 */
package trees._8_Views;

import trees.Node;

import java.util.ArrayList;

public class LeftViewOfBinaryTree {
    public ArrayList<Integer> leftView(Node root) {
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null) return res;
        dfs(root, res, 0);
        return res;
    }

    public void dfs(Node node, ArrayList<Integer> res, int depth) {

        if (node == null) return;

        if (depth == res.size()) {
            res.add(node.data);
        }

        dfs(node.left, res, depth + 1);
        dfs(node.right, res, depth + 1);
    }
}

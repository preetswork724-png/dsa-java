/**
 * Algorithm:
 * <Morris Traversal (Preorder)>
 *
 * Link:
 * <https://en.wikipedia.org/wiki/Threaded_binary_tree>
 *
 * Pattern:
 * <Tree Traversal + Threading (O(1) Space)>
 *
 * Intuition:
 * - Goal: Perform preorder traversal WITHOUT recursion or stack.
 *
 * - Preorder order:
 *   Node → Left → Right
 *
 * - Core Idea:
 *   - Same as Morris Inorder:
 *     → Use threads (temporary links) to return back
 *
 * - Key Difference from Inorder:
 *   - Visit node BEFORE going left
 *
 *
 * Algorithm Steps:
 *
 * - Initialize:
 *   - curr = root
 *
 * - While curr != null:
 *
 *   1. If curr.left == null:
 *        - Visit curr
 *        - Move to curr.right
 *
 *   2. Else:
 *        - Find predecessor:
 *          pred = rightmost node of curr.left
 *
 *        - Case A: pred.right == null
 *            - Visit curr   // PREORDER CHANGE
 *            - Create thread:
 *              pred.right = curr
 *            - Move left:
 *              curr = curr.left
 *
 *        - Case B: pred.right == curr
 *            - Remove thread:
 *              pred.right = null
 *            - Move right:
 *              curr = curr.right
 *
 *
 * Time Complexity:
 * - O(N)
 * - Each node processed at most twice
 *
 * Space Complexity:
 * - O(1)
 *
 *
 * Notes:
 *
 * - Only ONE change from inorder:
 *   → Visit happens when thread is created
 *
 * - Why?
 *   - Preorder = process node BEFORE left subtree
 *
 * - Tree is restored after traversal
 *
 *
 * Difference from Morris Inorder:
 *
 * - Inorder:
 *   - Visit AFTER thread removal
 *
 * - Preorder:
 *   - Visit BEFORE going left (thread creation)
 *
 *
 * Common mistakes:
 *
 * - Visiting node twice
 *
 * - Forgetting:
 *   while (pred.right != null && pred.right != curr)
 *
 * - Adding visit in wrong place
 *
 *
 * Mental model:
 *
 * - "If I am about to go left, process node first"
 *
 *
 * Edge cases:
 *
 * - Empty tree → return empty
 *
 * - Single node → directly visited
 *
 *
 * Key takeaway:
 *
 * - Morris Preorder = Morris Inorder + shift visit timing
 *
 */
package trees._9_Advanced;

import trees.Node;

import java.util.ArrayList;

public class MorrisTraversalPreorder {
    public ArrayList<Integer> preOrder(Node root) {
        ArrayList<Integer> res = new ArrayList<>();
        Node curr = root;

        while (curr != null) {

            if (curr.left == null) {
                res.add(curr.data);
                curr = curr.right;
            } else {

                Node pred = curr.left;

                while (pred.right != null && pred.right != curr) {
                    pred = pred.right;
                }

                if (pred.right == null) {
                    res.add(curr.data);
                    pred.right = curr;
                    curr = curr.left;
                } else {
                    pred.right = null;
                    curr = curr.right;
                }
            }
        }
        return res;
    }
}

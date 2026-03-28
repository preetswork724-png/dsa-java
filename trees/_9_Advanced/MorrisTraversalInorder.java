/**
 * Algorithm:
 * <Morris Traversal (Inorder)>
 *
 * Link:
 * <https://en.wikipedia.org/wiki/Threaded_binary_tree>
 *
 * Pattern:
 * <Tree Traversal + Threading (O(1) Space)>
 *
 * Intuition:
 * - Goal: Perform inorder traversal WITHOUT recursion or stack.
 *
 * - Problem:
 *   - In recursion, we return to parent using call stack.
 *   - In iterative, we use an explicit stack.
 *
 * - Key Idea:
 *   - Create temporary "threads" to return back to a node.
 *
 * - How?
 *   - For each node:
 *     - If left exists:
 *         → Find inorder predecessor (rightmost node in left subtree)
 *         → Connect it back to current node
 *
 * - Why this works?
 *   - Simulates recursion using tree itself.
 *   - Allows traversal without extra space.
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
 *            - Create thread:
 *              pred.right = curr
 *            - Move left:
 *              curr = curr.left
 *
 *        - Case B: pred.right == curr
 *            - Remove thread:
 *              pred.right = null
 *            - Visit curr
 *            - Move right:
 *              curr = curr.right
 *
 *
 * Time Complexity:
 * - O(N)
 * - Each node is processed at most twice
 *
 * Space Complexity:
 * - O(1)
 * - No recursion or stack used
 *
 *
 * Notes:
 *
 * - Tree is temporarily modified but restored fully.
 *
 * - Difference from normal inorder:
 *   - Uses threading instead of stack/recursion
 *
 * - Each edge is visited at most twice:
 *   - Once going down
 *   - Once via thread coming back
 *
 * - Thread:
 *   - A temporary pointer from predecessor → current node
 *
 * - Inorder sequence:
 *   - Left → Node → Right
 *
 *
 * Why no extra space:
 * - Reuses null pointers in tree structure
 *
 *
 * Why postorder/preorder differ:
 * - Only change is WHEN you visit the node
 *
 *
 * Common mistakes:
 *
 * - Missing condition:
 *   while (pred.right != null && pred.right != curr)
 *
 * - Forgetting to remove thread:
 *   pred.right = null
 *
 * - Visiting node at wrong time
 *
 * - Infinite loop due to incorrect predecessor logic
 *
 *
 * Variations:
 *
 * - Morris Inorder:
 *   - Visit after thread removal
 *
 * - Morris Preorder:
 *   - Visit BEFORE going left
 *
 * - Morris Postorder:
 *   - Requires edge reversal (complex)
 *
 *
 * Mental model:
 * - "Create a temporary return path instead of using stack"
 *
 *
 * Edge cases:
 *
 * - Empty tree → return empty
 *
 * - Single node:
 *   - Directly visited
 *
 * - Skewed tree:
 *   - Still works in O(N) time
 *
 *
 * Key takeaway:
 *
 * - Tree traversal without recursion/stack is possible
 *   by temporarily modifying structure.
 *
 * - Morris Traversal = Space optimized DFS
 */

package trees._9_Advanced;

import trees.Node;

import java.util.ArrayList;

public class MorrisTraversalInorder {
    public ArrayList<Integer> inOrder(Node root) {
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
                    pred.right = curr;
                    curr = curr.left;
                } else {

                    pred.right = null;
                    res.add(curr.data);
                    curr = curr.right;
                }

            }
        }
        return res;
    }
}

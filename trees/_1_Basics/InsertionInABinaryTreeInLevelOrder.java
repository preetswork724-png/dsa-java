/**
 * Problem:
 * <Insertion in a Binary Tree in Level Order>
 *
 * Link:
 * <https://www.geeksforgeeks.org/dsa/insertion-in-a-binary-tree-in-level-order/>
 *
 * Pattern:
 * <Level Order Traversal (BFS) + Tree Modification>
 *
 *
 * Approach (Used Below):
 *
 * - Insert new node at first available position in level order.
 *
 * - Idea:
 *   - Traverse tree level by level using queue.
 *   - Check left child first.
 *   - Then check right child.
 *   - First null position found → insert node.
 *
 *
 * Steps:
 *
 * - 1. Base case:
 *      - If root == null:
 *          - Return new TreeNode(data)
 *
 * - 2. Initialize queue:
 *      - Add root
 *
 * - 3. While queue is not empty:
 *
 *      - Remove front node → curr
 *
 *      - Check left child:
 *          - If left exists:
 *              - Add to queue
 *          - Else:
 *              - Insert node here
 *              - Return root
 *
 *      - Check right child:
 *          - If right exists:
 *              - Add to queue
 *          - Else:
 *              - Insert node here
 *              - Return root
 *
 * - 4. Return root
 *
 *
 * Why this works?
 *
 * - Level order visits nodes top → bottom, left → right.
 *
 * - First empty child encountered is exactly the correct
 *   insertion point to preserve level-order filling.
 *
 * - Returning immediately avoids unnecessary traversal.
 *
 *
 * Time Complexity:
 *
 * - Worst case: O(N)
 *   - When tree is full except last position
 *
 * - Best case: O(1)
 *   - Root has empty left/right child
 *
 *
 * Space Complexity:
 *
 * - O(N)
 *   - Queue may store one full level
 *
 *
 * Notes:
 *
 * - This is NOT BST insertion.
 *
 * - Value comparison is not used.
 *
 * - Position depends only on first available slot
 *   in level-order traversal.
 *
 * - Preserves complete-tree style insertion.
 *
 *
 * Common mistakes:
 *
 * - Forgetting root == null case
 *
 * - Using DFS instead of BFS
 *   - DFS may insert deeper before filling upper levels
 *
 * - Not returning after insertion
 *   - Causes duplicate traversal
 *
 * - Checking right before left
 *   - Breaks left-to-right insertion order
 *
 *
 * Mental model:
 *
 * - "Walk level by level and place the node
 *    in the first empty seat."
 *
 *
 * Edge cases:
 *
 * - Empty tree
 *      -> create root
 *
 * - Root has no left child
 *      -> insert left
 *
 * - Root has left but no right
 *      -> insert right
 *
 * - Complete tree with multiple levels
 *      -> first null found in BFS
 *
 *
 * Key takeaway:
 * - Binary Tree level-order insertion =
 *   BFS + first available null child
 */

package trees._1_Basics;

import java.util.LinkedList;
import java.util.Queue;

public class InsertionInABinaryTreeInLevelOrder {

    public TreeNode insertNode(TreeNode root, int data) {

        if (root == null) return new TreeNode(data);

        Queue<TreeNode> queue = new LinkedList<>();

        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();

            if (curr.left != null) {
                queue.offer(curr.left);
            } else {
                curr.left = new TreeNode(data);
                return root;
            }

            if (curr.right != null) {
                queue.offer(curr.right);
            } else {
                curr.right = new TreeNode(data);
                return root;
            }
        }
        return root;
    }
}

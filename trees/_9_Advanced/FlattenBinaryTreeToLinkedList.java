/**
 * Problem:
 * <Flatten Binary Tree to Linked List>
 *
 * Link:
 * <https://leetcode.com/problems/flatten-binary-tree-to-linked-list/>
 *
 * Pattern:
 * <Modified DFS (Reverse Preorder) + In-place Pointer Manipulation>
 *
 * Brute Force Intuition:
 * - Perform preorder traversal of the list (root -> left -> right).
 * - Store nodes in a list.
 * - Reconnect nodes:
 *  - left = null
 *  - right = next node in preorder list.
 *
 * - Why it is inefficient?
 * - Uses extra space for storing nodes.
 * - Not in-place.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Better Approach Intuition:
 * - Use stack to simulate preorder traversal.
 * - Process nodes iteratively:
 *  - Push right first then left.
 *  - Connect current node's right to the next node in stack.
 *  - Set left = null.
 *
 * - Why it is better?
 * - Avoids storing full traversal list.
 * - Still uses auxiliary stack.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Optimal Approach (Used Below):
 * - Use reverse preorder traversal:
 *  - Process: right -> left -> root.
 * - Maintain a global pointer "prev".
 * - This pointer represents the next node in the flattened list.
 * - Steps:
 * - 1. Traverse right subtree.
 * - 2. Traverse left subtree.
 * - 3. Set:
 *      root.right = prev.
 *      root.left = null.
 * - 4. Update prev = root.
 *
 * - Why reverse preorder works?
 * - Normal preorder root -> left -> right.
 * - Reverse preorder:
 *   We already know "next node" while processing the current node.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(H) {Recursion stack}
 *
 * Notes:
 * - In-place modification (no extra data structures).
 *
 * - Why prev pointer is needed:
 *   - Keeps track of next node in flattened list.
 *
 * - Why right-first traversal:
 *   - So that left subtree connects correctly before root.
 *
 * - Difference from normal traversal:
 *   - Normal preorder → forward building
 *   - This approach → backward linking
 *
 * - Common mistakes:
 *   - Doing left first (breaks structure)
 *   - Forgetting to set left = null
 *   - Losing reference to right subtree
 *
 * - Mental model:
 *   - Build linked list from end → start
 *   - Like reversing a preorder traversal
 *
 * - Edge cases:
 *   - Empty tree
 *   - Single node
 *   - Skewed tree (already flattened)
 *
 * - Alternative optimal approach:
 *   - Morris Traversal (O(1) space)
 *
 * - Key takeaway:
 *   - Tree restructuring problems often use reverse DFS
 *   - Always think:
 *     "Can I process children first and fix parent later?"
 */

package trees._9_Advanced;

import trees.TreeNode;

public class FlattenBinaryTreeToLinkedList {
    TreeNode prev = null;

    public void flatten(TreeNode root) {
        if (root == null) return;

        flatten(root.right);
        flatten(root.left);

        root.right = prev;
        root.left = null;
        prev = root;
    }
}

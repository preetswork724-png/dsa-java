/**
 * Problem:
 * <Delete Node in a BST>
 *
 * Link:
 * <https://leetcode.com/problems/delete-node-in-a-bst/description/>
 *
 * Pattern:
 * <BST Property>
 *
 * Brute Force Intuition:
 * - Perform inorder traversal → store nodes in sorted list.
 * - Remove the target key from the list.
 * - Reconstruct BST from remaining elements.
 *
 * - Why it is inefficient?
 * - Full traversal + rebuilding entire tree.
 * - Unnecessary operations for deleting a single node.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 *
 * Better Approach Intuition:
 * - Perform inorder traversal and store values.
 * - Remove key.
 * - Instead of rebuilding completely:
 *   - Insert values one by one into a new BST.
 *
 * - Why it is better?
 * - Conceptually simpler than rebuilding balanced tree.
 *
 * - Why it is still bad?
 * - Still O(N) insertions.
 * - Extra space used.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 *
 * Optimal Approach (Used Below):
 * - Use BST property to directly delete node.
 *
 * - Steps:
 *
 *   1. Search for node:
 *      - If key < root → go left
 *      - If key > root → go right
 *
 *   2. If node found:
 *
 *      Case 1: No child (leaf)
 *          → return null
 *
 *      Case 2: One child
 *          → return non-null child
 *
 *      Case 3: Two children
 *          → Find inorder successor (smallest in right subtree)
 *          → Replace node value with successor value
 *          → Delete successor from right subtree
 *
 *
 * - Why successor?
 * - Maintains BST property (next greater element)
 *
 * - Alternative:
 * - Use inorder predecessor (largest in left subtree)
 *
 *
 * Time Complexity:
 * - O(H) ≈ O(log N) (balanced tree)
 *
 * Space Complexity:
 * - O(H) recursion stack
 *
 *
 * Notes:
 *
 * - Deletion = search + restructure
 *
 * - Only tricky case:
 *   - Node with TWO children
 *
 * - Successor always lies in right subtree
 *
 *
 * Common mistakes:
 *
 * - Forgetting to delete successor after replacement
 *
 * - Not handling all 3 cases
 *
 * - Breaking BST property
 *
 *
 * Mental model:
 *
 * - "Replace with next valid node and delete duplicate"
 *
 *
 * Edge cases:
 *
 * - Deleting root node
 *
 * - Node not present → return original tree
 *
 * - Tree with single node
 *
 *
 * Key takeaway:
 *
 * - BST deletion is local restructuring, not rebuilding
 *
 */

package trees._6_BST;

import trees.TreeNode;

public class DeleteNodeInABST {
    public TreeNode deleteNode(TreeNode root, int key) {
        TreeNode parent = null;
        TreeNode curr = root;

        // Step 1: Find node
        while (curr != null && curr.val != key) {
            parent = curr;

            if (key < curr.val) curr = curr.left;
            else curr = curr.right;
        }

        // Node not found
        if (curr == null) return root;

        // Step 2: Delete node
        // Case: node has 2 children
        if (curr.left != null && curr.right != null) {
            TreeNode succParent = curr;
            TreeNode succ = curr.right;

            // Find inorder successor
            while (succ.left != null) {
                succParent = succ;
                succ = succ.left;
            }

            // Replace value
            curr.val = succ.val;

            // Now delete successor instead
            parent = succParent;
            curr = succ;
        }

        // Now curr has at most ONE child
        TreeNode child = (curr.left != null) ? curr.left : curr.right;

        // Case: deleting root
        if (parent == null) return child;

        // Attach child to parent
        if (parent.left == curr) parent.left = child;
        else parent.right = child;

        return root;
    }
}

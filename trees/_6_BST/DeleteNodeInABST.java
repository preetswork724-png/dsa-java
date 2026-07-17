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
 * - Use BST property + subtree restructuring (merge) to delete node.
 * - Steps:
 * - 1. Search for node:
 * -    If key < root → go left
 * -    If key > root → go right
 * - 2. If node found:
 * -    Case 1: No child (leaf)
 * -    return null
 * -    Case 2: One child
 * -    return non-null child
 * -    Case 3: Two children (MAIN CASE)
 * -    Store left subtree and right subtree
 * -    Replace node with LEFT subtree
 * -    Find rightmost node of LEFT subtree
 * -    Attach RIGHT subtree to its right
 *
 * - Why This Works?
 * - Rightmost node of left subtree = maximum element of left subtree
 * - max(leftSub) < root < all nodes in rightSub
 * - So attaching right subtree there preserves BST
 *
 * Time Complexity:
 * - O(H) ≈ O(log N) (balanced tree)
 *
 * Space Complexity:
 * - O(1)
 *
 *
 * Notes:
 * - Deletion = search + subtree merge
 * - Instead of replacing values:
 * - We restructure pointers
 * - Only tricky case:
 * - Node with TWO children
 * - Key operation:
 * - Find rightmost of left subtree
 * - Common mistakes:
 * - Forgetting to attach right subtree
 * - Attaching at wrong position (not rightmost)
 * - Breaking BST property
 * -  Mental model:
 * - "Bring left subtree up and hang right subtree at its extreme right"
 * -  Edge cases:
 * - Deleting root node
 * - Node not present → return original tree
 * - Tree with single node
 * - Left subtree exists but has no right child (direct attach case)
 * -  Key takeaway:
 * - BST deletion can be done by merging subtrees instead of replacing values.
 */

package trees._6_BST;

import trees.TreeNode;

public class DeleteNodeInABST {

    public TreeNode deleteNode(TreeNode root, int key) {
        TreeNode parent = null, curr = root;

        while (curr != null && curr.val != key) {
            parent = curr;

            if (key < curr.val) curr = curr.left;
            else curr = curr.right;
        }

        if (curr == null) return root;

        if (curr.left != null && curr.right != null) {

            TreeNode leftSub = curr.left;
            TreeNode rightSub = curr.right;

            TreeNode temp = leftSub;

            while (temp.right != null) {
                temp = temp.right;
            }

            temp.right = rightSub;

            if (parent == null) return leftSub;

            if (parent.left == curr) parent.left = leftSub;
            else parent.right = leftSub;

            return root;
        }

        TreeNode child = (curr.left != null) ? curr.left : curr.right;

        if (parent == null) return child;

        if (parent.left == curr) parent.left = child;
        else parent.right = child;

        return root;
    }
}

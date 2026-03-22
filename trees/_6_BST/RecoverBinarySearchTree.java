/**
 * Problem:
 * <Recover Binary Search Tree>
 *
 * Link:
 * <https://leetcode.com/problems/recover-binary-search-tree/description/>
 *
 * Pattern:
 * <BFS + Inorder Traversal>
 *
 * Brute Force Intuition:
 * - Perform inorder traversal of the BST.
 * - Store the values of the nodes inside a list.
 * - Since, the inorder of the BST should be sorted.
 * - Find, the first violation where:
 *   - list.get(i) > list.get(i+1).
 * - Identify the two swapped values (x, y).
 * - Traverse the tree again:
 *   - Swap x with y and y with x.
 *
 * - Why it is inefficient?
 * - Uses extra space to store the values of the nodes.
 * - Multiple traversals.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Better Approach Intuition:
 * - Perform Inorder traversal (DFS).
 * - Do not store the values of the nodes in a list.
 * - Track nodes directly using pointers:
 *   - prev -> previously visited node.
 *   - first -> first wrong node.
 *   - second -> second wrong node.
 * - During traversal:
 *   - If prev.val > curr.val -> violation.
 * - First violation:
 *   - first = prev.
 *   - second = current.
 * - Second violation:
 *   - Update only second = current.
 * - After traversal:
 *   - Swap values of first and second.
 *
 * - Why it is still not optimal?
 * - Already optimal but is slow due to recursion.
 * - Uses space for recursion stack.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(H)
 *
 * Optimal Approach (Used Below):
 * - Use Morris-Inorder Traversal (Threaded Binary Tree).
 * - Traverse tree without recursion and without stack.
 * - For each node:
 *   - If left is null:
 *     - Process node.
 *     - Move to the right.
 *   - Else:
 *     - Find inorder predecessor.
 *     - If thread not created:
 *       - Create thread pred.right = current.
 *       - Move to left.
 *     - Else:
 *       - Remove link.
 *       - Process node.
 *       - Move to right.
 * - Check violations exactly like better approach: prev.val > curr.val.
 * - Swap values of first and second at the end.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - Inorder traversal of BST should be strictly increasing.
 * - The problem reduced to:
 *   - Finding two misplaced nodes in the tree.
 * - There can be 1 violation: adjacent swap.
 * - There can be 2 violation: non-adjacent swap.
 * - prev is not parent / child:
 * - It is the previous node in the inorder sequence.
 */

package trees._6_BST;

import trees.TreeNode;

public class RecoverBinarySearchTree {
    public void recoverTree(TreeNode root) {
        TreeNode first = null, second = null, prev = null;
        TreeNode current = root;

        while (current != null) {

            if (current.left == null) {

                if (prev != null && prev.val > current.val) {
                    if (first == null) first = prev;
                    second = current;
                }

                prev = current;
                current = current.right;

            } else {

                TreeNode pred = current.left;

                while (pred.right != null && pred.right != current) {
                    pred = pred.right;
                }

                if (pred.right == null) {
                    pred.right = current;
                    current = current.left;
                } else {
                    pred.right = null;

                    if (prev != null && prev.val > current.val) {
                        if (first == null) first = prev;
                        second = current;
                    }

                    prev = current;
                    current = current.right;
                }
            }
        }

        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }
}

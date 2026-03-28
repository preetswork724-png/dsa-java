/**
 * Problem:
 * <Insert into a Binary Search Tree>
 *
 * Link:
 * <https://leetcode.com/problems/insert-into-a-binary-search-tree/>
 *
 * Pattern:
 * <BST Property>
 *
 * Brute Force Intuition:
 * - Perform inorder traversal → get sorted list.
 * - Linearly find correct position for value.
 * - Insert value into list.
 * - Reconstruct BST from updated list.
 *
 * - Why it is inefficient?
 * - Unnecessary full traversal.
 * - Rebuilding entire tree.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 *
 * Better Approach Intuition:
 * - Perform inorder traversal → sorted list.
 * - Use binary search to find insert position.
 * - Insert value.
 * - Reconstruct BST.
 *
 * - Why it is better?
 * - Faster search than linear scan.
 *
 * - Why it is still bad?
 * - Still rebuilding entire tree.
 * - Extra space used.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 *
 * Optimal Approach (Used Below):
 * - Use BST property directly.
 *
 * - Idea:
 *   - Traverse tree like binary search.
 *   - Find correct null position to insert node.
 *
 * - Steps:
 *   - 1. If root is null:
 *        - Return new node
 *
 *   - 2. Initialize:
 *        - curr = root
 *
 *   - 3. While true:
 *
 *        - If value < curr.val:
 *            - If curr.left == null:
 *                - Insert node here
 *                - Break
 *            - Else:
 *                - Move left
 *
 *        - Else (value > curr.val):
 *            - If curr.right == null:
 *                - Insert node here
 *                - Break
 *            - Else:
 *                - Move right
 *
 *   - 4. Return root
 *
 *
 * - Why this works?
 * - BST guarantees correct position via comparisons.
 *
 * - Why no full traversal?
 * - Only one path from root to leaf is explored.
 *
 *
 * Time Complexity:
 * - O(H) ≈ O(log N) (balanced tree)
 *
 * Space Complexity:
 * - O(1) (iterative)
 *
 *
 * Notes:
 *
 * - Multiple valid BSTs possible → any correct insertion is valid
 *
 * - Always insert at leaf position
 *
 * - No need to rebalance (unless AVL/Red-Black tree)
 *
 *
 * Common mistakes:
 *
 * - Rebuilding tree unnecessarily
 *
 * - Not handling null root case
 *
 * - Forgetting to break after insertion (infinite loop)
 *
 *
 * Mental model:
 *
 * - "Follow BST path until you hit null → insert there"
 *
 *
 * Edge cases:
 *
 * - Empty tree → return new node
 *
 * - Insert smallest value → goes to extreme left
 *
 * - Insert largest value → goes to extreme right
 *
 *
 * Key takeaway:
 *
 * - BST insertion = binary search path + attach node at null
 *
 */

package trees._6_BST;

import trees.TreeNode;

public class InsertIntoABinarySearchTree {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);

        TreeNode curr = root;

        while (true) {

            if (val <= curr.val) {

                if (curr.left == null) {
                    curr.left = new TreeNode(val);
                    break;
                } else {
                    curr = curr.left;
                }
            } else {

                if (curr.right == null) {
                    curr.right = new TreeNode(val);
                    break;
                } else {
                    curr = curr.right;
                }
            }
        }
        return root;
    }
}

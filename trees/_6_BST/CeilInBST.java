/**
 * Problem:
 * <Ceil in BST>
 *
 * Link:
 * <https://www.geeksforgeeks.org/problems/find-ceil-in-bst/1>
 *
 * Pattern:
 * <BST Property>
 *
 * Brute Force Intuition:
 * - Perform inorder traversal (sorted order).
 * - Store elements in an array.
 * - Linearly scan to find:
 *   - First value >= x
 *
 * - Why it is inefficient?
 * - Full traversal + extra space + linear scan.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 *
 * Better Approach Intuition:
 * - Perform inorder traversal → sorted array.
 * - Use binary search to find:
 *   - Smallest value >= x
 *
 * - Why it is better?
 * - Faster search than linear scan.
 *
 * - Why it is still bad?
 * - Still uses extra space.
 * - Still traverses entire tree unnecessarily.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 *
 * Optimal Approach (Used Below):
 * - Use BST property:
 *   - Left < Node < Right
 *
 * - Idea:
 *   - Traverse tree like binary search.
 *   - Keep track of potential ceil.
 *
 * - Steps:
 *   - 1. Initialize:
 *        - ceil = -1
 *
 *   - 2. While root != null:
 *
 *        - If root.val == x:
 *            - Exact match → return x
 *
 *        - If root.val < x:
 *            - Move right (need larger value)
 *
 *        - If root.val > x:
 *            - Possible ceil
 *            - Store root.val
 *            - Move left (try smaller valid value)
 *
 *   - 3. Return ceil
 *
 *
 * - Why this works?
 * - BST eliminates half search space each step.
 *
 * - Why move left after storing ceil?
 * - To find a smaller value still >= x.
 *
 *
 * Time Complexity:
 * - O(H) ≈ O(log N) (for balanced tree)
 *
 * Space Complexity:
 * - O(1)
 *
 *
 * Notes:
 *
 * - If no value >= x exists → return -1
 *
 * - Works only for BST (NOT binary tree)
 *
 * - Similar problems:
 *   - Floor in BST
 *   - Lower Bound / Upper Bound
 *
 *
 * Common mistakes:
 *
 * - Traversing entire tree unnecessarily
 *
 * - Not updating ceil before going left
 *
 * - Confusing with floor logic
 *
 *
 * Mental model:
 *
 * - "Whenever I go left, I found a possible answer"
 *
 *
 * Edge cases:
 *
 * - x smaller than all nodes → smallest node is ceil
 *
 * - x greater than all nodes → return -1
 *
 * - Exact match → return immediately
 *
 *
 * Key takeaway:
 *
 * - BST + comparison → think binary search on tree
 *
 */
package trees._6_BST;

import trees.TreeNode;

public class CeilInBST {
    int findCeil(TreeNode root, int x) {
        int ceil = -1;

        while (root != null) {
            if (root.val == x) {
                return x;
            } else if (root.val < x) {
                root = root.right;
            } else {
                ceil = root.val;
                root = root.left;
            }
        }

        return ceil;
    }
}

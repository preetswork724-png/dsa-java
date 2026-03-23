/**
 * Problem:
 * <Convert Sorted Array to Binary Search Tree>
 *
 * Link:
 * <https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/description/>
 *
 * Pattern:
 * <Divide and Conquer + DFS + Binary Search>
 *
 * Brute Force Intuition:
 * - Try to generate all the possible Binary Search Trees (BSTs) using the given elements.
 * - For each possible tree:
 *  - Check if it is height balanced.
 *  - Check the BST property.
 * - Return any valid height-balanced tree.
 *
 * - Why it is inefficient?
 * - Number of BSTs grow exponentially (Catalan number).
 * - For each tree, we must check balance -> additional cost.
 * - Extremely large search space.
 *
 * Time Complexity:
 * - O(Catalan(n)) ≈ O(4^N / √N)
 * Space Complexity:
 * - O(Catalan(N))
 *
 * Better Approach Intuition:
 * - Instead of trying all the BSTs, use the sorted property:
 * - For a BST:
 *  - Left subtree -> contains smaller elements.
 *  - Right subtree -> contains larger elements.
 * - To maintain height balance:
 *  - Choose the middle element as root.
 * - Recursively:
 *  - Left half -> left subtree.
 *  - Right half -> right subtree.
 *
 * - Why it is better?
 * - Ensures balanced tree at every step.
 * - Avoids generating unnecessary trees.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(H) ≈ O(log N) {for balanced tree}
 *
 * Optimal Approach (Used Below):
 * - Same as better approach.
 * - Use index boundaries instead of slicing arrays.
 * - Recursively pick middle element
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(log N)
 *
 * Notes:
 * - Sorted array guarantees BST property automatically.
 * - Picking the middle node ensures that tree is height-balanced.
 * - Base case:
 *  - If (left > right) return null.
 * - Important steps:
 *  - mid = left + (right - left) / 2.
 * - Recursion builds tree:
 *  - root.left - build(left half)
 *  - root.right - build(right half)
 * - Avoid picking first / last elements:
 *  - Leads to skewed trees.
 * - This is not aa search problem.
 *  - This is a construction problem using divide & conquer.
 * - Mental model:
 *  - Pick mid -> root.
 *  - left -> left subtree.
 *  - right -> right subtree.
 */

package trees._5_Construction;

import trees.TreeNode;

public class ConvertSortedArrayToBinarySearchTree {

    public TreeNode sortedArrayToBST(int[] nums) {
        return build(nums, 0, nums.length - 1);
    }

    public TreeNode build(int[] nums, int left, int right) {

        if (left > right) return null;

        int mid = left + (right - left) / 2;

        TreeNode root = new TreeNode(nums[mid]);

        root.left = build(nums, left, mid - 1);
        root.right = build(nums, mid + 1, right);

        return root;
    }

}

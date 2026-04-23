/**
 * Problem:
 * <Lowest Common Ancestor of a Binary Search Tree>
 *
 * Link:
 * <https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/description/>
 *
 * Pattern:
 * <LCA>
 *
 * Brute Force Intuition:
 * - Use the method used to find LCA in a binary tree.
 *
 * - Why it is inefficient?
 * - Ignores the sorted nature of the tree.
 *
 * Time Complexity:
 * - O(H)
 * Space Complexity:
 * - O(H) {Recursion Stack : Height of tree} O(N) {Worst case for skewed trees}
 *
 * Better Approach Intuition:
 * - The lowest common ancestor in a binary search tree is s split point where.
 * - p.val < root.val < q.val .
 * - Use the sorted nature of the tree to find such a node.
 * - At any node:
 *   If both p and q < root → go left
 *   If both p and q > root → go right
 *   Else → THIS is LCA
 *
 * - Why it is still not optimal?
 * - Recursion overhead.
 *
 * Time Complexity:
 * - O(H)
 * Space Complexity:
 * - O(H)
 *
 * Optimal Approach (Used Below):
 * - Use an iterative approach.
 *
 * Time Complexity:
 * - O(H)
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - Time Complexity:
 * - Balanced BST -> O(log N).
 * - Skewed BST -> O(N).
 */

package trees._7_LCA;

public class LowestCommonAncestorOfABinarySearchTree {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        while(root != null){

            if(p.val < root.val && q.val < root.val){
                root = root.left;
            }
            else if(p.val > root.val && q.val > root.val){
                root = root.right;
            }
            else{
                return root;
            }
        }
        return null;
    }
}

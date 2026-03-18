/**
 * Problem:
 * <Subtree of Another Tree>
 *
 * Link:
 * <https://leetcode.com/problems/subtree-of-another-tree/description/>
 *
 * Pattern:
 * <Symmetry>
 *
 * Brute Force Intuition:
 * - At every node in the root:
 * - Assume it could be teh start.
 * - Check is subtree matches using a helper method.
 *
 * - Why it is inefficient?
 * - Repeated comparisons.
 * - Very slow on skewed trees.
 *
 * Time Complexity:
 * - O(N x M)
 * Space Complexity:
 * - O(H) {Recursion Stack : Height of tree} O(N) {Worst case for skewed trees}
 *
 * Better Approach Intuition:
 * - Convert trees into strings and check.
 * - subRootString is substring of rootString.
 * - Without "N" (null markers), structure breaks.
 *
 * - Why it is still not optimal?
 * - Keeps on recreating string.
 *
 * Time Complexity:
 * - O(N x M)
 * Space Complexity:
 * - O(N + M)
 *
 * Optimal Approach (Used Below):
 * - Same as above, but instead of using contains().
 * - Use KMP Algorithm for substring matching.
 *
 * Time Complexity:
 * - O(N + M)
 * Space Complexity:
 * - O(N + M)
 *
 * Notes:
 * - As both of the approaches are less expected in interviews, we'll stick with the recursive DFS approach.
 */

package trees._2_DFS;

public class SubtreeOfAnotherTree {
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if(root == null) return false;

        if(isSameTree(root, subRoot)) return  true;

        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    public boolean isSameTree(TreeNode p, TreeNode q){
        if(p == null && q == null) return true;

        if(p == null || q == null) return false;

        if(p.val != q.val) return false;

        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}

/**
 * Problem:
 * <Same Tree>
 *
 * Link:
 * <https://leetcode.com/problems/same-tree/description/>
 *
 * Pattern:
 * <Symmetry>
 *
 * Brute Force Intuition:
 * - Store all the nodes in a list including null.
 * - Store nulls explicitly as they help to define the structure of a tree.
 * - Compare the lists.
 *
 * - Why it is inefficient?
 * - Uses extra space to check symmetry of the trees.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Better Approach Intuition:
 * - Iterative BFS using queue.
 * - Traverse both the trees simultaneously using a queue.
 * - Store nodes pair-wise.
 * - Push (p, q) in the queue.
 * - Poll out two nodes.
 * - If p = null and q = null, continue.
 * - If p = null or q = null, return false.
 * - If p.val != q.val, return false;
 * - Push the left children of both the nodes.
 * - Push the right children of both the nodes.
 *
 * - Why it is still not optimal?
 * - Uses extra space to store the nodes.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Optimal Approach (Used Below):
 * - Recursive DFS Approach.
 * - Compare nodes during recursion.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - You can use any type of traversal.
 * - Every recursion level returns a result, therefore store it.
 */

package trees._2_DFS;

public class SameTree {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        return preorder(p, q);
    }

    public boolean preorder(TreeNode p, TreeNode q){

        if(p == null && q == null) return true;

        if(p == null || q == null) return false;

        if(p.val != q.val) return false;

        return preorder(p.left, q.left) && preorder(p.right, q.right);
    }
}

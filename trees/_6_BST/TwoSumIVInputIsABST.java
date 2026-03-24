/**
 * Problem:
 * <Lowest Common Ancestor of Deepest Leaves>
 *
 * Link:
 * <https://leetcode.com/problems/lowest-common-ancestor-of-deepest-leaves/description/>
 *
 * Pattern:
 * <LCA>
 *
 * Brute Force Intuition:
 * - For each node in the tree:
 *   Traverse the entire tree again to find (K - node.val).
 *
 * - Why it is inefficient?
 * - For each node, full traversal -> repeated work.
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(H)
 *
 * Better Approach Intuition:
 * - Use HashSet.
 * - Traverse the tree using BFS / DFS.
 * - For every node in the tree:
 *      Check if the tree already contains (k - node.val).
 *      If yes -> return true.
 *      else add node.val to the set.
 * - If no pair exists, return false.
 *
 * - Why it is still not optimal?
 * - Uses extra space.
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(N)
 *
 * Optimal Approach (Used Below):
 * - Inorder traversal → gives sorted array
 * - Apply two-pointer technique
 *
 * - Why this works?
 * - BST inorder gives sorted sequence
 * - Two-pointer efficiently finds pair sum
 *
 * Time Complexity:
 * - O(N)
 *
 * Space Complexity:
 * - O(N)
 *
 * Even Better (Advanced Optimal):
 * - Use two BST iterators:
 *     - One for next smallest
 *     - One for next largest
 * - Simulate two-pointer without storing array
 *
 * Time Complexity:
 * - O(N)
 *
 * Space Complexity:
 * - O(H)
 *
 * Notes:
 * Pattern Recognition:
 * - BST + target sum → think:
 *     1. HashSet (default)
 *     2. Inorder + two pointer
 *     3. Two iterators (advanced)
 */
package trees._6_BST;

import trees.TreeNode;

import java.util.List;
import java.util.ArrayList;

public class TwoSumIVInputIsABST {
    public boolean findTarget(TreeNode root, int k) {
        List<Integer> nodesVal = new ArrayList<>();
        inOrder(root, nodesVal);

        int left = 0, right = nodesVal.size() - 1;

        while (left < right) {

            int sum = nodesVal.get(left) + nodesVal.get(right);

            if (sum == k) return true;
            else if (sum > k) right--;
            else left++;
        }
        return false;
    }

    public void inOrder(TreeNode node, List<Integer> nodesVal) {

        if (node == null) return;

        inOrder(node.left, nodesVal);
        nodesVal.add(node.val);
        inOrder(node.right, nodesVal);
    }
}

/**
 * Problem:
 * <Construct Binary Search Tree from Preorder Traversal>
 *
 * Link:
 * <https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/>
 *
 * Pattern:
 * <BST + Preorder + DFS + Recursion + Bounds>
 *
 *
 * Brute Force Intuition:
 * - First element is root.
 * - Scan ahead:
 *   - smaller -> left subtree
 *   - greater -> right subtree
 * - Recurse on both parts.
 *
 * - Why inefficient?
 * - Repeated scanning for split index.
 *
 * Time Complexity:
 * - Worst: O(N²)
 * - Avg: O(N log N)
 *
 * Space Complexity:
 * - O(H)
 *
 *
 * Better / Optimal Approach:
 * - Use preorder index + upper bound.
 * - If value exceeds bound:
 *   - return null
 * - Else:
 *   - create node
 *   - left -> bound = root.val
 *   - right -> inherited bound
 *
 * - Why better?
 * - No scanning.
 * - Every node processed once.
 *
 * Time Complexity:
 * - O(N)
 *
 * Space Complexity:
 * - O(H)
 *
 *
 * Notes:
 * - Preorder = root -> left -> right
 * - First value always root.
 * - Left subtree:
 *   - values < root
 * - Right subtree:
 *   - values < parent bound
 *
 * Base cases:
 * - index == preorder.length
 * - preorder[index] > bound
 *
 * Avoid:
 * - Sorting preorder
 *   -> loses structure
 *
 * Mental model:
 * - Read preorder left to right.
 * - Ask:
 *   "Can this value fit here?"
 * - If yes:
 *   build node
 * - Else:
 *   return
 *
 *
 * Example:
 * preorder = [8,5,1,7,10,12]
 *
 *         8
 *       /   \
 *      5     10
 *     / \      \
 *    1   7      12
 *
 *
 * Common mistakes:
 * - Scanning every subtree
 * - Wrong bound in right subtree
 *
 *
 * Key takeaway:
 * - BST + preorder can be built in one pass.
 * - Bound controls recursion.
 */

package trees._5_Construction;

import trees.TreeNode;

public class ConstructBinarySearchTreeFromPreorderTraversal {
    int index = 0;

    public TreeNode bstFromPreorder(int[] preorder) {
        return buildTree(preorder, Integer.MAX_VALUE);
    }

    public TreeNode buildTree(int[] preorder, int bound) {

        if (index == preorder.length || preorder[index] > bound) {
            return null;
        }

        TreeNode root = new TreeNode(preorder[index]);

        root.left = buildTree(preorder, preorder[index++]);
        root.right = buildTree(preorder, bound);

        return root;
    }
}

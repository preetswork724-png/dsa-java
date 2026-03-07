/**
 * Problem:
 * <Binary Tree Preorder Traversal>
 *
 * Link:
 * <https://leetcode.com/problems/binary-tree-preorder-traversal/description/>
 *
 * Definition:
 * - Preorder is a DFS traversal technique in which the node is processed before exploring its children.
 * - In preorder traversal, the root node is always processed first followed by its left subtree and right subtree.
 *
 * Approach:
 * - Traverse the nodes in the order:
 * - Root -> left -> right.
 * - For every node:
 *   Visit the node.
 *   Traverse the left subtree.
 *   Traverse the right subtree.
 * - Recursive thinking:
 * - Add / print the current node.
 * - Recursively process the left subtree.
 * - Recursively process the right subtree.
 * - Recursion stops when you hit a null node.
 *
 * Time Complexities:
 * - O(N)
 *
 * Space Complexity:
 * - O(N)
 */

package trees._1_Basics;

import java.util.List;
import java.util.ArrayList;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class BinaryTreePreorderTraversal {

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        preorder(root, res);
        return res;
    }

    public void preorder(TreeNode node, List<Integer> res) {
        if (node == null) return;
        res.add(node.val);
        preorder(node.left, res);
        preorder(node.right, res);
    }

}

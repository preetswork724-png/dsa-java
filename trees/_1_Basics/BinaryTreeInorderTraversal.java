/**
 * Problem:
 * <Binary Tree Inorder Traversal>
 *
 * Link:
 * <https://leetcode.com/problems/binary-tree-inorder-traversal/description/>
 *
 * Definition:
 * - Inorder is a DFS traversal technique in which the node is processed after exploring its left children.
 * - In inorder traversal, the root node is always processed after its left subtree is processed.
 *
 * Approach:
 * - Traverse the nodes in the order:
 * - left -> Root -> right.
 * - For every node:
 *   Traverse the left subtree.
 *   Visit the node.
 *   Traverse the right subtree.
 * - Recursive thinking:
 * - Recursively process the left subtree.
 * - Add / print the current node.
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

public class BinaryTreeInorderTraversal {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        inorder(root, res);
        return res;
    }

    public void inorder(TreeNode node, List<Integer> res){

        if(node == null) return;

        inorder(node.left, res);
        res.add(node.val);
        inorder(node.right, res);
    }
}

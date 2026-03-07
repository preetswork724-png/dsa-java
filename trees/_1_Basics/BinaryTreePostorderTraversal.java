/**
 * Problem:
 * <Binary Tree Postorder Traversal>
 *
 * Link:
 * <https://leetcode.com/problems/binary-tree-postorder-traversal/description/>
 *
 * Definition:
 * - Postorder is a DFS traversal technique in which the node is processed after exploring its children.
 * - In postorder traversal, the root node is always processed after its left subtree and right subtree are processed.
 *
 * Approach:
 * - Traverse the nodes in the order:
 * - left -> right -> Root.
 * - For every node:
 *   Traverse the left subtree.
 *   Traverse the right subtree.
 *   Visit the node.
 * - Recursive thinking:
 * - Recursively process the left subtree.
 * - Recursively process the right subtree.
 * - Add / print the current node.
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

public class BinaryTreePostorderTraversal {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        postorder(root, res);
        return res;
    }

    public void postorder(TreeNode node, List<Integer> res){

        if(node == null) return;

        postorder(node.left, res);
        postorder(node.right, res);
        res.add(node.val);
    }
}

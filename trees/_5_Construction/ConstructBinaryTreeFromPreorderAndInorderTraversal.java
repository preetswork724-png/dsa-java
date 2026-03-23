/**
 * Problem:
 * <Construct Binary Tree from Preorder and Inorder Traversal>
 *
 * Link:
 * <https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/description/>
 *
 * Pattern:
 * <Tree Construction + Divide and Conquer + DFS>
 *
 * Brute Force Intuition:
 * - Preorder = root -> left -> right.
 * - Inorder = left -> root -> right.
 * - Preorder decides which node to build.
 * - Inorder decides where to split the tree into left subtree and right subtree.
 * - Recursion decides "Who connects to Whom?".
 * - Preorder gives the root node.
 * - For each node :
 *  - Search it linearly in the inorder[].
 *  - Split inorder into left and right subtrees.
 * - Recursively repeat for subtrees.
 *
 * - Why it is inefficient?
 * - Each search in linear order takes O(N).
 * - Done for every node -> repeated work.
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(N) {recursion stack}
 *
 * Better Approach Intuition:
 * - HashMap.
 * - Use HashMap to remember the indexes of the inorder roots.
 * - This reduces the time from O(N) to O(1).
 *
 * - Why it is better?
 * - Reduces linear search time to constant.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N) {Recursion stack}
 *
 * Optimal Approach (Used Below):
 * - Same as better approach.
 * - Use index boundaries instead of slicing arrays.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Notes:
 * - Preorder always gives the root node.
 * - Inorder determines left and right subtrees.
 * - Always build the LEFT subtree before right:
 * - Because preorder = root -> left -> right -> .
 * - Recursion builds the tree:
 *  - Each recursive call returns a subtree root.
 *  - These are linked using:
 *    root.left = ....
 *    root.right = ...
 * - Do NOT use array slicing:
 *   - It increases time and space complexity.
 * - Use indices (inStart, inEnd) to define subproblems.
 * - Base case:
 *   - if (inStart > inEnd) return null;
 * - Important variable:
 *   - preIndex → tracks current root in preorder.
 *
 * - Common mistakes:
 *   - Not incrementing preIndex.
 *   - Building right subtree before left.
 *   - Not using hashmap → leads to O(N^2).
 *
 * - Mental Model:
 *   - Preorder → "Which node to create"
 *   - Inorder → "Where to split"
 *   - Recursion → "How nodes are linked"
 */

package trees._5_Construction;

import trees.TreeNode;

import java.util.Map;
import java.util.HashMap;

public class ConstructBinaryTreeFromPreorderAndInorderTraversal {
    int preIndex = 0;
    Map<Integer, Integer> map = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {

        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        return dfs(preorder, inorder, 0, inorder.length - 1);
    }

    public TreeNode dfs(int[] preorder, int[] inorder, int inStart, int inEnd) {

        if (inStart > inEnd) return null;

        int rootVal = preorder[preIndex++];
        TreeNode root = new TreeNode(rootVal);

        int index = map.get(rootVal);

        root.left = dfs(preorder, inorder, inStart, index - 1);
        root.right = dfs(preorder, inorder, index + 1, inEnd);

        return root;
    }

}

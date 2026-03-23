/**
 * Problem:
 * <Construct Binary Tree from Inorder and Postorder Traversal>
 *
 * Link:
 * <https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/description/>
 *
 * Pattern:
 * <Tree Construction + Divide and Conquer + DFS>
 *
 * Brute Force Intuition:
 * - Postorder = root -> left -> right.
 * - Inorder = left -> root -> right.
 * - Postorder decides which node to build.
 * - Inorder decides where to split the tree into left subtree and right subtree.
 * - Recursion decides "Who connects to Whom?".
 * - Iterating from right -> left on postorder[]:
 *  - gives the root node.
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
 * - Use a HashMap to store value → index mapping of inorder.
 * - This allows O(1) lookup.
 *
 * - Traverse postorder from the end:
 *   - Root → Right → Left
 *
 * - Steps:
 * 1. Build map from inorder.
 * 2. Pick root from postorder using postIndex.
 * 3. Split inorder using index.
 * 4. Recursively build:
 *    - Right subtree FIRST
 *    - Then left subtree
 *
 * - Why it is better?
 * - Avoids repeated searching.
 * - Each node processed once.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N) {Map + Recursion stack}
 *
 * Optimal Approach (Used Below):
 * - Same as better approach.
 * - Use index boundaries instead of slicing arrays.
 * - Maintain global postIndex.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Notes:
 * - Postorder always gives the root node (from the end).
 * - Inorder determines left and right subtrees.
 * - Always build the RIGHT subtree before right:
 * - Because postorder = left -> right -> root.
 * - Recursion builds the tree:
 *  - Each recursive call returns a subtree root.
 *  - These are linked using:
 *    root.right = ....
 *    root.left = ...
 * - Do NOT use array slicing:
 *   - It increases time and space complexity.
 * - Use indices (inStart, inEnd) to define subproblems.
 * - Base case:
 *   - if (inStart > inEnd) return null;
 * - Important variable:
 *   - postIndex → tracks current root in postorder.
 *
 * - Common mistakes:
 *   - Not incrementing postIndex.
 *   - Building left subtree before right.
 *   - Not using hashmap → leads to O(N^2).
 *
 * - Mental Model:
 *   - Postorder → "Which node to create"
 *   - Inorder → "Where to split"
 *   - Recursion → "How nodes are linked"
 */

package trees._5_Construction;

import trees.TreeNode;

import java.util.Map;
import java.util.HashMap;

public class ConstructBinaryTreeFromInorderAndPostorderTraversal {
    int postIndex;
    Map<Integer, Integer> map = new HashMap<>();

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        postIndex = postorder.length - 1;

        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        return dfs(inorder, postorder, 0, inorder.length - 1);
    }

    public TreeNode dfs(int[] inorder, int[] postorder, int inStart, int inEnd) {

        if (inStart > inEnd) return null;

        int rootVal = postorder[postIndex--];
        TreeNode root = new TreeNode(rootVal);

        int index = map.get(rootVal);

        root.right = dfs(inorder, postorder, index + 1, inEnd);
        root.left = dfs(inorder, postorder, inStart, index - 1);

        return root;
    }
}

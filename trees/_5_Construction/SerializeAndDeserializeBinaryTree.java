/**
 * Problem:
 * <Serialize and Deserialize Binary Tree>
 *
 * Link:
 * <https://leetcode.com/problems/serialize-and-deserialize-binary-tree/description/>
 *
 * Pattern:
 * <Tree Traversal + DFS>
 *
 * Brute Force Intuition:
 * - Store multiple traversals of the tree.
 * - Use them later to construct the tree.
 *
 * - Why it is inefficient?
 * - Requires storing extra 2 arrays (preorder + inorder).
 * - Constructing the tree becomes a separate problem.
 * - More complex and unnecessary.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Better Approach Intuition:
 * - Use BFS (level order traversal).
 * - Store null nodes explicitly.
 * - Steps:
 * - Traverse using queue.
 * - Add node values to the string.
 * - Add null values to the string.
 *
 * - Why it is better?
 * - Preserves structure clearly.
 * - Easy to visualize and debug.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Optimal Approach (Used Below):
 * - Use preorder DFS with null markers.
 * - Traverse tree using preorder.
 * - Append node values to a string.
 * - Append null values to a string.
 * - During deserialization:
 *  - Read values sequentially.
 *  - Build the tree using recursion.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Notes:
 * - Store null markers.
 *  - Without them, trees cannot be reconstructed uniquely.
 * - Serialization.
 *  - Use preorder traversal (root -> left -> right).
 * - Deserialization:
 *  - Replay preorder traversal.
 * - index -> tracks current position in serialized data.
 * - Parsing:
 *  - Parsing takes O(K) per number where k is number of digits.
 *  - But the number of digits are small -> overall O(N).
 * - Recursion builds tree:
 *  - root.left = build(left tree).
 *  - root.right = build(right tree).
 * - Common mistakes:
 *  - Not storing nulls explicitly.
 *  - Changing traversal order.
 *  - Incorrect index handling.
 * - Do not rely on inorder alone:
 *  - Multiple trees can have same inorder traversal.
 * - Mental model:
 *  - Serialization: Write tree structure with preorder traversal.
 *  - Deserialization: read and rebuild in same order.
 * - How does tha array index stay on bounds?
 *  - Every recursion call consumes exactly one element of the array.
 *  - The serialized string is designed in such a way that the recursion stops exactly when all elements are consumed.
 */

package trees._5_Construction;

import trees.TreeNode;

public class SerializeAndDeserializeBinaryTree {
    int index = 0;

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        dfs(root, sb);
        return sb.toString();
    }

    public void dfs(TreeNode node, StringBuilder sb) {

        if (node == null) {
            sb.append("null,");
            return;
        }

        sb.append(node.val).append(",");
        dfs(node.left, sb);
        dfs(node.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] arr = data.split(",");
        return build(arr);
    }

    public TreeNode build(String[] arr) {

        if (arr[index].equals("null")) {
            index++;
            return null;
        }

        TreeNode root = new TreeNode(Integer.parseInt(arr[index++]));

        root.left = build(arr);
        root.right = build(arr);

        return root;
    }
}

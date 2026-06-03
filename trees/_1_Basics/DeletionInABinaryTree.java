/**
 * Problem:
 * <Deletion in a Binary Tree>
 *
 * Link:
 * <https://www.geeksforgeeks.org/problems/deletion-in-a-binary-tree/1>
 *
 * Pattern:
 * <Level Order Traversal (BFS)>
 *
 *
 * Approach:
 *
 * - Find:
 *      - node containing key
 *      - deepest/rightmost node
 *
 * - Copy deepest node’s value into key node
 *
 * - Delete deepest node
 *
 *
 * Steps:
 *
 * - 1. If root == null:
 *        return null
 *
 * - 2. If single node:
 *        - key matches -> return null
 *        - else -> return root
 *
 * - 3. BFS traversal:
 *        - store keyNode when found
 *        - curr keeps updating
 *
 * - 4. After traversal:
 *        - curr = deepest node
 *
 * - 5. If keyNode exists:
 *        - keyNode.data = curr.data
 *        - deleteDeepest(root, curr)
 *
 * - 6. Return root
 *
 *
 * deleteDeepest():
 *
 * - BFS again
 *
 * - Find parent of deepest node
 *
 * - Remove:
 *      - parent.left = null
 *      OR
 *      - parent.right = null
 *
 *
 * Why this works?
 *
 * - Binary tree has no ordering.
 *
 * - Deepest/rightmost replacement preserves structure.
 *
 *
 * Time Complexity:
 * - O(N)
 *
 * Space Complexity:
 * - O(N)
 *
 *
 * Common mistakes:
 *
 * - Missing null / single-node case
 *
 * - Not deleting deepest node after replacement
 *
 * - Confusing deepest with any leaf
 *
 *
 * Mental model:
 *
 * - "Replace target with last node,
 *    then delete last node."
 *
 *
 * Key takeaway:
 *
 * - Binary tree deletion =
 *   deepest node replacement + BFS removal
 *
 */
package trees._1_Basics;

import trees.Node;

import java.util.LinkedList;
import java.util.Queue;

public class DeletionInABinaryTree {
    public Node deleteNode(Node root, int key) {

        if (root == null) return null;

        if (root.left == null && root.right == null) {

            if (root.data == key) {
                return null;
            } else {
                return root;
            }
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        Node keyNode = null;
        Node curr = null;

        while (!queue.isEmpty()) {

            curr = queue.poll();

            if (curr.data == key) {
                keyNode = curr;
            }

            if (curr.left != null) queue.add(curr.left);
            if (curr.right != null) queue.add(curr.right);
        }

        if (keyNode != null) {

            int x = curr.data;

            keyNode.data = x;

            deleteDeepest(root, curr);
        }

        return root;
    }

    public void deleteDeepest(Node root, Node del) {

        if (root == null) return;

        Queue<Node> queue = new LinkedList<>();

        queue.add(root);

        while (!queue.isEmpty()) {

            Node curr = queue.poll();

            if (curr == del) {
                curr = null;
                del = null;
                return;
            }

            if (curr.left != null) {

                if (curr.left == del) {
                    curr.left = null;
                    del = null;
                    return;
                }

                queue.add(curr.left);
            }

            if (curr.right != null) {

                if (curr.right == del) {
                    curr.right = null;
                    del = null;
                    return;
                }

                queue.add(curr.right);
            }
        }
    }
}

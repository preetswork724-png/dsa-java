/**
 * Problem:
 * <Convert Level Order Traversal to BST>
 *
 * Link:
 * <https://www.geeksforgeeks.org/problems/convert-level-order-traversal-to-bst/1>
 *
 * Pattern:
 * <BST + Level Order + Queue + Bounds>
 *
 *
 * Brute Force Intuition:
 * - First element is root.
 * - Insert remaining values one by one into BST.
 * - Use normal BST insertion:
 *   - smaller -> left
 *   - greater -> right
 *
 * - Why inefficient?
 * - Every insertion starts from root.
 * - Repeated traversal increases cost.
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
 * - Use queue + valid ranges.
 * - Store:
 *   - node
 *   - min bound
 *   - max bound
 *
 * - Read level order left to right.
 * - For each node:
 *   - next smaller valid value -> left
 *   - next greater valid value -> right
 * - Push children with updated bounds.
 *
 * - Why better?
 * - No repeated traversal.
 * - Every node processed once.
 * - Queue preserves level order.
 *
 * Time Complexity:
 * - O(N)
 *
 * Space Complexity:
 * - O(N)
 *
 *
 * Notes:
 * - BST:
 *   left < root < right
 *
 * - Level order:
 *   process level by level
 *
 * - Values from left/right subtree can interleave.
 * - So preorder-style recursion does not work.
 *
 * Base case:
 * - empty array
 *
 * Important:
 * - Left child:
 *   min < val < node
 *
 * - Right child:
 *   node < val < max
 *
 * Avoid:
 * - Sorting array
 * - Recursive preorder-style bounds
 *
 *
 * Mental model:
 * - Queue stores open positions.
 * - Each position has allowed range.
 * - Next value fills valid slot.
 *
 *
 * Example:
 * arr = [8,5,10,1,7,12]
 *
 *         8
 *       /   \
 *      5     10
 *     / \      \
 *    1   7      12
 *
 *
 * Common mistakes:
 * - Reusing preorder logic
 * - Ignoring bounds
 * - Losing level order
 *
 *
 * Key takeaway:
 * - Level order mixes subtrees.
 * - Queue + bounds reconstructs BST in O(N).
 */

package trees._5_Construction;

import trees.Node;

import java.util.LinkedList;
import java.util.Queue;

class Pair {
    Node node;
    int min;
    int max;

    Pair(Node node, int min, int max) {
        this.node = node;
        this.min = min;
        this.max = max;
    }
}

public class ConvertLevelOrderTraversalToBST {
    public Node constructBst(int[] arr) {

        if (arr.length == 0) return null;

        Node root = new Node(arr[0]);

        Queue<Pair> q = new LinkedList<>();

        q.offer(new Pair(root,
                Integer.MIN_VALUE,
                Integer.MAX_VALUE));

        int i = 1;

        while (i < arr.length) {

            Pair p = q.poll();

            // left child
            if (i < arr.length &&
                    arr[i] > p.min &&
                    arr[i] < p.node.data) {

                p.node.left = new Node(arr[i]);

                q.offer(new Pair(
                        p.node.left,
                        p.min,
                        p.node.data));

                i++;
            }

            // right child
            if (i < arr.length &&
                    arr[i] > p.node.data &&
                    arr[i] < p.max) {

                p.node.right = new Node(arr[i]);

                q.offer(new Pair(
                        p.node.right,
                        p.node.data,
                        p.max));

                i++;
            }
        }
        return root;
    }
}

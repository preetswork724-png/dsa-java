/**
 * Problem:
 * <Predecessor and Successor>
 *
 * Link:
 * <https://www.geeksforgeeks.org/problems/predecessor-and-successor/1>
 *
 * Pattern:
 * <BST Property>
 *
 * Brute Force Intuition:
 * - Perform inorder traversal → store nodes in a list.
 * - Since inorder of BST is sorted:
 *   - Traverse the list.
 *   - If list[i] < key:
 *       → update predecessor
 *   - First element > key:
 *       → successor
 *       → break
 *
 * - Why it is inefficient?
 * - Does not use BST property.
 * - Uses extra space for list.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N) {list + recursion stack}
 *
 *
 * Better Approach Intuition:
 * - Do inorder traversal WITHOUT storing list.
 * - Maintain:
 *   - prev (previous node)
 *   - pred (last smaller)
 *   - succ (first greater)
 *
 * - While traversing:
 *   - If node.val < key:
 *       → update pred
 *   - If node.val > key AND succ not set:
 *       → update succ
 *
 * - Why it is better?
 * - Avoids extra list.
 * - Uses traversal directly.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(H) {recursion stack}
 *
 *
 * Optimal Approach (Used Below):
 * - Use BST property to avoid full traversal.
 *
 * - Traverse iteratively:
 *
 *   1. If curr.data < key:
 *        → curr is potential predecessor
 *        → move right (to find closer value)
 *
 *   2. If curr.data > key:
 *        → curr is potential successor
 *        → move left (to find closer value)
 *
 *   3. If curr.data == key:
 *        → predecessor = max of left subtree
 *        → successor = min of right subtree
 *        → break
 *
 *
 * - Why This Works?
 * - BST property:
 *     left < root < right
 *
 * - Moving right gives closer smaller values (pred)
 * - Moving left gives closer greater values (succ)
 *
 *
 * Time Complexity:
 * - O(H) ≈ O(log N) (balanced tree)
 *
 * Space Complexity:
 * - O(1)
 *
 *
 * Notes:
 * - No need for full traversal.
 * - Keep updating pred and succ during traversal.
 * - Works even if key is NOT present.
 *
 *
 * Common mistakes:
 * - Using root instead of curr while updating succ/pred.
 * - Including key itself as predecessor/successor.
 * - Not handling case when key is absent
 *
 *
 * Mental model:
 * - "Track last smaller and last greater while moving down the tree"
 *
 *
 * Edge cases:
 * - No predecessor → return null
 * - No successor → return null
 * - Key not present → still compute floor & ceil
 * - Single node tree
 *
 *
 * Key takeaway:
 * - BST allows finding predecessor & successor in O(H) without full traversal
 */

package trees._6_BST;

import trees.Node;

import java.util.ArrayList;

public class PredecessorAndSuccessor {
    public ArrayList<Node> findPreSuc(Node root, int key) {
        ArrayList<Node> res = new ArrayList<>();

        Node pred = null, succ = null, curr = root;

        while (curr != null) {

            if (curr.data < key) {
                pred = curr;
                curr = curr.right;
            } else if (curr.data > key) {
                succ = curr;
                curr = curr.left;
            } else {

                if (curr.left != null) {

                    Node temp = curr.left;

                    while (temp.right != null) {
                        temp = temp.right;
                    }

                    pred = temp;
                }

                if (curr.right != null) {

                    Node temp = curr.right;

                    while (temp.left != null) {
                        temp = temp.left;
                    }

                    succ = temp;
                }
                break;
            }
        }
        res.add(pred);
        res.add(succ);

        return res;
    }
}

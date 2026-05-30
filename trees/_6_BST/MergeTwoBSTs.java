/**
 * Problem:
 * <Merge Two BSTs into a sorted list>
 *
 * Pattern:
 * <BST Inorder + Merge>
 *
 *
 * Brute Force Intuition:
 *
 * - Traverse both BSTs
 *
 * - Store every node in one list
 *
 * - Sort the final list
 *
 *
 * Steps:
 *
 * - 1. Traverse BST1
 *      -> add all nodes
 *
 * - 2. Traverse BST2
 *      -> add all nodes
 *
 * - 3. Sort combined list
 *
 * - 4. Return result
 *
 *
 * Why it works?
 *
 * - All values are collected
 *
 * - Sorting gives final order
 *
 *
 * Why inefficient?
 *
 * - BST property not used
 *
 * - Sorting extra work
 *
 *
 * Time Complexity:
 * - O((N+M) log(N+M))
 *
 * Space Complexity:
 * - O(N+M)
 *
 *
 * --------------------------------------------------
 *
 * Better Approach:
 *
 * - Use BST property
 *
 * - Inorder of BST gives sorted list
 *
 * - Build:
 *      - list1 from BST1
 *      - list2 from BST2
 *
 * - Merge like sorted arrays
 *
 *
 * Steps:
 *
 * - 1. Inorder(root1) -> list1
 *
 * - 2. Inorder(root2) -> list2
 *
 * - 3. Merge with 2 pointers
 *
 * - 4. Return result
 *
 *
 * Why better?
 *
 * - No sorting needed
 *
 * - Uses BST property directly
 *
 *
 * Why still not optimal?
 *
 * - Stores full inorder of both trees
 *
 *
 * Time Complexity:
 * - O(N+M)
 *
 * Space Complexity:
 * - O(N+M)
 *
 *
 * --------------------------------------------------
 *
 * Optimal Approach:
 *
 * - Simultaneous inorder traversal
 *
 * - Use:
 *      - stack1
 *      - stack2
 *
 * - Generate next smallest value on demand
 *
 *
 * Steps:
 *
 * - 1. Push left chain of both trees
 *
 * - 2. Compare top of stacks
 *
 * - 3. Smaller top:
 *      - pop
 *      - add to result
 *      - move right
 *
 * - 4. Push left chain again
 *
 * - 5. Continue until both stacks empty
 *
 *
 * Why this works?
 *
 * - Inorder gives sorted order
 *
 * - Two stacks behave like BST iterators
 *
 * - Smaller top always next answer
 *
 *
 * Why optimal?
 *
 * - Same time
 *
 * - No extra inorder lists
 *
 *
 * Time Complexity:
 * - O(N+M)
 *
 * Space Complexity:
 * - O(H1 + H2)
 *
 *      H1 = height of BST1
 *      H2 = height of BST2
 *
 *
 * Common mistakes:
 *
 * - Forgetting stack empty checks
 *
 * - Not pushing left chain after moving right
 *
 * - Confusing height with node count
 *
 *
 * Mental model:
 *
 * - Brute:
 *      collect + sort
 *
 * - Better:
 *      inorder + merge arrays
 *
 * - Optimal:
 *      two BST iterators + merge
 *
 *
 * Edge cases:
 *
 * - Both trees null
 *
 * - One tree null
 *
 * - Duplicate values
 *
 * - Skewed BST
 *
 *
 * Key takeaway:
 *
 * - BST merge:
 *
 *      Brute  -> collect + sort
 *
 *      Better -> inorder + merge
 *
 *      Optimal -> simultaneous inorder with 2 stacks
 *
 */

package trees._6_BST;

import java.util.ArrayList;
import java.util.Stack;

public class MergeTwoBSTs {
    public ArrayList<Integer> merge(Node root1, Node root2) {
        ArrayList<Integer> res = new ArrayList<>();

        if (root1 == null && root2 == null) return res;

        Stack<Node> stack1 = new Stack<>();
        Stack<Node> stack2 = new Stack<>();

        while (root1 != null || root2 != null || !stack1.isEmpty() || !stack2.isEmpty()) {

            while (root1 != null) {
                stack1.push(root1);
                root1 = root1.left;
            }

            while (root2 != null) {
                stack2.push(root2);
                root2 = root2.left;
            }

            if (stack1.isEmpty()) {
                Node curr = stack2.pop();
                res.add(curr.data);
                root2 = curr.right;
            } else if (stack2.isEmpty()) {
                Node curr = stack1.pop();
                res.add(curr.data);
                root1 = curr.right;
            } else if (stack1.peek().data <= stack2.peek().data) {
                Node curr = stack1.pop();
                res.add(curr.data);
                root1 = curr.right;
            } else {
                Node curr = stack2.pop();
                res.add(curr.data);
                root2 = curr.right;
            }
        }
        return res;
    }
}

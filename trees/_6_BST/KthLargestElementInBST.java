/**
 * Problem:
 * <Kth Largest Element in BST>
 *
 * Link:
 * <https://www.geeksforgeeks.org/problems/kth-largest-element-in-bst/1>
 *
 * Pattern:
 * <BST Property + Reverse Inorder Traversal>
 *
 * Brute Force Intuition:
 * - Perform inorder traversal of BST.
 * - Store all nodes in a sorted list.
 *
 * - Inorder gives:
 *     left -> root -> right
 *     => ascending order
 *
 * - Kth largest:
 *     → list.get(size - k)
 *
 * - Why it is inefficient?
 * - Stores every node.
 * - Extra traversal + extra memory.
 *
 * Time Complexity:
 * - O(N)
 *
 * Space Complexity:
 * - O(N) {list} + O(H) {recursion stack}
 *
 *
 * Better Approach Intuition:
 * - Use BST property:
 *     left < root < right
 *
 * - Reverse inorder gives:
 *     right -> root -> left
 *
 * - This visits nodes in descending order.
 *
 * - Maintain:
 *     → count of visited nodes
 *     → answer
 *
 * - At every node:
 *     1. Visit right subtree
 *     2. Increment count
 *     3. If count == k
 *           → store answer
 *     4. Visit left subtree
 *
 * - Why it is better?
 * - No extra list needed.
 * - Uses BST ordering directly.
 *
 * Time Complexity:
 * - O(N)
 *
 * Space Complexity:
 * - O(H)
 *
 *
 * Optimal Approach (Used Below):
 * - Reverse inorder traversal:
 *
 *      right -> root -> left
 *
 * - BST guarantees descending order.
 *
 * - Maintain:
 *      count
 *      ans
 *
 * - Traverse:
 *
 *   1. Go right first:
 *        → larger values first
 *
 *   2. count++
 *
 *   3. If count == k:
 *        → store current node value
 *        → return
 *
 *   4. Explore left subtree
 *
 * - Why This Works?
 * - In BST:
 *
 *      left < root < right
 *
 * - Reverse inorder naturally visits:
 *
 *      largest
 *      second largest
 *      third largest
 *
 * - So when count reaches k:
 *      current node = kth largest
 *
 *
 * Time Complexity:
 * - O(N)
 *
 * Space Complexity:
 * - O(H)
 *
 *
 * Notes:
 * - Reverse inorder = descending order
 * - No sorting required
 * - Uses BST property directly
 *
 *
 * Common mistakes:
 * - Using normal inorder (gives kth smallest)
 * - Forgetting right subtree first
 * - Not handling k > number of nodes
 * - Continuing recursion after answer found
 *
 *
 * Mental model:
 * - "Walk BST from biggest to smallest"
 *
 *
 * Edge cases:
 * - Empty tree → return 0 / invalid
 * - Single node
 * - k = 1 → largest
 * - k = N → smallest
 * - Skewed BST
 *
 *
 * Key takeaway:
 * - BST ordering lets traversal answer ranking problems directly
 *
 *
 * Follow-up:
 * - Kth smallest in BST
 * - Iterative reverse inorder using stack
 * - Maintain subtree sizes for O(log N) query
 */

package trees._6_BST;

public class KthLargestElementInBST {
    int ans = 0, count = 0;

    public int kthLargest(Node root, int k) {

        if(root == null) return 0;

        reverseInorder(root, k);

        return ans;
    }

    public void reverseInorder(Node root, int k){

        if(root == null) return;

        reverseInorder(root.right, k);
        count++;

        if(count == k){
            ans = root.data;
            return;
        }

        reverseInorder(root.left, k);
    }
}

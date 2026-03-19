/**
 * Problem:
 * <Kth Smallest Element in a BST>
 *
 * Link:
 * <https://leetcode.com/problems/kth-smallest-element-in-a-bst/description/>
 *
 * Pattern:
 * <BST>
 *
 * Brute Force Intuition:
 * - Traverse entire tree and store all values in a list.
 * - Sort the list.
 * - Return (k-1)th element.
 *
 * - Why it is inefficient?
 * - Sorting takes extra O(N log N) time.
 *
 * Time Complexity:
 * - O(N log N)
 * Space Complexity:
 * - O(N)
 *
 * Better Approach Intuition:
 * - Use inorder traversal (BST property -> sorted order).
 * - Store elements in list during traversal.
 * - Return (k-1)th element.
 *
 * - Why it is still not optimal?
 * - Uses extra space.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Optimal Approach (Used Below):
 * - Inorder traversal without storing all the elements.
 * - Maintain a counter.
 * - Traverse left -> root -> right.
 * - Decrement k at each node.
 * - When k == 0 -> return that node.
 *
 * - Why this works?
 * - Stops early (no full traversal required).
 * - No extra storage needed.
 *
 * Time Complexity:
 * - O(N)
 *
 * Space Complexity:
 * - O(H) {Recursion stack}
 *
 * Notes:
 * Pattern Recognition:
 * - BST + kth smallest/largest ->
 *   Think: Inorder + Counter (NOT heap)
 */

package trees._6_BST;
import trees.TreeNode;

public class KthSmallestElementInABST {
    int ans = 0, count = 0;

    public int kthSmallest(TreeNode root, int k) {
        inOrder(root, k);
        return ans;
    }

    public void inOrder(TreeNode node, int k){

        if(node == null) return;

        inOrder(node.left, k);
        count++;

        if(count == k){
            ans = node.val;
            return;
        }
        inOrder(node.right, k);
    }
}

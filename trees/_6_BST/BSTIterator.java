/**
 * Problem:
 * <Binary Search Tree Iterator>
 *
 * Link:
 * <https://leetcode.com/problems/binary-search-tree-iterator/description/>
 *
 * Pattern:
 * <BST + Inorder Traversal + Stack (Lazy Traversal)>
 *
 * Brute Force Intuition:
 * - Perform inorder traversal of the tree.
 * - Store values in a list.
 * - Initialize index = 0.
 * - hasNext():
 *  - Check whether index is in bounds.
 * - next():
 *  - Return the curr element and increment the index.
 *
 * - Why it is inefficient?
 * - Does not use BST property.
 * - Uses extra space.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N) {list + recursion stack}
 *
 *
 * Better / Optimal Approach Intuition:
 * - Use stack to simulate the inorder traversal.
 * - Perform traversal lazily (on-demand).
 * - After initialization:
 *  - Push all the left nodes in the stack.
 * - Keep the smallest and unvisited node in the top of teh stack.
 * - next():
 * - Pop the top node from the stack.
 * - If the node has right children:
 *  - Push all the left nodes of the right subtree.
 * - Return node.val.
 * - hasNext():
 * - Check if stack is not empty.
 *
 * - Why This Works?
 * - Inorder traversal left -> root -> right.
 * - Stack maintains path to the smallest node.
 * - Ensures nodes are processed in a sorted order.
 *
 * Time Complexity:
 * - next() -> O(1) amortized {Each node is pushed and popped once}
 *
 * Space Complexity:
 * - O(H) {Height of tree}
 *
 *
 * Notes:
 * - This is a LAZY traversal (important concept).
 * - We do NOT store entire traversal.
 * - Stack always contains path to next smallest element.
 *
 *
 * Common mistakes:
 * - Forgetting to push left nodes of right subtree
 * - Thinking next() is always O(1) (it is amortized)
 * - Using full inorder list (not optimal)
 *
 *
 * Mental model:
 * - "Simulate recursion using stack and always go left first"
 *
 *
 * Edge cases:
 * - Empty tree
 * - Single node tree
 * - Skewed tree (space becomes O(N))
 *
 *
 * Key takeaway:
 * - Iterator problems = process elements ON DEMAND, not upfront
 *
 *
 * Follow-up:
 * - Reverse BST Iterator:
 *     → Push RIGHT nodes instead of LEFT
 *
 * - Bidirectional iterator:
 *     → Combine forward + reverse iterators
 */

package trees._6_BST;

import trees.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class BSTIterator {
    Deque<TreeNode> stack = new ArrayDeque<>();

    public BSTIterator(TreeNode root) {
        pushAllLeft(root);
    }

    private void pushAllLeft(TreeNode node) {
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
    }

    public int next() {
        TreeNode node = stack.pop();

        if (node.right != null) {
            pushAllLeft(node.right);
        }

        return node.val;
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }
}

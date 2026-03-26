/**
 * Problem:
 * <Tree Boundary Traversal>
 *
 * Link:
 * <https://www.geeksforgeeks.org/problems/boundary-traversal-of-binary-tree/1>
 *
 * Pattern:
 * <Tree Traversal>
 *
 * Brute Force Intuition:
 * - Try to simulate the boundary traversal using:
 *  - Left View.
 *  - Boundary View (leaves).
 *  - Right View (reverse order).
 * - Compute:
 *  - Left view, bottom view and right view.
 * - Add the nodes to the final list carefully to avoid duplicate nodes.
 *
 * - Why it is inefficient?
 * - Views != boundary.
 * - Hard to avoid duplicates.
 *
 * Time Complexity:
 * - O(N log N)
 * Space Complexity:
 * - O(N)
 *
 * Better Approach Intuition:
 * - Break problem into 3 parts:
 *  - Left boundary (excluding leaves).
 *  - Leaf nodes (all leaves from left -> right).
 *  - Right boundary (excluding leaves, reverse order).
 * - Steps:
 *  - Traverse left boundary using iteration.
 *  - Traverse leaf nodes using DFS.
 *  - Traverse right boundary using iteration and store in a stack.
 *  - Combine all the parts.
 *
 * - Why it is better?
 * - Clear logic separation.
 * - Avoids unnecessary structure.
 * - Easier duplicate handling.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Optimal Approach (Used Below):
 * - Perform 3 traversals:
 *
 *   Step 1: Add root (if not leaf)
 *   Step 2: Add left boundary (top-down)
 *   Step 3: Add all leaf nodes (iterative DFS)
 *   Step 4: Add right boundary (bottom-up using stack)
 *
 * - Key Idea:
 *   - Each node should be visited once
 *   - Leaves should not be duplicated
 *   - Avoid recursion for deep trees (StackOverflow risk)
 *
 * - Steps:
 * - Define helper:
 *     isLeaf(node)
 *
 * - Left Boundary:
 *     Traverse root.left
 *     Prefer left → else right
 *     Skip leaf nodes
 *
 * - Leaf Nodes (IMPORTANT FIX):
 *     Use iterative DFS (stack)
 *     Avoid recursion (deep tree → StackOverflowError)
 *
 * - Right Boundary:
 *     Traverse root.right
 *     Prefer right → else left
 *     Store in stack
 *     Add in reverse
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N) (explicit stack + result)
 *
 * DFS Approach (Alternative):
 * - Recursive leaf traversal works for small trees
 * - NOT safe for deep/skewed trees
 *
 * - Risk:
 *   - Recursion depth = O(N)
 *   - Causes StackOverflowError in large inputs
 *
 * Notes:
 * - Root should be added only once
 *
 * - Why leaves are separate:
 *   - They appear in both left and right boundary otherwise
 *
 * - Why left boundary excludes leaves:
 *   - To avoid duplication
 *
 * - Why right boundary is reversed:
 *   - Boundary must be anti-clockwise
 *
 * - Difference from Views:
 *   - Left/Right view → visibility based
 *   - Boundary → structural outer nodes
 *
 * - Common mistakes:
 *   - Including leaf nodes in left/right boundary
 *   - Forgetting to reverse right boundary
 *   - Using view-based logic (incorrect)
 *   - Adding root twice
 *   - Using recursive leaf traversal (can cause StackOverflow)
 *
 * - Mental model:
 *   - Walk around the tree anti-clockwise:
 *     root → left edge → leaves → right edge (reverse)
 *
 * - Edge cases:
 *   - Empty tree → return []
 *   - Single node → return [root]
 *   - Left skewed tree → all nodes in left boundary
 *   - Right skewed tree → careful with duplication
 *   - Deep tree → must avoid recursion for leaves
 *
 * - Key takeaway:
 *   - This is NOT a level problem
 *   - This is NOT a view problem
 *   - This is a STRUCTURE traversal problem
 *   - Prefer iterative traversal for robustness
 */

package trees._8_Views;

import trees.Node;

import java.util.ArrayList;
import java.util.Deque;
import java.util.ArrayDeque;

public class TreeBoundaryTraversal {
    ArrayList<Integer> boundaryTraversal(Node root) {
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null) return res;

        if (!isLeaf(root)) res.add(root.data);

        addLeftBoundary(root, res);
        addLeaves(root, res);
        addRightBoundary(root, res);

        return res;
    }

    public boolean isLeaf(Node node) {
        return node.left == null && node.right == null;
    }

    public void addLeftBoundary(Node node, ArrayList<Integer> res) {
        Node curr = node.left;

        while (curr != null) {

            if (!isLeaf(curr)) res.add(curr.data);

            if (curr.left != null) curr = curr.left;
            else curr = curr.right;
        }
    }

    public void addLeaves(Node node, ArrayList<Integer> res) {

        if (node == null) return;

        Deque<Node> dq = new ArrayDeque<>();
        dq.push(node);

        while (!dq.isEmpty()) {

            Node curr = dq.pop();

            if (isLeaf(curr)) {
                res.add(curr.data);
            } else {
                if (curr.right != null) dq.push(curr.right);
                if (curr.left != null) dq.push(curr.left);
            }
        }
    }

    public void addRightBoundary(Node node, ArrayList<Integer> res) {
        Node curr = node.right;
        Deque<Integer> dq = new ArrayDeque<>();

        while (curr != null) {

            if (!isLeaf(curr)) dq.push(curr.data);

            if (curr.right != null) curr = curr.right;
            else curr = curr.left;
        }

        while (!dq.isEmpty()) res.add(dq.pop());
    }
}

/**
 * Problem:
 * <Symmetric Tree>
 *
 * Link:
 * <https://leetcode.com/problems/symmetric-tree/description/>
 *
 * Pattern:
 * <Symmetry>
 *
 * Brute Force Intuition:
 * - BFS using queue.
 * - Store all the nodes including nulls at the same level.
 * - Store the level.
 * - After traversing all the levels:
 * - Iterate over the levels and check if the left child = right child using 2-pointers.
 *
 * - Why it is inefficient?
 * - Uses extra space to store the nodes of the trees.
 * - Another O(N) for checking the nodes at the same level.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Better Approach Intuition:
 * - BFS Pair-wise Mirror Check.
 * - Instead of storing all the levels.
 * - Push pairs of nodes root.left and root.right inside the queue.
 * - Compare n1.left vs n2.right.
 * - Compare n1.right vs n2.left.
 * - Offer the nodes to the queue.
 *
 * - Why it is still not optimal?
 * - Queue may store many nodes.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Optimal Approach (Used Below):
 * - Recursive DFS Approach.
 * - Two trees are mirror if:
 *   node1.val = node2.val.
 *   node1.right = node2.left.
 *   node1.left = node2.right.
 * - Split the tree from the root.
 * - Treat the left subtree and right subtree as two different trees.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Notes:
 * - O(H) Height = longest path from root to leaf.
 * - Each level of the tree adds one stack frame.
 * - Space used = number of recursive calls at one time.
 * - That equals the height of the tree.
 * - Height = longest path from root to leaf.
 * - Worst case = O(N).
 * - If the tree is skewed then it behaves like a linked list.
 */

package trees._2_DFS;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class SymmetricTree {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return dfs(root.left, root.right);
    }

    public boolean dfs(TreeNode p, TreeNode q) {

        if (p == null && q == null) return true;

        if (p == null || q == null) return false;

        if (p.val != q.val) return false;

        return dfs(p.left, q.right) && dfs(p.right, q.left);
    }
}

/**
 * Problem:
 * <Binary Tree Level Order Traversal>
 *
 * Link:
 * <https://leetcode.com/problems/binary-tree-level-order-traversal/description/>
 *
 * Definition:
 * - Level Order Traversal is a tree traversal technique where nodes of a binary tree are visited
 *   level by level from top to bottom, and within each level from left to right.
 *
 * Approach1:
 * - DFS Style recursion with level tracking (less natural).
 * - Instead of using BFS, we can use DFS Style recursion and track the level.
 * - When visiting a node:
 * - If level == list.size()
 *      add a new bucket for that level.
 * - Add the current node in its correct bucket.
 * - Traverse the left subtree.
 * - Traverse the right subtree.
 *
 * Time Complexities:
 * - O(N)
 *
 * Space Complexity:
 * - O(N)
 *
 Approach2:
 * - BFS using queue (natural).
 * - This is the standard solution.
 * - Use a queue:
 * - Push root.
 * - Process nodes level by level.
 * - At each level:
 *   Pop the front node.
 *   Store the popped nodes as they are the nodes at the current level in the temp list.
 *   Push the left and right children of the front node.
 * - Add the temp list to the final list.
 *
 * Time Complexities:
 * - O(N)
 *
 * Space Complexity:
 * - O(N)
 */
package trees._3_BFS_LevelOrder;

import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.ArrayDeque;

public class BinaryTreeLevelOrderTraversal {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res;

        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);

        while(!q.isEmpty()){

            int levelSize = q.size();
            List<Integer> level = new ArrayList<>();

            for(int i = 0; i < levelSize; i++){

                TreeNode node = q.poll();

                if(node.left != null) q.offer(node.left);
                if(node.right != null) q.offer(node.right);

                level.add(node.val);
            }

            res.add(level);
        }

        return res;
    }
}

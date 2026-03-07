/**
 * Problem:
 * <Binary Tree ZigZag Level Order Traversal>
 *
 * Link:
 * <https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/description/>
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
 * - After traversing all the levels:
 * - Traverse the final list.
 * - Reverse the sub-lists alternatively.
 *
 * Time Complexities:
 * - O(N)
 *
 * Space Complexity:
 * - O(N)
 *
 * Approach2:
 * - BFS using queue (natural).
 * - This is the standard solution.
 * - Use a queue:
 * - Push root.
 * - Process nodes level by level.
 * - At each level:
 *   Pop the front node.
 *   Store the popped nodes as they are the nodes at the current level in a temp list.
 *   Push the left and right children of the front node.
 * - Add the temp list to the final list.
 * - After traversing all the levels:
 * - Traverse the final list.
 * - Reverse the sub-lists alternatively.
 *
 * Time Complexities:
 * - O(N)
 *
 * Space Complexity:
 * - O(N)
 * Approach3:
 * - BFS using queue (natural) + LinkedList.
 * - This is the optimal solution because it prevents reversing the subLists list.
 * - Use a queue:
 * - Push root.
 * - Process nodes level by level.
 * - At each level:
 *   Pop the front node.
 *   If you are traversing from left to right:
 *   Add at the end of the linked list.
 *   Else:
 *   Add at the front of the linked list.
 * - Offer the children nodes back to the queue.
 * - Add the current list to the final list.
 *
 * Time Complexities:
 * - O(N)
 *
 * Space Complexity:
 * - O(N)
 */

package trees._3_BFS_LevelOrder;

import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Queue;
import java.util.ArrayDeque;

 class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode() {}
     TreeNode(int val) { this.val = val; }
     TreeNode(int val, TreeNode left, TreeNode right) {
         this.val = val;
         this.left = left;
         this.right = right;
     }
 }
public class BinaryTreeZigzagLevelOrderTraversal {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res;

        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        boolean leftToRight = true;

        while(!q.isEmpty()){
            int levelSize = q.size();
            LinkedList<Integer> temp = new LinkedList<>();

            while(levelSize-- > 0){

                TreeNode node = q.poll();

                if(leftToRight){
                    temp.addLast(node.val);
                }
                else{
                    temp.addFirst(node.val);
                }

                if(node.left != null) q.offer(node.left);
                if(node.right != null) q.offer(node.right);
            }
            leftToRight = !leftToRight;
            res.add(temp);
        }
        return res;
    }
}

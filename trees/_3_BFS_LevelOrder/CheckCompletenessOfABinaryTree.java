/**
 * Problem:
 * <Check Completeness of a Binary Tree>
 *
 * Link:
 * <https://leetcode.com/problems/check-completeness-of-a-binary-tree/description/>
 *
 * Pattern:
 * <BFS>
 *
 * Brute Force Intuition:
 * - Do level order traversal
 * - Store nodes including null in an array/list
 * - Then check:
 * - Once a null appears
 * - No non-null should appear after
 *
 * - Why it is inefficient?
 * - Uses extra space to store the nodes.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N) {queue + list}
 *
 * Better Approach Intuition:
 * - Assign indices to nodes like array representation.
 *   - Root = 1.
 *   - Left = 2*i
 *   - Right = 2*i + 1
 * - Count total nodes in tree.
 * - Traverse tree and check:
 *   - If any node index > total number of nodes -> NOT Complete.
 *
 * - Why it is still not optimal?
 * - Uses recursion + index tracking.
 * - Requires two passes (count + validation)
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(H)
 *
 * Optimal Approach (Used Below):
 * - Use BFS (Level Order Traversal)
 * - Push nodes in the queue.
 * - If a null node is encountered:
 *   - Mark seenNull = true.
 * - If a non-null is encountered after a null:
 *   - Return false.
 * - Otherwise continue traversal.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Notes:
 * - Complete Binary tree = no null-node appears after a null in BFS.
 * - This is a structure/position-based problem, not count-based.
 * - Always think BFS when:
 *   - Level order property
 *   - Left-to-right filling
 */

package trees._3_BFS_LevelOrder;

import java.util.Queue;
import java.util.LinkedList;

public class CheckCompletenessOfABinaryTree {
    public boolean isCompleteTree(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        boolean seenNull = false;

        while (!q.isEmpty()) {
            TreeNode curr = q.poll();

            if (curr == null) {
                seenNull = true;
            } else {
                if (seenNull) return false;

                q.offer(curr.left);
                q.offer(curr.right);
            }
        }
        return true;
    }
}

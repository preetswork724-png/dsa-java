/**
 * Problem:
 * <Diagonal Tree Traversal>
 *
 * Link:
 * <https://www.geeksforgeeks.org/problems/diagonal-traversal-of-binary-tree/1>
 *
 * Pattern:
 * <Tree Traversal>
 *
 * Brute Force Intuition:
 * - Group nodes by diagonal number.
 * - Right child stays on the same diagonal.
 * - Left child stays on the next diagonal.
 * - Rule:
 *  - root -> d = 0.
 *  - left -> d + 1.
 *  - right -> d.
 * - Use TreeMap<diagonal, List<values>>
 *  - Map nodes by their diagonal number.
 *
 * - Why it is inefficient?
 * - Sorting overhead.
 * - Uses recursion -> risk of Stack Overflow.
 * - Extra space for map.
 *
 * Time Complexity:
 * - O(N log N)
 * Space Complexity:
 * - O(N)
 *
 * Better Approach Intuition:
 * - Process one diagonal at a time using queue.
 * - Right child lie on the same diagonal.
 * - Left child lie on the next diagonal.
 * - Push root in the queue.
 * - While queue not empty:
 *  - Take node from queue.
 *  - Traverse the right chain:
 *    - Add the value of the right child in the final list.
 *    - Push the left child in the queue.
 *
 * - Why it is better?
 * - Avoids sorting.
 * - More direct simulation of diagonals.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Optimal Approach (Used Below):
 * - Use queue + iterative traversal
 *
 * - Key Idea:
 *   - Right traversal → same diagonal
 *   - Left child → next diagonal (store in queue)
 *
 * - Steps:
 * - Initialize queue with root
 * - While queue not empty:
 *   - Pop node
 *   - Traverse along right:
 *       → Add node to result
 *       → If left exists, push into queue
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * DFS Approach (Alternative):
 * - Use recursion with diagonal index
 *
 * - Risk:
 *   - Deep tree → recursion depth = O(N)
 *   - Can cause StackOverflowError
 *
 * Notes:
 * - Diagonal traversal is NOT level order
 *
 * - Why right traversal:
 *   - Right nodes lie on same diagonal
 *
 * - Why left goes to queue:
 *   - Left nodes belong to next diagonal
 *
 * - Queue meaning:
 *   - Stores starting nodes of future diagonals
 *
 * - Difference from other traversals:
 *   - Level order → level based
 *   - Vertical → column based
 *   - Boundary → outer nodes
 *   - Diagonal → slope-based grouping
 *
 * - Common mistakes:
 *   - Treating as level order traversal
 *   - Not traversing full right chain
 *   - Forgetting to push left nodes
 *   - Using recursion without considering depth limits
 *
 * - Mental model:
 *   - Move along slope (-1):
 *     right → same diagonal
 *     left → next diagonal
 *
 * - Edge cases:
 *   - Empty tree → return []
 *   - Single node → return [root]
 *   - Skewed tree → behaves like linear traversal
 *
 * - Key takeaway:
 *   - This is a STRUCTURE + MOVEMENT problem
 *   - Not a level-based or view-based problem
 *   - Queue simulates diagonal layers efficiently
 */

package trees._8_Views;

import trees.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class DiagonalTreeTraversal {

    public ArrayList<Integer> diagonal(Node root) {
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null) return res;

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {

            Node curr = queue.poll();

            while (curr != null) {

                res.add(curr.data);

                if (curr.left != null) queue.offer(curr.left);

                curr = curr.right;
            }
        }
        return res;
    }
}

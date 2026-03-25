/**
 * Problem:
 * <Vertical Order Traversal of a Binary Tree>
 *
 * Link:
 * <https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/description/>
 *
 * Pattern:
 * <BFS/DFS + Sorting>
 *
 * Brute Force Intuition:
 * - Traverse the tree using DFS.
 * - Store nodes as (row, col, value).
 * - After traversal:
 * - Sort the nodes by:
 *  - columns (left -> right)
 *  - rows (top -> bottom)
 *  - values (if same row and same column)
 * - Group the nodes column-wise.
 *
 * - Why it is inefficient?
 * - Requires sorting the entire dataset -> heavy operation.
 * - No structure is maintained while traversal.
 * - Sorting dominates the performance.
 *
 * Time Complexity:
 * - O(N log N)
 * Space Complexity:
 * - O(N)
 *
 * Better Approach Intuition:
 * - Instead of global sorting i.e sorting everything:
 * - We group the nodes by column and then sort the corresponding nodes.
 * - Use:
 *  - TreeMap<col, List<row, value>>.
 * - Use DFS traversal:
 * - For each column:
 *  - If it is absent in the map, add a new list corresponding to that column.
 *  - Else, add the nodes by (row, value).
 * - After traversal:
 *  - For each column in the map:
 *    - Sort the list of nodes stored corresponding to that column by (row, value).
 *    - Add the value of the nodes to a temp list and finally add the list to the final ans.
 *
 * - Why it is better?
 * - Just sorts the node by (row, value) instead of global sorting.
 *
 * Time Complexity:
 * - O(N log N)
 * Space Complexity:
 * - O(N)
 *
 * Optimal Approach (Used Below):
 * - Use BFS (level order traversal).
 * - Traverse level by level, so that the row order is maintained.
 * - Now, we only need to sort the columns (left -> right) and the values (if same row and same column).
 * - Use:
 *  - TreeMap<col, TreeMap<row, PriorityQueue<values>>
 *  - TreeMap col -> to maintain left to right order.
 *  - TreeMap row -> to maintain row order.
 *  - Priority Queue (min Heap) -> To handle sorting for same rows and same columns.
 * - Store nodes in the map as:
 *  - map[row][col] -> Priority Queue (min Heap).
 * - After traversal:
 *  - Iterate columns-wise.
 *  - Then row-vise.
 *  - Extract elements from PQ (sorted).
 *
 * Time Complexity:
 * - O(N log N) {Insertions into the map}
 * Space Complexity:
 * - O(N)
 *
 * Notes:
 * - Ordering rules (VERY IMPORTANT):
 *   1. Column → left to right
 *   2. Row → top to bottom
 *   3. Value → ascending (if same row & col)
 *
 * - Why BFS is preferred over DFS?
 *   - BFS naturally processes nodes level-wise (row order).
 *   - DFS requires explicit row tracking and later sorting.
 *
 * - Data structure intuition:
 *   - TreeMap:
 *     - Maintains sorted order of columns and rows.
 *   - PriorityQueue:
 *     - Handles tie-breaking (same row & col).
 *
 * - Common mistakes:
 *   - Only grouping by column (missing row sorting).
 *   - Ignoring tie condition (same row & column).
 *   - Using HashMap instead of TreeMap → unordered output.
 *   - Not handling duplicates properly.
 *
 * - Mental model:
 *   - Think of nodes placed on a 2D grid:
 *       row increases downward
 *       col moves left (-1) and right (+1)
 *   - We are grouping vertical slices of this grid.
 *
 * - Why can't we avoid sorting completely?
 *   - Because same (row, col) can have multiple values.
 *   - Problem explicitly requires sorted order in that case.
 *
 * - Alternative thought:
 *   - DFS + sorting works, but less elegant.
 *   - BFS + structured maps avoids explicit global sorting.
 *
 * - Interview tip:
 *   - Always clarify tie-breaking rule:
 *     "If same row and column, sort by value."
 *
 * - Edge cases:
 *   - Single node → return [[root]]
 *   - Left skewed tree → each node new column
 *   - Multiple nodes same position → ensure sorting
 *
 * - Key takeaway:
 *   - This problem is NOT just traversal.
 *   - It is traversal + ordering + data structure design.
 */

package trees._8_Views;

import trees.TreeNode;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.LinkedList;

public class VerticalOrderTraversalOfABinaryTree {
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        Map<Integer, TreeMap<Integer, PriorityQueue<Integer>>> map = new TreeMap<>();
        Queue<Object[]> queue = new LinkedList<>();
        queue.offer(new Object[]{root, 0, 0});

        while (!queue.isEmpty()) {

            Object[] curr = queue.poll();
            TreeNode node = (TreeNode) curr[0];
            int row = (int) curr[1];
            int col = (int) curr[2];

            map.putIfAbsent(col, new TreeMap<>());
            map.get(col).putIfAbsent(row, new PriorityQueue<>());
            map.get(col).get(row).offer(node.val);

            if (node.left != null) {
                queue.offer(new Object[]{node.left, row + 1, col - 1});
            }

            if (node.right != null) {
                queue.offer(new Object[]{node.right, row + 1, col + 1});
            }
        }

        List<List<Integer>> res = new ArrayList<>();

        for (TreeMap<Integer, PriorityQueue<Integer>> rows : map.values()) {

            List<Integer> colList = new ArrayList<>();

            for (PriorityQueue pq : rows.values()) {
                while (!pq.isEmpty()) {
                    colList.add((int) pq.poll());
                }
            }

            res.add(new ArrayList<>(colList));
        }
        return res;
    }
}

/**
 * Problem:
 * <Longest Univalue Path>
 *
 * Link:
 * <https://leetcode.com/problems/longest-univalue-path/description/>
 *
 * Pattern:
 * <Modified Diameter of Tree + DFS (Postorder)>
 *
 * Brute Force Intuition:
 * - For every node:
 *  - Consider it as a starting point.
 *  - Try to expand in path in left and right directions if the value matches.
 *  - Check longest same-value path in the left subtree.
 *  - Check longest same-value path in the right subtree.
 *  - Combine if both match.
 *
 * - Why it is inefficient?
 * - Repeated work for each node.
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(H)
 *
 * Better Approach Intuition:
 * - Store results for each node:
 *  - Store longest-univalue path for a node.
 * - Use:
 *  - Map<node, Integer> memo.
 *
 * - Why it is better?
 * - Avoids recomputation.
 *
 * - Why it is still bad?
 * - Uses extra space.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Optimal Approach (Used Below):
 * - Use postorder DFS (diameter - like logic with condition).
 * - At each node, compute longest same-value path downward.
 * - Update global maximum using left + right paths.
 * - Steps:
 * - 1. Traverse tree using postorder DFS.
 * - 2. For each node:
 *      - Get left and right child.
 * - 3. If child value matched current node value:
 *      Extend path (left + 1 or right + 1).
 * - 4. Update global max:
 *      - max = max (max, leftPath + rightPath)
 * - 5. Return:
 *      - max(leftPath, rightPath) -> only one direction.
 *
 * - Why only one direction returned?
 * - Parent can extend only one path.
 *
 * - Why both used in max?
 * - Path THROUGH node can include both sides.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(H) {Recursion stack}
 *
 * Notes:
 * - Path length is measured in EDGES (not nodes).
 *
 * - Difference from Diameter:
 *   - Diameter → no constraint
 *   - Univalue → values must match
 *
 * - Why no memoization:
 *   - Each node processed once in DFS.
 *   - No overlapping subproblems.
 *
 * - Why postorder traversal:
 *   - Need child results before parent computation.
 *
 * - Common mistakes:
 *   - Returning left + right (incorrect)
 *   - Forgetting value check
 *   - Using BFS unnecessarily
 *   - Adding memoization without need
 *
 * - Mental model:
 *   - "How far can I extend same-value path downward?"
 *
 * - Edge cases:
 *   - Single node → 0
 *   - All nodes same → full tree path
 *   - No matching values → 0
 *
 * - Key takeaway:
 *   - Tree + constraint → modify diameter logic
 *   - DFS postorder naturally avoids recomputation.
 */

package trees._9_Advanced;

import trees.TreeNode;

public class LongestUnivaluePath {
    int max = 0;

    public int longestUnivaluePath(TreeNode root) {
        dfs(root);
        return max;
    }

    public int dfs(TreeNode node) {

        if (node == null) return 0;

        int left = dfs(node.left);
        int right = dfs(node.right);

        int leftPath = 0, rightPath = 0;

        if (node.left != null && node.left.val == node.val) {
            leftPath = left + 1;
        }

        if (node.right != null && node.right.val == node.val) {
            rightPath = right + 1;
        }

        max = Math.max(max, leftPath + rightPath);
        int best = Math.max(leftPath, rightPath);
        return best;
    }
}

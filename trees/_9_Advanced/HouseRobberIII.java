/**
 * Problem:
 * <House Robber III>
 *
 * Link:
 * <https://leetcode.com/problems/house-robber-iii/>
 *
 * Pattern:
 * <Tree DP (Postorder DFS) + Include/Exclude Strategy>
 *
 * Brute Force Intuition:
 * - Every node gets to act as the first robbed house.
 * - For every node:
 *  - Rob it -> skip its children -> consider grandchildren.
 *  - Skip it -> consider it grand children.
 * - Recursively compute the money.
 * - Track tha maximum globally.
 *
 * - Why it is inefficient?
 * - Repeated computation of subtrees.
 * - Same nodes evaluated multiple times.
 *
 * Time Complexity:
 * - O(2^N) {worst case}
 * Space Complexity:
 * - O(H)
 *
 * Better Approach Intuition:
 * - Use memoization (HashMap<TreeNode, Integer>).
 * - Store each node:
 *  - Store result of maximum money obtainable.
 * - Avoid recomputing subtrees.
 *
 * - Why it is better?
 * - Eliminates repeated calculations.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Optimal Approach (Used Below):
 * - Use Tree DP (Postorder traversal).
 * - For every node, compute two values:
 *  - 1. rob -> If we rob this node.
 *  - 2. notRob -> If we skip this node.
 * - Steps:
 *  1. Perform DFS (postorder).
 *  2. For each node:
 *      - Compute left[] and right[] results.
 *  3. Compute:
 *      - rob = node.val + left[1] + right[1].
 *      - notRob = max(left[0], left[1]) + max(right[0] + right[1]).
 *  4. Return pair [rob, notRob].
 *  5. Final answer:
 *     max(result[0], result[1]).
 *
 * - Why this works?
 * - Each node decides independently:
 *  - Take it -> skip children.
 *  - Skip it -> take children.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(H)
 *
 * Notes:
 * - This is NOT a graph/BFS problem.
 * - It is a decision-based DP problem.
 *
 * - Why postorder traversal:
 *   - Need children’s results before computing parent.
 *
 * - Why two states per node:
 *   - Captures both possibilities (rob / skip).
 *
 * - Difference from House Robber I:
 *   - Array → linear DP
 *   - Tree → branching DP
 *
 * - Common mistakes:
 *   - Using global max (wrong)
 *   - Not considering both states
 *   - Recomputing subtrees (TLE)
 *
 * - Mental model:
 *   - At every node:
 *     "Do I rob this house or not?"
 *
 * - Edge cases:
 *   - Empty tree → 0
 *   - Single node → return its value
 *
 * - Key takeaway:
 *   - Tree problems with choices → think DP on tree
 *   - Always consider:
 *     include vs exclude
 */

package trees._9_Advanced;

import trees.TreeNode;

public class HouseRobberIII {
    public int rob(TreeNode root) {
        int[] res = dfs(root);
        return Math.max(res[0], res[1]);
    }

    public int[] dfs(TreeNode node) {

        if (node == null) return new int[]{0, 0};

        int[] left = dfs(node.left);
        int[] right = dfs(node.right);

        int rob = node.val + left[1] + right[1];
        int notRob = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);

        return new int[]{rob, notRob};
    }
}

/**
 * Problem:
 * <Path Sum III>
 *
 * Link:
 * <https://leetcode.com/problems/path-sum-iii/description/>
 *
 * Pattern:
 * <DFS + Backtracking>
 *
 * Brute Force Intuition:
 * - For every node:
 *  - Treat it as a starting point of the tree.
 *  - Perform DFS to find all the downward paths where sum = targetSum.
 * - Total paths =
 *  - paths starting from root +
 *  - paths starting from left subtree +
 *  - paths starting from right subtree.
 *
 * - Why it is inefficient?
 * - For every node, we explore all paths again.
 * - Repeated work -> overlapping computations.
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(H)
 *
 * Better Approach Intuition:
 * - Same as brute.
 * - Still relies on DFS for every node.
 *
 * - Why it is still not optimal?
 * - Repeated computations.
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(H)
 *
 * Optimal Approach (Used Below):
 * - Use Prefix Sum + Hashing
 * - Maintain:
 *  - currSum: sum from root -> current node.
 *  - map: stores frequency of prefix sums.
 * - For each node:
 *  - currSum += node.val.
 *  - Check:
 *      - If (currSum - target) exists in the map.
 *      - number of such occurrences are the number of valid Paths ending here.
 *  - Add currSum to the map.
 *  - Recurse left and right.
 *  - Backtrack:
 *      - Remove currSum from the map.
 *
 * Why it works?
 * - currSum - prevSum = target.
 * - currSum - target = prevSum.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(H)
 *
 * Notes:
 * - Paths must be downward (parent → child only).
 * - Paths can start from ANY node.
 *
 * - Key Formula:
 *   - currSum - prevSum = target
 *
 * - Always initialize:
 *   - map.put(0, 1)
 *   - (handles paths starting from root)
 *
 * - Backtracking is critical:
 *   - map.put(currSum, freq + 1)
 *   - recurse
 *   - map.put(currSum, freq - 1)
 *
 * - Common Mistakes:
 *   - Using (target - currSum)
 *   - Forgetting backtracking
 *   - Not initializing map with (0 → 1)
 *
 * - Mental Model:
 *   - “Find how many previous prefix sums make current path valid”
 */

package trees._4_PathProblems;

import trees.TreeNode;

import java.util.Map;
import java.util.HashMap;

public class PathSumIII {
    public int pathSum(TreeNode root, int targetSum) {
        Map<Long, Integer> map = new HashMap<>();
        map.put(0L, 1);

        return dfs(root, 0, targetSum, map);
    }

    public int dfs(TreeNode node, long currSum, int targetSum, Map<Long, Integer> map) {

        if (node == null) return 0;

        currSum += node.val;

        int count = map.getOrDefault(currSum - targetSum, 0);

        map.put(currSum, map.getOrDefault(currSum, 0) + 1);

        count += dfs(node.left, currSum, targetSum, map);
        count += dfs(node.right, currSum, targetSum, map);

        map.put(currSum, map.get(currSum) - 1);
        return count;
    }
}

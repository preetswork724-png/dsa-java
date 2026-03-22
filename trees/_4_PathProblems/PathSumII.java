/**
 * Problem:
 * <Path Sum II>
 *
 * Link:
 * <https://leetcode.com/problems/path-sum-ii/description/>
 *
 * Pattern:
 * <DFS + Backtracking>
 *
 * Brute Force Intuition:
 * - Generate all the root to leaf paths.
 * - Store all the paths.
 * - For each path:
 *  - Compute the sum of node values.
 *  - Check if it equals targetSum.
 *  - If it does, add that path to the result.
 *
 * - Why it is inefficient?
 * - Stores all paths explicitly.
 * - Recomputes sum for each path.
 *
 * Time Complexity:
 * - O(N * H)
 * Space Complexity:
 * - O(N * H)
 *
 * Better Approach Intuition:
 * - DFS traversal + backtracking.
 * - Maintain:
 *  - currPath list.
 *  - remaining target sum.
 * - At each node:
 *  - Add current node.val to the list.
 *  - Reduce the targetSum by node.val.
 * - At leaf node:
 *  - Check if targetSum == node.val
 *  - If it does, add the current path.
 * - Backtrack after recursion:
 *  - Remove last element from path.
 *
 * - Why it is better?
 * - Avoids recomputing sum.
 * - Build paths incrementally.
 *
 * Time Complexity:
 * - O(N * H)
 * Space Complexity:
 * - O(H)
 *
 * Optimal Approach (Used Below):
 * - Same as better approach.
 * - DFS with running sum.
 *
 * Time Complexity:
 * - O(N * H)
 * Space Complexity:
 * - O(H) {Size of list + recursion stack}
 *
 * Notes:
 * - Only root to leaf paths are valid.
 * - Do not check the sum at null node.
 * - Leaf condition is critical:
 *  - node.left == null && node.right == null
 * - Always store copy of the path.
 * - Backtracking is mandatory:
 *  - ADD -> RECURSE -> REMOVE.
 * - "Build path while going down, clean it while coming up".
 */

package trees._4_PathProblems;

import trees.TreeNode;

import java.util.List;
import java.util.ArrayList;

public class PathSumII {
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> allPaths = new ArrayList<>();
        if (root == null) return allPaths;
        helper(root, targetSum, new ArrayList<>(), allPaths);
        return allPaths;
    }

    public void helper(TreeNode node, int targetSum, List<Integer> currPaths,
                       List<List<Integer>> allPaths) {

        if (node == null) return;

        currPaths.add(node.val);

        if (node.left == null && node.right == null) {
            if (targetSum == node.val) {
                allPaths.add(new ArrayList<>(currPaths));
            }
        } else {
            helper(node.left, targetSum - node.val, currPaths, allPaths);
            helper(node.right, targetSum - node.val, currPaths, allPaths);
        }
        currPaths.remove(currPaths.size() - 1);
    }
}

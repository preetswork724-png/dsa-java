/**
 * Problem:
 * <Print Path Between Two Nodes in a Binary Tree>
 *
 * Link:
 * <Custom / Common Tree Problem>
 *
 * Pattern:
 * <Root-to-Node Path + LCA>
 *
 *
 * Approach:
 *
 * - Find path from root -> n1
 *
 * - Find path from root -> n2
 *
 * - Compare both paths:
 *      common prefix = path till LCA
 *
 * - Build answer:
 *      - reverse remaining part of path1
 *      - add LCA
 *      - add remaining part of path2
 *
 *
 * Steps:
 *
 * - 1. If root == null:
 *        return empty list
 *
 * - 2. Find:
 *        path1 = root -> n1
 *        path2 = root -> n2
 *
 * - 3. Traverse both paths:
 *        while values are same
 *        move ahead
 *
 * - 4. i - 1 becomes LCA index
 *
 * - 5. Add:
 *        path1 from end -> i
 *
 * - 6. Add LCA
 *
 * - 7. Add:
 *        path2 from i -> end
 *
 * - 8. Return answer
 *
 *
 * findPath():
 *
 * - DFS traversal
 *
 * - Add current node
 *
 * - If target found:
 *      return true
 *
 * - Recurse:
 *      left / right
 *
 * - If not found:
 *      backtrack
 *
 *
 * Why this works?
 *
 * - Both paths start from root
 *
 * - Common prefix ends at LCA
 *
 * - Remaining path1:
 *      node1 -> LCA
 *
 * - Remaining path2:
 *      LCA -> node2
 *
 * - Combining gives full node-to-node path
 *
 *
 * Time Complexity:
 * - O(N)
 *
 *   path to n1 + path to n2 + merge
 *
 *
 * Space Complexity:
 * - O(H)
 *
 *   recursion + path lists
 *
 *
 * Common mistakes:
 *
 * - Forgetting to add target node in path
 *
 * - Using i++ instead of j++ in last loop
 *
 * - Missing LCA insertion
 *
 * - Forgetting backtracking
 *
 *
 * Mental model:
 *
 * - "root→n1
 *    root→n2
 *    remove common
 *    combine"
 *
 *
 * Key takeaway:
 *
 * - Node-to-node path =
 *   two root paths + LCA merge
 *
 */

package trees._1_Basics;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreeNodeToNodePath {

    public List<Integer> nodeToNode(TreeNode root, int n1, int n2) {
        List<Integer> ans = new ArrayList<>();

        if (root == null) return ans;

        List<Integer> path1 = new ArrayList<>();
        List<Integer> path2 = new ArrayList<>();

        findPath(root, n1, path1);
        findPath(root, n2, path2);

        int i = 0;

        while (i < path1.size() && i < path2.size() && path1.get(i).equals(path2.get(i))) {
            i++;
        }

        for (int j = path1.size() - 1; j >= i; j--) {
            ans.add(path1.get(j));
        }

        ans.add(path1.get(i - 1));

        for (int j = i; j < path2.size(); i++) {
            ans.add(path2.get(i));
        }

        return ans;
    }

    public boolean findPath(TreeNode root, int n, List<Integer> path) {

        if (root == null) return false;

        if (root.val == n) return true;

        path.add(root.val);

        if (findPath(root.left, n, path) || findPath(root.right, n, path)) return true;

        path.remove(path.size() - 1);
        return false;
    }
}

/**
 * Problem:
 * <Binary Tree Paths>
 *
 * Link:
 * <https://leetcode.com/problems/binary-tree-paths/>
 *
 * Pattern:
 * <DFS + Backtracking>
 *
 *
 * Approach:
 *
 * - Maintain current root-to-node path
 *
 * - DFS traversal:
 *      - add node
 *      - if leaf -> build string + store
 *      - recurse left/right
 *      - backtrack
 *
 *
 * Steps:
 *
 * - 1. Create result list
 *
 * - 2. Start DFS with:
 *        - root
 *        - result
 *        - empty path
 *
 * - 3. Base case:
 *        if root == null return
 *
 * - 4. Add current node
 *
 * - 5. If leaf:
 *        - convert path to string
 *        - add to result
 *        - backtrack + return
 *
 * - 6. DFS left + right
 *
 * - 7. Remove current node
 *
 * - 8. Return result
 *
 *
 * Why this works?
 *
 * - DFS explores every root-to-leaf path
 *
 * - path stores current route
 *
 * - Backtracking resets path
 *   before exploring sibling
 *
 *
 * Time Complexity:
 * - O(N * H)
 *
 *
 * Space Complexity:
 * - O(H)
 *
 *
 * Common mistakes:
 *
 * - Forgetting backtracking
 *
 * - Not handling leaf separately
 *
 * - Incorrect "->" placement
 *
 *
 * Mental model:
 *
 * - "Add → explore → remove"
 *
 *
 * Key takeaway:
 *
 * - Root-to-leaf path problems
 *   are classic DFS + backtracking
 *
 */

package trees._1_Basics;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreePaths {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();

        preorder(root, res, new ArrayList());

        return res;
    }

    public void preorder(TreeNode root, List<String> res, List<Integer> path){

        if(root == null) return;

        if(root.left == null && root.right == null){
            path.add(root.val);

            StringBuilder sb = new StringBuilder();

            for(int i = 0; i < path.size(); i++){
                sb.append(path.get(i));

                if(i != path.size() - 1){
                    sb.append("->");
                }
            }
            res.add(sb.toString());

            path.remove(path.size() - 1);
            return;
        }

        path.add(root.val);
        preorder(root.left, res, path);
        preorder(root.right, res, path);
        path.remove(path.size() - 1);
    }
}

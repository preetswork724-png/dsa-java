/**
 * Problem:
 * <Binary Tree Cameras>
 *
 * Link:
 * <https://leetcode.com/problems/binary-tree-cameras/>
 *
 * Pattern:
 * <Tree Greedy + DFS (Postorder) + State-Based Decisions>
 *
 * Brute Force Intuition:
 * - Try all possible subsets of nodes where camera can be placed.
 * - For each subset:
 *  - Check if all the nodes are covered.
 *  - Coverage rules:
 *    - Camera covers itself, parent, children.
 * - Track the minimum number of cameras globally.
 *
 * - Why it is inefficient?
 * - Exponential combination (2^N).
 * - Repeated coverage checks.
 * - Does not use tree structure.
 *
 * Time Complexity:
 * - O(2 ^ N * N)
 * Space Complexity:
 * - O(N)
 *
 * Better Approach Intuition:
 * - Convert Tree -> Graph using parent map.
 * - Use recursion with state tracking:
 *  - Maintain a set of covered nodes.
 *  - Try placing camera at each node.
 *  - Update coverage accordingly.
 *
 * - Why it is better?
 * - Prunes some invalid configuration.
 *
 * - Why it is still bad?
 * - Still exponential (2^N).
 * - Uses heavy-state tracking (set + map).
 *
 * Time Complexity:
 * - O(2^N)
 * Space Complexity:
 * - O(N)
 *
 * Optimal Approach (Used Below):
 * - Greedy + Postorder (DFS).
 * - Traverse the tree using postorder DFS.
 * - For each node:
 *  - Get left and the right subtree.
 * - Decision:
 *  - If any child is NOT_COVERED:
 *      - Place a camera at current node.
 *      - Return HAS_CAMERA
 *  - Else if any child HAS_CAMERA:
 *      - Return current node is COVERED.
 *  - Else:
 *      - Current node is NOT_COVERED.
 *  - Final Check:
 *      - If root is NOT_COVERED:
 *          - Add one more camera.
 *
 * - Why this works?
 * - Children decisions determine parent actions.
 * - Greedy placement ensures minimum cameras.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(H) {Recursion stack}
 *
 * Notes:
 * - This is NOT a traversal problem.
 * - This is a decision-based greedy problem.
 *
 * - Why postorder traversal:
 *   - Need children states before deciding for parent.
 *
 * - Why 3 states:
 *   - Binary state (rob/skip) is insufficient.
 *   - Need explicit tracking of coverage.
 *
 * - Why no parent map:
 *   - Decisions propagate bottom-up.
 *   - No need for upward traversal.
 *
 * - Common mistakes:
 *   - Trying BFS or graph approach
 *   - Using parent map unnecessarily
 *   - Not handling root separately
 *   - Thinking in terms of subsets
 *
 * - Mental model:
 *   - Leaves force camera placement on parents.
 *   - Cameras are placed as late (low) as possible.
 *
 * - Edge cases:
 *   - Single node → needs 1 camera
 *   - Skewed tree
 *   - Complete binary tree
 *
 * - Key takeaway:
 *   - Coverage problems → use states
 *   - Greedy + DFS works when children dictate parent decisions
 */

package trees._9_Advanced;

import trees.TreeNode;

public class BinaryTreeCameras {
    int cameras = 0;

    private static final int HAS_CAMERA = 0;
    private static final int COVERED = 1;
    private static final int NOT_COVERED = 2;

    public int minCameraCover(TreeNode root) {
        if (dfs(root) == NOT_COVERED) {
            cameras++;
        }
        return cameras;
    }

    public int dfs(TreeNode node) {

        if (node == null) return COVERED;

        int left = dfs(node.left);
        int right = dfs(node.right);

        if (left == NOT_COVERED || right == NOT_COVERED) {
            cameras++;
            return HAS_CAMERA;
        }

        if (left == HAS_CAMERA || right == HAS_CAMERA) {
            return COVERED;
        }
        return NOT_COVERED;
    }
}

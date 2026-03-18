/**
 * Problem:
 * <Flip Equivalent Binary Trees>
 *
 * Link:
 * <https://leetcode.com/problems/flip-equivalent-binary-trees/description/>
 *
 * Pattern:
 * <Symmetry>
 *
 * Brute Force Intuition:
 * - Try out all the possible configurations.
 * - At every node, you have 2 choices:
 *   Flip.
 *   No flip.
 * - Compare the values of the nodes and the structure at each step.
 * - Check if any combination works.
 *
 * - Why it is inefficient?
 * - Checking out all the combinations.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(H) {Recursion Stack : Height of tree} O(N) {Worst case for skewed trees}
 *
 * Better Approach Intuition:
 * - There is no meaningful intermediate "better" approach here, because:
 * - You must compare structure.
 * - You must consider both possibilities.
 *
 * Optimal Approach (Used Below):
 * - This problem is actually:
 * - Tree isomorphism with allowed swaps.
 * - At each node:
 * - "Are these two subtrees actually equivalent under swapping?"
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(H)
 *
 * Notes:
 * - Why time complexity is not exponential?
 * - Because every node is visited once.
 * - Constant work per node (2 checks).
 */

package trees._2_DFS;

public class FlipEquivalentBinaryTrees {
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        if(root1 == null && root2 == null) return true;
        if(root1 == null || root2 == null) return false;

        if(root1.val != root2.val) return false;

        boolean noFlip = flipEquiv(root1.left, root2.left) &&
                flipEquiv(root1.right, root2.right);

        boolean flip = flipEquiv(root1.left, root2.right) &&
                flipEquiv(root1.right, root2.left);

        return flip || noFlip;
    }
}

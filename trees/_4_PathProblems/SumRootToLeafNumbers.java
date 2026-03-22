/**
 * Problem:
 * <Sum Root to Leaf Numbers>
 *
 * Link:
 * <https://leetcode.com/problems/sum-root-to-leaf-numbers/description/>
 *
 * Pattern:
 * <DFS + Backtracking>
 *
 * Brute Force Intuition:
 * - Generate all the root to leaf paths.
 * - Store the paths.
 * - Convert each path to a number.
 * - Add the numbers represented as string to get the total sum of all the paths.
 *
 * - Why it is inefficient?
 * - Uses StringBuilder to build the numbers.
 * - Stores all the paths.
 * - Extra overhead due to string operations.
 *
 * Time Complexity:
 * - O(N * H)
 * Space Complexity:
 * - O(N)
 *
 * Better Approach Intuition:
 * - No need to store all the paths.
 * - Build the number incrementally using StringBuilder.
 * - For every node:
 *  - Append node.val.
 * - For leaf node:
 *  - Append node.val.
 *  - Parse the string to the number.
 *  - Add the number to the total sum.
 *
 * - Why it is still not optimal?
 * - Extra overhead due to string operations.
 *
 * Time Complexity:
 * - O(N * H)
 * Space Complexity:
 * - O(H)
 *
 * Optimal Approach (Used Below):
 * - DFS with running number.
 * - Start with curr = 0.
 * - Traverse the tree using DFS.
 * - Update curr at each node:
 *  - curr = curr * 10 + node.val.
 * - If leaf :
 *  - add curr to result.
 * - Recurse left and right.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(H)
 *
 * Notes:
 * - Digits are in range [0 - 9], number formation is valid.
 * - No backtracking needed for curr:
 *  - Because integers are passed by value.
 */

package trees._4_PathProblems;

import trees.TreeNode;

public class SumRootToLeafNumbers {
    int totalSum = 0;

    public int sumNumbers(TreeNode root) {
        if (root == null) return 0;
        helper(root, 0);
        return totalSum;
    }

    public void helper(TreeNode node, int currSum) {

        if (node == null) return;

        currSum = currSum * 10 + node.val;

        if (node.left == null && node.right == null) {
            totalSum += currSum;
        }

        helper(node.left, currSum);
        helper(node.right, currSum);
    }
}

/**
 * Problem:
 * <Construct BST from Postorder Traversal>
 *
 * Link:
 * <GFG - Construct BST from Postorder>
 *
 * Pattern:
 * <BST + Postorder + DFS + Recursion + Bounds>
 *
 *
 * Brute Force Intuition:
 * - Last element is root.
 * - Scan left:
 *   - smaller -> left subtree
 *   - greater -> right subtree
 * - Recurse on both parts.
 *
 * - Why inefficient?
 * - Repeated scanning for split index.
 *
 * Time Complexity:
 * - Worst: O(N²)
 * - Avg: O(N log N)
 *
 * Space Complexity:
 * - O(H)
 *
 *
 * Better / Optimal Approach:
 * - Traverse postorder from right to left.
 * - Use index + lower bound.
 * - If value < bound:
 *   - return null
 * - Else:
 *   - create node
 *   - build right subtree first
 *   - then left subtree
 *
 * - Why?
 * - Postorder = left -> right -> root
 * - Reverse = root -> right -> left
 * - Matches BST construction naturally.
 *
 * Time Complexity:
 * - O(N)
 *
 * Space Complexity:
 * - O(H)
 *
 *
 * Notes:
 * - Postorder:
 *   left -> right -> root
 *
 * - Reverse traversal:
 *   root -> right -> left
 *
 * - Last value always root.
 *
 * - Right subtree:
 *   values > root
 *
 * - Left subtree:
 *   values > inherited lower bound
 *
 * Base cases:
 * - index < 0
 * - postorder[index] < bound
 *
 * Important:
 * - Build right before left.
 *
 * Avoid:
 * - Sorting
 * - Repeated scanning
 *
 *
 * Mental model:
 * - Read from end.
 * - Ask:
 *   "Can this value fit here?"
 * - If yes:
 *   build node
 * - Else:
 *   return
 *
 *
 * Example:
 * postorder = [1,7,5,12,10,8]
 *
 *         8
 *       /   \
 *      5     10
 *     / \      \
 *    1   7      12
 *
 *
 * Common mistakes:
 * - Building left first
 * - Wrong bound check
 * - Traversing from start
 *
 *
 * Key takeaway:
 * - Reverse postorder + bound
 * - Build BST in one pass.
 */

package trees._5_Construction;

import trees.Node;

public class ConstructBSTFromPostorder {
    int postIndex;

    Node constructTree(int[] post) {
        postIndex = post.length - 1;
        return buildTree(post, Integer.MIN_VALUE);
    }

    Node buildTree(int[] post, int bound){

        if(postIndex < 0 || post[postIndex] < bound) return null;

        Node root = new Node(post[postIndex]);

        root.right = buildTree(post, post[postIndex--]);
        root.left = buildTree(post, bound);

        return root;
    }
}

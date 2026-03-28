/**
 * Problem:
 * <Floor in BST>
 *
 * Link:
 * <https://www.geeksforgeeks.org/problems/implementing-floor-in-bst/0>
 *
 * Pattern:
 * <BST Property>
 *
 * Brute Force Intuition:
 * - Perform inorder traversal (sorted order).
 * - Store elements in an array.
 * - Linearly scan to find:
 *   - Largest value <= x
 *
 * - Why it is inefficient?
 * - Full traversal + extra space + linear scan.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 *
 * Better Approach Intuition:
 * - Perform inorder traversal → sorted array.
 * - Use binary search to find:
 *   - Largest value <= x
 *
 * - Why it is better?
 * - Faster search than linear scan.
 *
 * - Why it is still bad?
 * - Still uses extra space.
 * - Still traverses entire tree unnecessarily.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 *
 * Optimal Approach (Used Below):
 * - Use BST property:
 *   - Left < Node < Right
 *
 * - Idea:
 *   - Traverse tree like binary search.
 *   - Keep track of potential floor.
 *
 * - Steps:
 *   - 1. Initialize:
 *        - floor = -1
 *
 *   - 2. While root != null:
 *
 *        - If root.val == x:
 *            - Exact match → return x
 *
 *        - If root.val > x:
 *            - Move left (need smaller value)
 *
 *        - If root.val < x:
 *            - Possible floor
 *            - Store root.val
 *            - Move right (try larger valid value)
 *
 *   - 3. Return floor
 *
 *
 * - Why this works?
 * - BST eliminates half search space each step.
 *
 * - Why move right after storing floor?
 * - To find a larger value still <= x.
 *
 *
 * Time Complexity:
 * - O(H) ≈ O(log N) (for balanced tree)
 *
 * Space Complexity:
 * - O(1)
 *
 *
 * Notes:
 *
 * - If no value <= x exists → return -1
 *
 * - Works only for BST (NOT binary tree)
 *
 * - Mirror of Ceil problem:
 *   - Ceil → smallest ≥ x
 *   - Floor → largest ≤ x
 *
 *
 * Common mistakes:
 *
 * - Traversing entire tree unnecessarily
 *
 * - Not updating floor before going right
 *
 * - Confusing ceil and floor conditions
 *
 *
 * Mental model:
 *
 * - "Whenever I go right, I found a possible answer"
 *
 *
 * Edge cases:
 *
 * - x greater than all nodes → largest node is floor
 *
 * - x smaller than all nodes → return -1
 *
 * - Exact match → return immediately
 *
 *
 * Key takeaway:
 *
 * - BST + comparison → think binary search on tree
 *
 */
package trees._6_BST;

import trees.Node;

public class FloorInBST {
    public int findFloor(Node root, int x) {

        int floor = -1;

        while (root != null) {

            if (root.data == x) return x;
            else if (root.data > x) {
                root = root.left;
            } else {
                floor = root.data;
                root = root.right;
            }
        }
        return floor;
    }
}

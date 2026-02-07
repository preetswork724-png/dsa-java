/**
 * Problem:
 * <Intersection of Two Linked Lists>
 *
 * Link:
 * <https://leetcode.com/problems/intersection-of-two-linked-lists/description/>
 *
 * Pattern:
 * <Two Pointer with switching head techniques>
 *
 * Brute Force Intuition:
 * - An intersection point in a linked list is a point at which the two lists merge and act as a single list afterward.
 * - For every node in A:
 * - Scan the entire list B.
 * - Return the first common node in list A and list B.
 *
 * - Why it is inefficient?
 * - Very slow for large lists.
 *
 * Time Complexity:
 * - O(N * M)
 * Space Complexity:
 * - O(1)
 *
 * Better Approach Intuition:
 * - Instead of scanning the list everytime:
 * - Store the nodes of list A in a HashSet.
 * - Iterate over the list B and then check the nodes.
 *
 * Time Complexity:
 * - O(N + M)
 * Space Complexity:
 * - O(N)
 *
 * Why it is still not optimal?
 * - Uses extra space.
 *
 * Optimal Approach (Used Below):
 * - Use 2-pointers.
 * - If we do: A+B or B+A.
 * - Both pointers travel the same distance.
 * - So, they must meet at intersection.
 * - After switching heads, the total distance becomes:
 * - p1 travels A+C+B.
 * - p2 travels B+A+C.
 *
 * Time Complexity:
 * - O(N + M)
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - The loop breaks after finding the intersection.
 */

package two_pointers;

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}
public class IntersectionOfTwoLinkedLists {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode tempA = headA, tempB = headB;

        while(tempA != tempB){
            tempA = (tempA != null) ? tempA.next : headB;
            tempB = (tempB != null) ? tempB.next : headA;
        }

        return tempA;
    }
}

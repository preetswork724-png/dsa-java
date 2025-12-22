/**
 * Problem:
 * <Middle of the Linked List>
 *
 * Link:
 * <https://leetcode.com/problems/middle-of-the-linked-list/description/>
 *
 * Pattern:
 * <Fast & Slow Pointer>
 *
 * Brute Force Intuition:
 * - Find the number of nodes in a Linked List i.e the size of the Linked List.
 * - Travel till one less the size.
 * - Return the middle node.
 *
 * - Why it is inefficient?
 * - Determining the size of the Linked List is unnecessary.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(1)
 *
 * Better Approach Intuition:
 * - Maintain a counter.
 * - Take a node, make it point to the head.
 * - Update the head in order to traverse the Linked List.
 * - Increment counter in every iteration, update the mid only when the counter points to an odd number.
 * - The algorithm just works like fast & slow pointer ut sounds unnecessarily complex.
 *
 * - Why it is still not optimal?
 * - Could not be generalises for many problems which require finding the middle node of the Linked List.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(1)
 *
 * Optimal Approach (Used Below):
 * - Use 2-pointers.
 * - Fast moves twice as slow, so when it finishes, slow is halfway.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - Fast and slow pointers may give a wrong middle if initialized wrong.
 */
package linkedlist;

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
public class MiddleOfTheLinkedList {

    public ListNode middleNode(ListNode head) {
        ListNode slow = head, fast = head;

        while(fast != null && fast.next != null){
            slow  = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

}

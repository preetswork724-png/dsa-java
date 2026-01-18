/**
 * Problem:
 * <Rotate List>
 *
 * Link:
 * <http://leetcode.com/problems/rotate-list/description/>
 *
 * Pattern:
 * <Linked List Rotation using circular linking>
 *
 * Brute Force Intuition:
 * - Convert the linked list to an array.
 * - Rotate the array k % size times.
 * - Rebuild a new Linked List from the nodes stored in the array
 *
 * - Why it is inefficient?
 * - Requires extra space.
 * - Avoids direct pointer manipulation.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Better Approach Intuition:
 * - Maintain a gap of size K between 2 pointers.
 * - Make slow and fast both point to the head.
 * - Do k = k % n.
 * - Move fast k steps ahead.
 * - Then move fast and slow together until fast reaches the last node in the list.
 * - Break at slow and rearrange.
 *
 * - Why it is still not optimal?
 * - Uses two pass.
 * - Does not reuse tail efficiency.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(1)
 *
 * Optimal Approach (Used Below):
 * - Compute length and last node.
 * - Make it circular.
 * - Normalize k.
 * - Find new tail.
 * - Break the circle.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - We temporarily connect the last node to the head to form a circular list.
 * - We must break the link between the newTail and newHead at the end to avoid a cycle.
 */
package linkedlist;

public class RotateList {

    public ListNode rotateRight(ListNode head, int k) {

        if(head == null || head.next == null || k == 0) return head;

        int size = 1;
        ListNode curr = head;

        while(curr.next != null){
            curr = curr.next;
            size++;
        }

        curr.next = head;
        k = k % size;
        int stepsToNewHead = size - k;
        ListNode newTail = curr;

        while(stepsToNewHead-- > 0){
            newTail = newTail.next;
        }

        ListNode newHead = newTail.next;
        newTail.next = null;
        return newHead;
    }

}

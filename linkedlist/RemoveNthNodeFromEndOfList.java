/**
 * Problem:
 * <Remove Nth Node From End of List>
 *
 * Link:
 * <https://leetcode.com/problems/remove-nth-node-from-end-of-list/description/>
 *
 * Pattern:
 * <Delete a node>
 *
 * Brute Force Intuition:
 * - Calculate the size of the Linked List.
 * - A nth node from the end is size - n from distance away from the start.
 * - Iterate from the start and delete the node just like you delete a node at an index.
 *
 * - Why it is inefficient?
 * - Requires extra pass when finding the size of the list is unnecessary to solve the problem.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(1)
 *
 * Better and Optimal Approach Intuition:
 * - Use fast & slow pointers.
 * - Move fast pointer till the nth position.
 * - Now, move fast pointer until it reaches the end of the list and slow pointer exactly to the size - n position from the start.
 * - This is only possible as the distance of n is always maintained between the fast and slow pointer.
 *
 * Why better approach is the optimal one?
 * - Because it solves the problem in a single pass.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - Only move slow when fast is n steps ahead.
 */
package linkedlist;

public class RemoveNthNodeFromEndOfList {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode fast = dummy, slow = dummy;

        for(int i = 0; i <= n; i++){
            fast = fast.next;
        }

        while(fast != null){
            slow = slow.next;
            fast = fast.next;
        }

        slow.next = slow.next.next;
        return dummy.next;
    }
}

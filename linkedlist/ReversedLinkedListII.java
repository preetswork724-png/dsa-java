/**
 * Problem:
 * <Reverse Linked List II>
 *
 * Link:
 * <https://leetcode.com/problems/reverse-linked-list-ii/>
 *
 * Pattern:
 * <Linked List>
 *
 * Brute Force Intuition:
 * - Add a dummy node as it handles the edge cases where left = 1 which means you have to reverse the sublist from the start.
 * - Traverse till the left node.
 * - Store the nodes from left to right in a list.
 * - Take the list which os compatible for storing the nodes and from the last index reverse the links.
 * - Reattach head and tail.
 *
 * - Why it is inefficient?
 * - Requires additional space.
 * - Avoids direct pointer manipulation.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Better Approach Intuition:
 * - Add the dummy node.
 * - Traverse till the left node.
 * - Reverse the sublist like we actually reverse the entire list.
 * - Reattach head and tail.
 *
 * - Why it is still not optimal?
 * - Uses two pass.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(1)
 *
 * Optimal Approach (Used Below):
 * - Add the dummy node.
 * - Traverse till the left node.
 * - Make one node as your left boundary which is invariant.
 * - At each step, pickup a next node and put it at the front of the list.
 * - One node represents the tail of the reversed part.
 * - Perform exactly right - left operations to reverse the sublist.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - Move prev exactly to the node before the actual node where the reversal of the sublist starts.
 */
package linkedlist;

public class ReversedLinkedListII {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if(head == null || left == right) return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;

        for(int i = 1; i < left; i++){
            prev = prev.next;
        }

        ListNode curr = prev.next;
        ListNode then = curr.next;

        for(int i = 0; i < right - left; i++){
            curr.next = then.next;
            then.next = prev.next;
            prev.next = then;
            then = curr.next;
        }

        return dummy.next;
    }
}

/**
 * Problem:
 * <Reorder List>
 *
 * Link:
 * <https://leetcode.com/problems/reorder-list/description/>
 *
 * Pattern:
 * <Fast & Slow Pointer>
 *
 * Brute Force Intuition:
 * - Store nodes of the list in some list which uses indexing.
 * - Have 2-pointers one at the start and another at the end.
 * - Build a new list by attaching nodes from the start and end alternately.
 * - Make the head point to the newly built list.
 *
 * - Why it is inefficient?
 * - Uses extra memory.
 * - Violates the in-place modification of the list.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Better Approach Intuition:
 * - Use fast and slow approach to traverse till the middle of the List.
 * - Store the second half of the list in the stack.
 * - Attach nodes from the head and the top of the stack alternately.
 *
 * - Why it is still not optimal?
 * - Uses extra space to store the second half of the list.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Optimal Approach (Used Below):
 * - Find the middle of the Linked List.
 * - Reverse the sublist from the middle.
 * - Detach the list from the middle.
 * - Build a new list by attaching nodes alternately from both the lists.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - Even though it looks like we are reconstructing the new list, but it's physically the same list.
 * - Because we are not creating any new nodes in the memory.
 * - We are just changing the next pointers.
 * - We are rearranging the list.
 */
package linkedlist;

public class ReorderList {
    public void reorderList(ListNode head) {
        ListNode slow = head, fast = head;

        while(fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode second = reverse(slow.next);
        slow.next = null;
        ListNode first = head;

        while(second != null){
            ListNode f1 = first.next;
            ListNode s1 = second.next;

            first.next = second;
            second.next = f1;

            first = f1;
            second = s1;
        }
    }

    public ListNode reverse(ListNode head){
        ListNode prev = null, curr = head;

        while(curr != null){
            ListNode tempNode = curr.next;
            curr.next = prev;
            prev = curr;
            curr = tempNode;
        }

        return prev;
    }
}

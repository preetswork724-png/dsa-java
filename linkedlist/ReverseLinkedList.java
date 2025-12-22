/**
 * Problem:
 * <Reverse Linked List>
 *
 * Link:
 * <https://leetcode.com/problems/reverse-linked-list/>
 *
 * Pattern:
 * <Linked List>
 *
 * Brute Force Intuition:
 * - Recursively traverse till the last node.
 * - As you reach the last node and the function starts returning. store all the intermediate nodes.
 * - Create a new LinkedList collecting all the nodes from the start till the end.
 *
 * - Why it is inefficient?
 * - Requires additional space and is slower.
 * - Instead of reversing the list, we are rebuilding a new list.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N) {Recursion stack + extra list}
 *
 * Better Approach Intuition:
 * - Recursively reverse the pointers from the tail.
 * - Traverse till the end of the linked list.
 * - The actual reversal starts from the second lastt node.
 *
 * - Why it is still not optimal?
 * - Uses extra space.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N) {Recursion stack}
 *
 * Optimal Approach (Used Below):
 * - Iterate the list and reverse the pointers during the iteration.
 * - Have a node which points to null.
 * - Have a curr node which points to the head.
 * - Store the next node to the curr in a temp node.
 * - Make the curr point to the previous.
 * - Update the prev as curr.
 * - And curr as temp.
 * - Repeat the process.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - Only update curr once you've updates prev.
 */
package linkedlist;

public class ReverseLinkedList {
    public ListNode reverseList(ListNode head) {
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

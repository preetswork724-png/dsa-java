/**
 * Problem:
 * <Remove Duplicates from Sorted List II>
 *
 * Link:
 * <https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/>
 *
 * Pattern:
 * <In-place two pointer>
 *
 * Brute Force Intuition:
 * - For each node, check its frequency.
 * - If frequency > 1, delete the curr node.
 * - Move forward.
 *
 * - Why it is inefficient?
 * - Scanning the entire list for each node takes O(N) everytime.
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(1)
 *
 * Better Approach Intuition:
 * - Use a Map to store the frequency of values of the nodes.
 *
 * - Why it is still not optimal?
 * - Uses extra space to store the values of the nodes.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Optimal Approach (Used Below):
 * - If the node's value is equal to its next node, then skip the node in between.
 * - Only update the node, after skipping all the adjacent nodes.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - In this approach, we are not constructing a new linked list.
 * - Instead, we skip all the nodes which have multiple occurrences.
 */

package linkedlist;

public class RemoveDuplicatesFromSortedListII {

    public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null) return head;

        ListNode dummy = new ListNode(-1), prev = dummy;
        dummy.next = head;
        ListNode curr = head;

        while(curr != null){
            boolean isDup = false;

            while(curr.next != null && curr.val == curr.next.val){
                isDup = true;
                curr = curr.next;
            }

            if(isDup){
                prev.next = curr.next;
            }
            else{
                prev = curr;
            }
            curr = curr.next;
        }
        return dummy.next;
    }
}

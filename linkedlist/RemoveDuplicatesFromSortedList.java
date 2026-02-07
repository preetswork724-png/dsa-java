/**
 * Problem:
 * <Remove Duplicates from Sorted List>
 *
 * Link:
 * <https://leetcode.com/problems/remove-duplicates-from-sorted-list/description/>
 *
 * Pattern:
 * <Deletion>
 *
 * Brute Force Intuition:
 * - Store only the unique numbers in a ArrayList.
 * - Create a new linked list.
 * - Iterate over the elements of the ArrayList and create a new list out of the nodes stored in the ArrayList.
 *
 * - Why it is inefficient?
 * - Checking if each value is already present in the list or not takes O(N) everytime.
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(N)
 *
 * Better Approach Intuition:
 * - Store the values of nodes inside a set.
 * - Create a new list of all the nodes stored in a set.
 *
 * - Why it is still not optimal?
 * - Uses extra space to store the values.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Optimal Approach (Used Below):
 * - If the node's value is equal to its next node, then skip the node in between.
 * - Only update the node, if the next node's value is different.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - Only iterate till the last node.
 */

package linkedlist;

public class RemoveDuplicatesFromSortedList {

    public ListNode deleteDuplicates(ListNode head) {

        if (head == null || head.next == null) return head;

        ListNode curr = head;

        while (curr != null && curr.next != null) {

            if (curr.val == curr.next.val) {
                curr.next = curr.next.next;
            } else {
                curr = curr.next;
            }
        }
        return head;
    }
}



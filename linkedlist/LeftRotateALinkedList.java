/**
 * Problem:
 * <Rotate a Linked List>
 *
 * Link:
 * <https://www.geeksforgeeks.org/problems/rotate-a-linked-list/1>
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
 * - Traverse once to get size.
 * - Compute effective rotation.
 * - Traverse again to cut at the right place.
 * - Stitch two parts.
 *
 * - Why it is still not optimal?
 * - Uses two pass.
 * - Does not reuse tail efficiency.
 *
 * Time Complexity:
 * - O(N) {two passes}
 * Space Complexity:
 * - O(1)
 *
 * Optimal Approach (Used Below):
 * - If you know the tail:
 * - Make the list circular.
 * - Cut it at the right spot.
 *
 * Time Complexity:
 * - O(N) {one pass}
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - We temporarily connect the last node to the head to form a circular list.
 * - We must break the link between the newTail and newHead at the end to avoid a cycle.
 */

package linkedlist;

public class LeftRotateALinkedList {

    public Node rotate(Node head, int k) {

        if (head == null || head.next == null)
            return head;

        Node curr = head;
        int size = 1;

        while (curr.next != null) {
            curr = curr.next;
            size++;
        }

        k = k % size;
        if (k == 0) return head;

        curr = head;
        for (int i = 1; i < k; i++)
            curr = curr.next;

        Node newHead = curr.next;
        curr.next = null;

        Node tail = newHead;
        while (tail.next != null)
            tail = tail.next;

        tail.next = head;
        return newHead;
    }
}

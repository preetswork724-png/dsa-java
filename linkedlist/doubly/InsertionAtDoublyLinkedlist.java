/**
 * Problem:
 * <Insertion in a Doubly Linked List>
 *
 * Link:
 * <https://www.geeksforgeeks.org/problems/insert-a-node-in-doubly-linked-list/1>
 *
 * Pattern:
 * <Insertion>
 *
 * Intuition:
 * - To insert in a doubly linked list, we need to adjust pointers on both the sides.
 * - Traverse till the node just before the given position.
 * - Carefully rewire 'next' and 'prev' pointers.
 *
 * Approach:
 * - Create a new node 'newNode' with the given data.
 * - Traverse the list till the (pos - 1)th node, call it as 'curr'.
 * - Let 'nextNode' = curr.next;
 * - Link the newNode in between:
 * - newNode.next = curr.next.
 * - newNode.prev = curr.
 * - curr.next = newNode
 * - if nextNode != null, then nextNode.prev = newNode.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - Edge case:
 * - If position == 1:
 *       - Insert at head
 *       - newNode.next = head
 *       - head.prev = newNode
 *       - update head
 * - If insertion is at the end:
 *       - nextNode will be null
 *       - Only adjust curr.next and newNode.prev
 * - Handle empty list separately.
 */

package linkedlist.doubly;

public class InsertionAtDoublyLinkedlist {

    Node insertAtPos(Node head, int p, int x) {

        Node newNode = new Node(x);

        if(head == null) return newNode;

        Node curr = head;

        for(int i = 0; i < p && curr != null; i++) curr = curr.next;

        newNode.next = curr.next;
        newNode.prev = curr;

        if(curr.next != null) curr.next.prev = newNode;

        curr.next = newNode;
        return head;
    }

}

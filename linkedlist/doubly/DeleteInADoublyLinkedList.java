/**
 * Problem:
 * <Delete in a Doubly Linked List>
 *
 * Link:
 * <https://www.geeksforgeeks.org/problems/delete-node-in-doubly-linked-list/1>
 *
 * Pattern:
 * <Deletion>
 *
 * Intuition:
 * - Traverse till the node just before the node to be deleted.
 * - Skip the node.
 * - Rewrite the pointers.
 * - Completely detach the node to be deleted by deleting its references.
 *
 * Approach:
 * - Lets say the node to be deleted is toDelete.
 * - Make the curr node point to toDelete.next.
 * - If toDelete node is not the last node in the linked list, then make the node next to it point to the current node.
 * - Rewrite the two pointers of the toDelete node as null.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - Edge case:
 * - Handle the case separately when the node to be deleted is the first node, because you need to move the head one step ahead.
 */

package linkedlist.doubly;
class Node {
    int data;
    Node next;
    Node prev;

    Node(int val) {
        data = val;
        next = null;
        prev = null;
    }
}
public class DeleteInADoublyLinkedList {
    public Node delPos(Node head, int x) {

        if(head == null) return head;

        if(x == 1){
            Node newHead = head.next;
            if(newHead != null){
                newHead.prev = null;
            }
            head.next = null;
            return newHead;
        }

        Node curr = head;


        for (int i = 1; i < x - 1 && curr.next != null; i++) {
            curr = curr.next;
        }


        if (curr.next == null) return head;

        Node toDelete = curr.next;

        curr.next = toDelete.next;

        if (toDelete.next != null) {
            toDelete.next.prev = curr;
        }


        toDelete.next = null;
        toDelete.prev = null;

        return head;
    }
}

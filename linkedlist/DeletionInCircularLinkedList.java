/**
 * Problem:
 * <Deletion in Circular Linked List>
 *
 * Link:
 * <https://www.geeksforgeeks.org/problems/deletion-and-reverse-in-linked-list/1>
 *
 * Pattern:
 * <Insertion>
 *
 * Intuition:
 * - In a circular linked list, the last node points back to the head.
 * - While deleting a node, we must ensure the circular structure is preserved.
 * - There are no NULL pointers, so traversal must stop explicitly.
 * - Special care is needed when deleting the head or when the list has only one node.
 *
 * Approach:
 * - If the list is empty, return null.
 * - If the node to be deleted is the head:
 *     - If the list has only one node, return null.
 *     - Otherwise:
 *         - Traverse to the last node (tail).
 *         - Move head to head.next.
 *         - Update tail.next = new head.
 * - If the node to be deleted is not the head:
 *     - Traverse the list keeping track of the previous node.
 *     - When the target node is found:
 *         - prev.next = curr.next
 * - Return the updated head.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - Edge cases:
 *   - Empty list
 *   - Single-node circular list
 *   - Deleting the head node
 *   - Value not present in the list
 * - Always stop traversal when the pointer comes back to head to avoid infinite loops.
 */

package linkedlist;

public class DeletionInCircularLinkedList {
    Node deleteNode(Node head, int key) {
        // code here
        if(head == null) return head;

        if(head.data == key){
            head = head.next;
        }

        Node curr = head;

        do{
            if(curr.next.data == key){
                curr.next = curr.next.next;
                break;
            }else{
                curr = curr.next;
            }
        }while(curr != head);

        return head;
    }
}

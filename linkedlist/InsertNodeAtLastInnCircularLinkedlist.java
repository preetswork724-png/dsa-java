/**
 * Problem:
 * <Insert node at last in circular linked list>
 *
 * Link:
 * <https://www.geeksforgeeks.org/problems/insert-node-at-last-in-circular-linked-list/1>
 *
 * Pattern:
 * <Insertion>
 *
 * Intuition:
 * - In a circular linked list, the last node always points back to the head.
 * - While inserting a node at the end, the circular structure must be preserved.
 * - If the list is empty, the new node should point to itself.
 * - Traversal must explicitly stop when the pointer comes back to the head.
 *
 * Approach:
 * - Create a new node `newNode` with the given data.
 * - If the list is empty:
 *     - Make newNode.next = newNode.
 *     - Return newNode as the head.
 * - Otherwise:
 *     - Traverse the list to find the last node (tail), where tail.next == head.
 *     - Set tail.next = newNode.
 *     - Set newNode.next = head.
 * - Return the head of the list.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - Edge cases:
 *   - Empty circular linked list
 *   - Single-node circular linked list
 * - Ensure that the new node always points back to the head to maintain circularity.
 * - Avoid infinite loops by stopping traversal when current.next == head.
 */

package linkedlist;

public class InsertNodeAtLastInnCircularLinkedlist {
    public Node insertAtBeginning(Node tail, int key) {
        Node newNode = new Node(key);

        if(tail == null) return newNode;

        Node temp = tail.next;
        tail.next = newNode;

        if(temp != null) newNode.next = temp;

        return tail;
    }
}

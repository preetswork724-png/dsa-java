/**
 * Queue Using Singly Linked List
 *
 * Link:
 * <https://www.geeksforgeeks.org/problems/implement-two-stacks-in-an-array/1>
 *
 * Supports:
 * - enqueue(x): Insert an element x at the rear of the queue.
 * - dequeue(): Remove the element from the front of the queue.
 * - getFront(): Return front element if not empty, else -1.
 * - isEmpty(): Return true if the queue is empty else return false.
 * - size(): Return the number of elements currently in the queue.
 *
 * Time Complexities:
 * - enqueue(): O(1)
 * - dequeue(): O(1)
 * - getFront(): O(1)
 *
 * Space Complexity:
 * - O(N)
 */

package queue;
class Node {
    int data;
    Node next;

    Node(int new_data) {
        data = new_data;
        next = null;
    }
}

public class MyQueue {
    private Node head, tail;
    private int size;

    public myQueue() {
        head = tail = null;
        size = 0;
    }

    public boolean isEmpty() {
        // check if the queue is empty
        return size == 0;
    }

    public void enqueue(int x) {
        // Adds an element x at the rear of the queue.
        Node newNode = new Node(x);
        size++;
        if(head == null){
            head = tail = newNode;
            return;
        }
        tail.next = newNode;
        tail = newNode;
    }

    public void dequeue() {
        // Removes the front element of the queue
        if(head == null) return;

        head = head.next;
        if(head == null) tail = null;
        size--;
    }

    public int getFront() {
        // Returns the front element of the queue.
        // If queue is empty, return -1.

        if(size == 0) return -1;
        return head.data;
    }

    public int size() {
        // Returns the current size of the queue.
        return size;
    }
}

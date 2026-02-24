/**
 * Queue Using Array
 *
 * Link:
 * <https://www.geeksforgeeks.org/problems/implement-queue-using-array/1>
 *
 * Supports:
 * - enqueue(x): Insert an element x at the rear of the queue.
 * - dequeue(): Remove the element from the front of the queue.
 * - getFront(): Return front element if not empty, else -1.
 * - getRear(): Return rear element if not empty, else -1.
 * - isEmpty(): Return true if the queue is empty else return false.
 * - isFull(): Return true if the queue is full else return false.
 *
 * Time Complexities:
 * - enqueue(): O(1)
 * - dequeue(): O(1)
 * - getFront(): O(1)
 * - getRear(): O(1)
 *
 * Space Complexity:
 * - O(N)
 */

package queue;

public class QueueUsingArray {
    private int[] arr;
    private int front, rear, size, capacity;

    // Constructor
    public QueueUsingArray(int n) {
        arr = new int[n];
        capacity = n;
        rear = 0;
        front = 0;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public void enqueue(int x) {
        if(isFull()) return;
        arr[rear] = x;
        rear = (rear + 1) % capacity;
        size++;
    }

    public void dequeue() {
        if(isEmpty()) return;
        front = (front + 1) % capacity;
        size--;
    }

    public int getFront() {
        if(isEmpty()) return -1;
        return arr[front];
    }

    public int getRear() {
        if(isEmpty()) return -1;
        int index = (rear - 1 + capacity) % capacity;
        return arr[index];
    }
}

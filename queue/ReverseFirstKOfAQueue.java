/**
 * Problem:
 * <Reverse first K of a Queue>
 *
 * Link:
 * <https://www.geeksforgeeks.org/problems/reverse-first-k-elements-of-queue/1>
 *
 * Pattern:
 * <Queue>
 *
 * Brute Force Intuition:
 * - Try out every possible starting position and simulate the entire circular trip.
 *
 * - Why it is inefficient?
 * - Correct but unnecessary full copy. We are rebuilding entire queue even though only first k needs reversal.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Better Approach intuition:
 * - Dequeue first k -> push into stack.
 * - Pop from stack -> enqueue into new queue.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Why this is still not optimal?
 * - Better than brute as we only store k elements.
 * - No need to copy entire queue into list.
 * - We don't need actually need a queue.
 *
 * Optimal Approach (Used below):
 * - Use one stack.
 * - Modify the queue in-place.
 * - Dequeue first k and store the elements inside a stack.
 * - Enqueue k elements from the stack.
 * - Dequeue the n-k elements and enqueue them back.
 * - Basically, move the remaining n-k elements from the front to the rear to restore their relative order.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(K)
 *
 * Notes:
 * - We don't create a new queue, instead we modify it in-place.
 */

package queue;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
public class ReverseFirstKOfAQueue {

    public static void main(String[] args) {
        Queue<Integer> q = new LinkedList<>(Arrays.asList(1, 2, 3, 4, 5));
        System.out.println(reverseFirstK(q, 3));
    }

    public static Queue<Integer> reverseFirstK(Queue<Integer> q, int k) {

        if(k < 0 || k > q.size()) return q;

        Stack<Integer> stack = new Stack<>();

        for(int i = 0; i < k; i++) stack.push(q.poll());

        while(!stack.isEmpty()) q.offer(stack.pop());

        int remaining = q.size() - k;

        for(int i = 0; i < remaining; i++) q.offer(q.poll());

        return q;
    }
}

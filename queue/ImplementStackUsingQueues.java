/**
 * Implement Stack using Queues
 *
 * Link:
 * <https://leetcode.com/problems/implement-stack-using-queues/description/>
 *
 * Supports:
 * - void push(int x) Pushes element x to the top of the stack.
 * - int pop() Removes the element on the top of the stack and returns it.
 * - int top() Returns the element on the top of the stack.
 * - boolean empty() Returns true if the stack is empty, false otherwise.
 *
 * Time Complexities:
 * - push(): O(N)
 * - pop(): O(1)
 * - top(): O(1)
 * - empty(): O(1)
 *
 * Space Complexity:
 * - O(N)
 */

package queue;
import java.util.Queue;
import java.util.LinkedList;

public class ImplementStackUsingQueues {
    private Queue<Integer> q1;
    private Queue<Integer> q2;

    public ImplementStackUsingQueues() {
        q1 = new LinkedList<>();
        q2 = new LinkedList<>();
    }

    public void push(int x) {
        q2.offer(x);

        while(!q1.isEmpty()){
            q2.offer(q1.poll());
        }

        Queue<Integer> temp = new LinkedList<>();
        q1 = q2;
        q2 = temp;
    }

    public int pop() {
        return q1.isEmpty() ? -1 : q1.poll();
    }

    public int top() {
        return q1.isEmpty() ? -1 : q1.peek();
    }

    public boolean empty() {
        return q1.isEmpty();
    }
}

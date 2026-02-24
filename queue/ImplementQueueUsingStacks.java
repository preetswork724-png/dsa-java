/**
 * Problem:
 * <Implement Queue using Stacks>
 *
 * Link:
 * <https://leetcode.com/problems/implement-queue-using-stacks/description/>
 *
 * Pattern:
 * <Two Stacks>
 *
 * Brute Force Intuition:
 * - Use one stack.
 * - push(): Push all the elements to the Stack.
 * - On pop() and peek():
 * - Move all the elements back to the stack (reverse order).
 * - Get top.
 * - Move them back.
 *
 * - Why it is inefficient?
 * - Reversing for pop() and peek() everytime they are called is very inefficient.
 *
 * Time Complexity:
 * - push1(): O(1), pop(): O(N), peek(): O(N).
 * Space Complexity:
 * - O(N)
 *
 * Better Approach:
 * - Maintain elements in correct queue order inside a stack.
 * - On every push():
 * - Move everything to another stack.
 * - push x.
 * - Move everything back.
 *
 * Time Complexity:
 * - push1(): O(N), pop(): O(1), peek(): O(1).
 * Space Complexity:
 * - O(N)
 *
 * Why this is not optimal?
 * - We shift the cost push().
 *
 * Optimal Approach (used below):
 * - Maintain 2 stacks.
 * - inStack -> for push.
 * - outStack -> for pop / peek.
 * - push() -> push in inStack.
 * - pop() and peek():
 * - If outStack not empty:
 * - That means elements that were previously added are not popped yet -> pop from it.
 * - Else:
 * - Move all the elements from inStack -> outStack, then pop.
 *
 * Time Complexity:
 * - push(): O(1), pop(): Amortized O(1), peek(): Amortized O(1).
 * Space Complexity:
 * - O(N)
 *
 * Notes:
 * - Why move all the elements from inStack -> outStack, then pop?
 * - Because reversing once gives correct FIFO order.
 *
 * - Why this is efficient?
 * - Element moves from inStack -> outStack at most once.
 * - Gets popped once.
 * - So total operations per element = constant time.
 */

package queue;
import java.util.Stack;

public class ImplementQueueUsingStacks {

    private Stack<Integer> inStack;
    private Stack<Integer> outStack;

    public ImplementQueueUsingStacks() {
        inStack = new Stack<>();
        outStack = new Stack<>();
    }

    public void push(int x) {
        inStack.push(x);
    }

    public int pop() {
        shiftStacks();
        return outStack.pop();
    }

    public int peek() {
        shiftStacks();
        return outStack.peek();
    }

    public boolean empty() {
        return inStack.isEmpty() && outStack.isEmpty();
    }

    public void shiftStacks(){

        if(outStack.isEmpty()){

            while(!inStack.isEmpty()){
                outStack.push(inStack.pop());
            }
        }
    }
}

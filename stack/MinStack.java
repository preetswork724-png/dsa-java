/**
 * Problem:
 * <Min Stack>
 *
 * Link:
 * <https://leetcode.com/problems/min-stack/description/>
 *
 * Pattern:
 * <Stack>
 *
 * Brute Force Intuition:
 * - Push:
 * - Just push normally.
 * - Pop:
 * - Just pop normally.
 * - Top:
 * - Just peek.
 * - getMin:
 * - Iterate entire stack → find the smallest element.
 *
 * - Why it is inefficient?
 * - Rescans the entire stack repeatedly.
 *
 * Time Complexity:
 * - push(): O(1), pop(): O(1), peek(): O(1), getMin(): O(N)
 * Space Complexity:
 * - O(N)
 *
 * Better Approach:
 * - Use Two Stacks.
 * - MainStack and minStack.
 * - MainStack represents the elements of the stack.
 * - MinStack represents the minimum element at the current level.
 * - push():
 * - push the element in the mainStack.
 * - If minStack is empty:
 * - push the element.
 * - Else:
 * - push the Math.min(current element, minStack.peek()).
 * - pop():
 * - pop out from the MainStack.
 * - pop out from the MinStack.
 * - peek():
 * - Retrieve the top from the mainStack.
 * - getMin():
 * - Retrieve the top from the minStack.
 *
 * Time Complexity:
 * - push(): O(1), pop(): O(1), peek(): O(1), getMin(): O(1)
 * Space Complexity:
 * - O(N)
 *
 * Why this is not optimal?
 * - Uses two stacks.
 *
 * Optimal Approach (used below):
 * - Single-stack.
 * - Store a pair at each level: (value, minSoFar).
 * - So instead of two stacks, you compress both into one stack of pairs.
 * - Instead of:
 * - stack → values
 * - minStack → minimum history
 * - You combine them:
 * - stack.push(new int[]{value, currentMin});
 * - So every level stores its own minimum snapshot.
 *
 * Time Complexity:
 * - push(): O(1), pop(): O(1), peek(): O(1), getMin(): O(1)
 * Space Complexity:
 * - O(N)
 *
 * Notes:
 * - Store minimum at every depth.
 */

package stack;
import java.util.Deque;
import java.util.ArrayDeque;

public class MinStack {
    Deque<int[]> minStack;

    public MinStack() {
        minStack = new ArrayDeque<>();
    }

    public void push(int val) {

        if(minStack.isEmpty()){
            minStack.push(new int[]{val, val});
        }
        else{
            minStack.push(new int[]{val, Math.min(val, minStack.peek()[1])});
        }
    }

    public void pop() {
        minStack.pop();
    }

    public int top() {
        return minStack.peek()[0];
    }

    public int getMin() {
        return minStack.peek()[1];
    }
}

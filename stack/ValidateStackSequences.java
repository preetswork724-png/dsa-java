/**
 * Problem:
 * <Validate Stack Sequences>
 *
 * Link:
 * <https://leetcode.com/problems/validate-stack-sequences/description/>
 *
 * Pattern:
 * <Stack + two pointer>
 *
 * Brute Force Intuition:
 * - Trying out all the possible sequences of push() and pop() operations.
 * - At each step, we can:
 * - Push the next element (if any left).
 * - Pop the top element of the stack (if not empty).
 * - Recursive state contains:
 * - index in pushed.
 * - index in popped.
 * - current stack state.
 * - At each step:
 * - If stack top matches popped[j] -> try popping.
 * - If there are still elements to the left, push them in the stack.
 * - Explore both possibilities.
 *
 * - Why it is inefficient?
 * - We explore all the possibilities.
 * - Exponential Time complexity.
 *
 * Time Complexity:
 * - O(2^n)
 * Space Complexity:
 * - O(N)
 *
 * Better and Optimal Approach (used below):
 * - Instead of exploring all the possibilities:
 * - Simulate the process logically.
 * - We push the elements in pushed[] order.
 * - Pop out when the top of the stack equals popped[j].
 * - Because, we must push in given order.
 * - Because, we must pop in given order.
 * - Initialize an empty stack.
 * - Keep a pointer j for popped.
 * - Iterate over pushed:
 * - Push the current num in the stack.
 * - While the stack is not empty and popped[j] equals Stack peek:
 *      pop
 *      j++;
 * - In the end, if the stack is empty then pushed[] and popped[] are valid.
 * - Else, invalid.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Why better is the optimal approach here?
 * - Because each element is pushed and popped once.
 *
 * Notes:
 * - Use Deque for faster operation.
 */

package stack;
import java.util.Deque;
import java.util.ArrayDeque;

public class ValidateStackSequences {

    public static void main(String[] args) {
        System.out.println(validateStackSequences(new int[]{1,2,3,4,5}, new int[]{4,5,3,2,1}));
    }

    public static boolean validateStackSequences(int[] pushed, int[] popped) {
        Deque<Integer> dq = new ArrayDeque<>();
        int j = 0;

        for (int num : pushed) {
            dq.push(num);

            while (!dq.isEmpty() && dq.peek() == popped[j]) {
                dq.pop();
                j++;
            }
        }
        return dq.isEmpty();
    }
}

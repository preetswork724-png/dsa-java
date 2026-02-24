/**
 * Problem:
 * <Two Stacks in an Array>
 *
 * Link:
 * <https://www.geeksforgeeks.org/problems/implement-two-stacks-in-an-array/1>
 *
 * Pattern:
 * <2 pointers>
 *
 * Brute Force Intuition:
 * - Create 2 completely separate arrays.
 * - Stack1 -> size n.
 * - Stack2 -> size n.
 *
 * - Why it is inefficient?
 * - Total Space (2N).
 *
 * Time Complexity:
 * - push1(): O(1), pop1(): O(1), push2(): O(1), pop2(): O(1).
 * Space Complexity:
 * - O(N)
 *
 * Better Approach:
 * - Fixed-partition (half - split):
 * - Use 1 array of size N, divide it:
 * - |Stack1 (0 -> N/2 - 1) | Stack2 (N/2 -> N-1)|.
 * - Each stack gets N/2 capacity.
 *
 * Time Complexity:
 * - push(): O(1), pop(): O(1), peek(): O(1), getMin(): O(1)
 * Space Complexity:
 * - O(N) {Space improved by 50%}
 *
 * Why this is not optimal?
 * - Because of static allocation.
 * - Example:
 * - Capacity = 10
 * - Stack1 → 0..4
 * - Stack2 → 5..9
 * - If:
 * - Stack1 pushes 8 times
 * - Stack2 pushes 1 time
 * - Stack1 overflows at 5 elements
 * - Even though array has free slots.
 * - That is wasted memory.
 *
 * Optimal Approach (used below):
 * - 2-pointer approach.
 * - One-array.
 * - Two pointers.
 * - Both stack grow towards each other.
 *
 * Time Complexity:
 * - push(): O(1), pop(): O(1), peek(): O(1), getMin(): O(1)
 * Space Complexity:
 * - O(N)
 *
 * Notes:
 * - Half-split is space optimization not memory utilization.
 */
package two_pointers;

public class TwoStacksInAnArray {

    private int[] arr;
    private int top1, top2;
    private int n;

    TwoStacksInAnArray() {
        arr = new int[100];
        n = arr.length;
        top1 = -1;
        top2 = n;
    }


    void push1(int x) {
        if(top1 + 1 == top2){
            return;
        }
        arr[++top1] = x;
    }

    // Function to push an integer into the stack2.
    void push2(int x) {
        if(top1 + 1 == top2){
            return;
        }
        arr[--top2] = x;
    }

    // Function to remove an element from top of the stack1.

    int pop1() {
        if(top1 == -1) return -1;
        return arr[top1--];
    }


    // Function to remove an element from top of the stack2.
    int pop2() {
        if(top2 == n) return -1;
        return arr[top2++];
    }
}

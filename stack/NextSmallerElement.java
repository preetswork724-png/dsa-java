/**
 * Problem:
 * <Next Smaller Element>
 *
 * Link:
 * <https://www.geeksforgeeks.org/problems/immediate-smaller-element1142/1>
 *
 * Pattern:
 * <Monotonic Stack>
 *
 * Brute Force Intuition:
 * - For every element in the array :
 * - Find the next smaller element to it such as j > i and nums[j] < nums[i].
 *
 * - Why it is inefficient?
 * - For every index, we need to search for its next smaller element O(N) time.
 * - Slower for large arrays.
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(1)
 *
 * Better and Optimal Approach ( Used Below ):
 * - Maintain a monotonically increasing Stack.
 * - Push when current element is greater than or equal to the top element in the stack.
 * - Pop when the current element is smaller than the top element in the stack.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Why the better approach is the optimal one?
 * - We must process every element once.
 * - Monotonic increasing stack lets us find the next greater element for all the elements in O(N).
 *
 * Notes:
 * - Direction of traversal doesn't matter as both the directions give the correct output.
 *
 * - What iterating from left -> right tells us?
 * - It tells whether we have found an answer for the previous elements or not.
 * - Let's say that the current element is greater than the element at the top, then is it also greater than the elements waiting in the stack?
 *
 * - What iterating from right -> left tells us?
 * - Stack already contains processed right-side elements.
 * - Top of stack is the potential answer.
 * - Stack stores future candidates.
 *
 * - Choose direction on the basis whether you want to:
 * - Resolve current element immediately.
 * - Or resolve previous pending elements later.
 */

package stack;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class NextSmallerElement {
    public static void main(String[] args) {
        System.out.println(nextSmallerEle(new int[]{4, 8, 5, 2, 25}));
    }

    static ArrayList<Integer> nextSmallerEle(int[] arr) {
        int n = arr.length;
        int[] nse = new int[n];
        Deque<Integer> dq = new ArrayDeque<>();

        for(int i = 0; i < n; i++){

            while(!dq.isEmpty() && arr[i] < arr[dq.peek()]){
                nse[dq.peek()] =  arr[i];
                dq.pop();
            }
            dq.push(i);
        }

        while(!dq.isEmpty()){
            nse[dq.peek()] =  -1;
            dq.pop();
        }
        ArrayList<Integer> res = new ArrayList<>();
        for(int i = 0; i < nse.length; i++) res.add(nse[i]);

        return res;
    }
}

/**
 * Problem:
 * <Previous Smaller Element>
 *
 * Link:
 * <https://www.geeksforgeeks.org/problems/previous-smaller-element/1>
 *
 * Pattern:
 * <Monotonic Stack>
 *
 * Brute Force Intuition:
 * - For every element in the array :
 * - Find the previous smaller element to it such as j < i and nums[j] < nums[i].
 *
 * - Why it is inefficient?
 * - For every index, we need to search for its previous smaller element which takes O(N) time.
 * - Slower for large arrays.
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(1)
 *
 * Better and Optimal Approach ( Used Below ):
 * - Iterate from right -> left.
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
 * - Monotonic increasing stack lets us find the previous smaller element for all the elements in O(N).
 *
 * Notes:
 * - We iterate from right -> left because the answer lies to the left of each element.
 * - Use Deque as it is faster.
 */
package stack;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Collections;

public class PreviousSmallerElement {

    public static void main(String[] args) {
        System.out.println(prevSmaller(new int[]{1,6,2}));
    }

    public static ArrayList<Integer> prevSmaller(int[] arr) {

        int n = arr.length;
        ArrayList<Integer> pse = new ArrayList<>(Collections.nCopies(n, -1));
        Deque<Integer> dq = new ArrayDeque<>();

        for(int i = n - 1; i >= 0; i--){

            while(!dq.isEmpty() && arr[i] < arr[dq.peek()]){
                pse.set(dq.peek(), arr[i]);
                dq.pop();
            }
            dq.push(i);
        }
        return pse;
    }
}

/**
 * Problem:
 * <Sum of Subarray Minimums>
 *
 * Link:
 * <https://leetcode.com/problems/sum-of-subarray-minimums/description/>
 *
 * Pattern:
 * <Monotonic Stack>
 *
 * Brute Force Intuition:
 * - Generate all the subarrays.
 * - Compute the minimum for that subarray.
 * - Add the sum to the minimums and track the sum globally.
 *
 * - Why it is inefficient?
 * - Generates all the subarrays.
 * - Computing minimum takes linear time.
 * - No use of previous information.
 *
 * Time Complexity:
 * - O(N^3)
 * Space Complexity:
 * - O(N)
 *
 * Better Approach:
 * - We can eliminate the 3rd loop and track the minimum on the fly.
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(N)
 *
 * Why this is not optimal?
 * - Still generates all the subarrays.
 *
 * Optimal Approach (used below):
 * - Instead of finding the minimum for each subarray.
 * - Flip the thinking:
 * - For every element arr[i], count how many subarrays where it is the minimum.
 * - If we know the count, then we can multiply with arr[i].
 * - Subarrays can extend in both the directions.
 * - Therefore, we need to compute the left distance where it is the minimum and the right distance where it is the minimum.
 * - leftDistance[i]: How far we can expand to the left until we hit a smaller-or equal element.
 * - rightDistance[i]: How far we can expand to the right until we hit a smaller element.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Notes:
 * - Why multiply left[i] * arr[i] * right[i]?
 * - We are not counting elements, we are counting subarrays.
 * - Each subarray contributes arr[i] to the total sum, therefore we multiply.
 *
 * - Why < on one side and <= on the other side?
 * - This avoids duplicates counting.
 * - If we use < on both the sides:
 * - Duplicate elements claim the subarray on both the sides.
 * - If we use <= on both the sides:
 * - Duplicate elements block expansion of the subarray on both the sides.
 * - If we use < on one side and <= on other side:
 * - Each subarray is claimed exactly once.
 * - When duplicate elements exits, we decide only one side gets to extend.
 * - If two indices claim the same subarray -> overcount.
 * - If none claims it, undercount.
 */

package stack;
import java.util.Deque;
import java.util.ArrayDeque;

public class SumOfSubarrayMinimums {

    public static void main(String[] args) {
        System.out.println(sumSubarrayMins(new int[]{3,1,2,4}));
    }

    public static int sumSubarrayMins(int[] arr) {
        int n  = arr.length;
        if(n == 1) return arr[0];

        int[] rightDistance = prevSmaller(arr, n);
        int[] leftDistance = nextSmaller(arr, n);

        int mod = 1000000007;
        long result = 0;

        for(int i = 0; i < n; i++){
            long interResult = (1L * arr[i] * leftDistance[i] % mod * rightDistance[i]) % mod;
            result = (result + interResult) % mod;
        }
        return (int)result;
    }

    public static int[] prevSmaller(int[] arr, int n){
        Deque<Integer> dq = new ArrayDeque<>();
        int[] pse = new int[n];

        for(int i = n-1; i >= 0; i--){

            while(!dq.isEmpty() && arr[i] < arr[dq.peek()]){
                dq.pop();
            }
            pse[i] = dq.isEmpty() ? (n - i) : dq.peek() - i;
            dq.push(i);
        }
        return pse;
    }

    public static int[] nextSmaller(int[] arr, int n){
        Deque<Integer> dq = new ArrayDeque<>();
        int[] nse = new int[n];

        for(int i = 0; i < n; i++){

            while(!dq.isEmpty() && arr[i] <= arr[dq.peek()]){
                dq.pop();
            }
            nse[i] = dq.isEmpty() ? i + 1 : i - dq.peek();
            dq.push(i);
        }
        return nse;
    }
}

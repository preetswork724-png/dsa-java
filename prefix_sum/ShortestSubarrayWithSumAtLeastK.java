/**
 * Problem:
 * <Shortest Subarray with Sum at Least K>
 *
 * Link:
 * <https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/description/>
 *
 * Pattern:
 * <Prefix Sum>
 *
 * Brute Force Intuition:
 * - Try all the subarrays.
 * - Check if the sum >= k.
 * - Track the minLen.
 *
 * - Why it is inefficient?
 * - Calculating sum of the subarray takes O(N) each time which is quiet inefficient.
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(1)
 *
 * Better Approach Intuition:
 * - Building prefix sum.
 * - We could binary search previous prefix sums, but prefix is not sorted because of negatives, so binary search cannot be implemented directly.
 *
 * Optimal Approach (Used Below):
 * - Compute the prefix sum array.
 * - We need smaller prefixes such that the distance between i and j must be minimum.
 * - Whenever we find a smaller prefix, we remove the previous greater prefix sums because they are useless.
 * - They are useless because :
 * - They might produce a subarray whose sum is greater than k.
 * - And the distance is any ways bigger than the current.
 * - The above two reasons make it an invalid candidate.
 * - Maintain a monotonic increasing deque.
 * - Why increasing ?
 * - So that the best candidate is always at the front.
 * - Pop out from the end until the current prefix is smaller or equal to the prefix sums stored at the end in the deque.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Notes:
 * - Use Monotonic Deque  + Prefix Sum Sliding Window Technique when :
 * - Need shortest / longest subarray.
 * - Constraint like sum >= k.
 * - Negative numbers exist.
 * - n upto 1e5.
 *
 * Mistake Log :
 * - Tried to implement the sliding window but the sliding window approach works only when the window is monotonic.
 * - That is, there aren't mixed numbers in the input like both positives and negatives.
 */
package prefix_sum;
import java.util.Deque;
import java.util.ArrayDeque;
public class ShortestSubarrayWithSumAtLeastK {

    public static void main(String[] args) {
        System.out.println(shortestSubarray(new int[]{2,-1,2}, 3));
    }

    public static int shortestSubarray(int[] nums, int k) {
        int n = nums.length, ans = Integer.MAX_VALUE;long[] prefix = new long[n + 1];

        for(int i = 0; i < n; i++){
            prefix[i + 1] = prefix[i] + nums[i];
        }

        Deque<Integer> dq = new ArrayDeque<>();

        for(int i = 0; i <= n; i++){

            while(!dq.isEmpty() && prefix[i] - prefix[dq.peekFirst()] >= k){
                ans = Math.min(ans, i - dq.peekFirst());
                dq.pollFirst();
            }

            while(!dq.isEmpty() && prefix[i] <= prefix[dq.peekLast()]){
                dq.pollLast();
            }

            dq.offerLast(i);
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}

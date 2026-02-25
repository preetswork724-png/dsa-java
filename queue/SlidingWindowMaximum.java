/**
 * Problem:
 * <Sliding Window Maximum>
 *
 * Link:
 * <https://leetcode.com/problems/sliding-window-maximum/description/>
 *
 * Pattern:
 * <Monotonic Queue>
 *
 * Brute Force Intuition:
 * - Generate all the subarrays of size k and track the maximum inside that subarray.
 *
 * - Why it is inefficient?
 * - Repeated scanning of overlapping windows.
 *
 * Time Complexity:
 * - O(N * K)
 * Space Complexity:
 * - O(1)
 *
 * Better Approach intuition:
 * - Use MaxHeap of size k.
 * - Iterate over the first window:
 * - Put nums[i], i inside the heap.
 * - Compute the maximum in that window.
 * - Now, slide the window.
 * - Remove all the elements out of the heap which are not a part of the window.
 * - Store the peek element of the heap inside the res array.
 *
 * Time Complexity:
 * - O(N log K)
 * Space Complexity:
 * - O(N)
 *
 * Why this is still not optimal?
 * - We are still using log K operations.
 * - We are not fully exploiting window structure.
 *
 * Optimal Approach (Used below):
 * - Monotonic Deque.
 * - Maintain a monotonic Deque of decreasing indices.
 * - The deque will:
 * - Store only useful elements.
 * - Front always holds maximum.
 * - Remove out-of-window elements from front.
 * - For each index i:
 * - Remove out-of-window elements
 * - if dq.front <= i - k → remove front
 * - Maintain decreasing order
 * - while nums[dq.back] < nums[i]
 *     remove back
 * - Add current index
 * - dq.addLast(i)
 * - Record answer
 * - If i >= k - 1:
 * - result.add(nums[dq.front])
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(K)
 *
 * Notes:
 * - If current element is bigger than previous smaller elements:
 * - Those smaller elements will NEVER become maximum in future windows
 * - So we remove them immediately.
 * - This avoids reprocessing.
 */

package queue;

import java.util.Arrays;
import java.util.Deque;
import java.util.ArrayDeque;

public class SlidingWindowMaximum {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(maxSlidingWindow(new int[]{1,3,-1,-3,5,3,6,7}, 3)));
    }

    public static int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        if(n == 0) return new int[0];

        Deque<Integer> dq = new ArrayDeque<>();
        int[] res = new int[n - k + 1];
        int idx = 0;

        for(int i = 0; i < n; i++){

            if(!dq.isEmpty() && dq.peekFirst() <= i - k){
                dq.pollFirst();
            }

            while(!dq.isEmpty() && nums[dq.peekLast()] < nums[i]){
                dq.pollLast();
            }

            dq.addLast(i);

            if(i >= k-1){
                res[idx++] = nums[dq.peekFirst()];
            }
        }
        return res;
    }
}

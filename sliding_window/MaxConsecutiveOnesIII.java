/**
 * Problem:
 * <Max Consecutive Ones III>
 *
 * Link:
 * <https://leetcode.com/problems/max-consecutive-ones-iii/description/>
 *
 * Pattern:
 * <Sliding Window>
 *
 * Brute Force Intuition:
 * - Try every possible starting point.
 * - Extend to right until you have less than or equal to k zeroes.
 * - Stop when you have more than k zeroes.
 * - Track max length.
 *
 * - Why it is inefficient?
 * - We waste time checking overlapping window intervals.
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(1)
 *
 * Optimal Approach (Used Below):
 * - Maintain a window between left and right pointers where the number of zeroes is at most k.
 *
 * - What to say in questions where the better approach is the optimal one?
 * - Prefix sum still needs searching windows.
 * - Shrinking logic without sliding window doesn't help much.
 * - You still need to scan almost all windows.
 * - Anything you come up with will still be O(N^2)
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - Shrink when number of zeroes in the current window > k.
 * - Expand when number of zeroes in the current window <= k.
 */
package sliding_window;

public class MaxConsecutiveOnesIII {

    public static void main(String[] args) {
        System.out.println(longestOnes(new int[]{1,1,1,0,0,0,1,1,1,1,0}, 2));
    }

    public static int longestOnes(int[] nums, int k) {
        int n = nums.length, left = 0, maxLen = 0, countZeroes = 0;

        for(int right = 0; right < n; right++){
            if(nums[right] == 0) countZeroes++;

            while(countZeroes > k){
                if(nums[left] == 0) countZeroes--;
                left++;
            }

            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }
}

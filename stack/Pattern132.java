/**
 * Problem:
 * <132 Pattern>
 *
 * Link:
 * <https://leetcode.com/problems/132-pattern/description/>
 *
 * Pattern:
 * <Monotonic Stack>
 *
 * Brute Force Intuition:
 * - Try out all the possible combinations where:
 * - i < j < k.
 * - nums[i] < nums[k] < nums[j].
 *
 * - Why it is inefficient?
 * - Very slow for large arrays.
 *
 * Time Complexity:
 * - O(N^3)
 * Space Complexity:
 * - O(1)
 *
 * Better Approach:
 * - For each j from 1 to n-1.
 * - Divide the problem into two parts.
 * - Find the maximum on the left side before j.
 * - If it is the same as nums[j], then skip.
 * - Find the nums[k] on the right side such that nums[k] < nums[j] and nums[k] > minLeft.
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(1)
 *
 * Why this is not optimal?
 * - Multiple passed.
 *
 * Optimal Approach (used below):
 * - Preprocess right side dynamically using stack.
 * - Iterate from right -> left because that way we are already aware of the future elements.
 * - Maintain a monotonic decreasing stack.
 * - We try to build the pattern backward.
 * - nums[i] < third < nums[j].
 * - nums[j] -> current element.
 * - third -> the best candidate for nums[k].
 * - stack -> decreasing sequence (possible 3's).
 * - third stores the largest popped values.
 * - If nums[i] < third, 132 found.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Notes:
 * - nums[i] < third < some bigger value before.
 */

package stack;
import java.util.Deque;
import java.util.ArrayDeque;

public class Pattern132 {

    public static void main(String[] args) {
        System.out.println(find132pattern(new int[]{1,2,3,4}));
    }

    public static boolean find132pattern(int[] nums) {
        int n = nums.length;
        Deque<Integer> dq = new ArrayDeque<>();
        int third = Integer.MIN_VALUE;

        for(int i = n-1; i >= 0; i--){

            if(nums[i] < third) return true;

            while(!dq.isEmpty() && nums[i] > dq.peek()){
                third = dq.pop();
            }
            dq.push(nums[i]);
        }
        return false;
    }
}

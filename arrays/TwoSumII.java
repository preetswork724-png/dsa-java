/**
 * Problem:
 * <Two Sum II - Input Array Is Sorted>
 *
 * Link:
 * <https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/description/>
 *
 * Pattern:
 * <Two Pointer>
 *
 * Brute Force Intuition:
 * - Try all pairs of indices (i,j).
 * - Check if nums[i] + nums[j] = target.
 * - Return indices when a match is found.
 *
 * - Why it is inefficient?
 * - While the approach is valid, it is still very slow for large arrays which might exceed the time limit.
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(1)
 *
 * Better Approach Intuition:
 * - Use a HashMap to store the elements previously seen.
 * - For every element check of its compliment exists.
 *
 * - Why it is still not optimal.
 * - The constraint of the problem is to avoid using extra space. This approach uses extra space.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Optimal Approach (Used Below):
 * - Implement 2-pointer approach as the array is already sorted.
 * - Whenever the sum of the elements represented by the pointer equals target, return the pointers
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - if(sum > target) Then taking any number between (i..j) will produce a greater sum. Therefore, we must move the rightmost pointer inwards.
 * - if(sum < target) Then we need to move the leftmost pointer inwards.
 * - stop when both the pointers haven't crossed each other or are at equal index.
 */
package arrays;
import java.util.Arrays;
public class TwoSumII {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(twoSum(new int[]{2,7,11,15}, 9)));
    }

    public static int[] twoSum(int[] numbers, int target) {
        int[] res = new int[2];

        int n = numbers.length, left = 0, right = n - 1;

        while(left < right){
            int sum = numbers[left] + numbers[right];

            if(sum == target){
                res[0] = left+1;
                res[1] = right+1;

                left++;
                right--;
            }else if(sum > target){
                right--;
            }else{
                left++;
            }
        }

        return res;
    }
}

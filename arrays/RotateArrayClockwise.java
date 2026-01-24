/**
 * Problem:
 * <Rotate Array Clockwise>
 *
 * Link:
 * <https://www.geeksforgeeks.org/problems/rotate-array-clockwise/1>
 *
 * Pattern:
 * <Rotate array>
 *
 * Brute Force Intuition:
 * - Rotate the array one step at a time , k times.
 *
 * - Why it is inefficient?
 * - You are shifting the whole array k times.
 * - if n = 10^5 and k = 10^5, this becomes 10^10 operations.
 *
 * Time Complexity:
 * - O(n * k)
 * Space Complexity:
 * - O(1)
 *
 * Better Approach intuition:
 * - Instead of rotating one step at a time i.e shifting each element to the right, k times:
 * - Directly calculate the position for the element after k steps.
 * - Create an additional array.
 * - For each element i in the array, place it at (i+k) % n in the new array.
 * - Copy the new array to the already existing array.
 *
 * Time Complexity:
 * - O(n)
 * Space Complexity:
 * - O(n)
 *
 * Why this is still not optimal?
 * - Uses extra space to rotate the array while in-place rotation is possible.
 *
 * Optimal Approach (Used below):
 * - For clockwise (right) rotation, our goal is to bring the last k elements to the front while restoring their relative order.
 * - Step 1: Reverse the array.
 * - This moves the last k elements to the front but their order is reversed.
 * - Step 2: Reverse the first k elements.
 * - This restores the correct order of elements that should appear at the front.
 * - Step 3: Reverse the remaining n-k elements.
 * - This restores  the correct order of the rest of the array
 *
 * Time Complexity:
 * - O(n)
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - We only need to do k % n rotations.
 *
 * Mistake log:
 * - Assumed that reversing the whole array is same as rotating by n.
 * - Meanwhile, it is just a transformation that places the required block at the front but in reversed order, which can be fixed using two more reversals.
 */
package arrays;

import java.util.Arrays;

public class RotateArrayClockwise {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6};
        int k = 2;
        rotate(nums, k);
        System.out.println(Arrays.toString(nums));
    }

    public static void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n;

        reverse(nums, 0, n-1);
        reverse(nums, 0, k-1);
        reverse(nums, k, n-1);
    }

    public static void reverse(int[] nums, int left, int right){

        while(left < right){

            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }
}

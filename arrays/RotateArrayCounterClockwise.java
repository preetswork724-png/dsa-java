/**
 * Problem:
 * <Rotate Array Counter Clockwise>
 *
 * Link:
 * <https://www.geeksforgeeks.org/problems/rotate-array-by-n-elements-1587115621/1>
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
 * - Instead of rotating one step at a time i.e shifting each element to the left, k times:
 * - Directly calculate the position for the element before k steps.
 * - Create an additional array.
 * - For each element i in the array, place it at (i - k + n) % n in the new array.
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
 * - For ant-clockwise (left) rotation, our goal is to remove the first k elements and move them to the end while preserving their relative order.
 * - Step 1: Reverse the first k elements.
 * - This brings the block that must go the end in a reversed form.
 * - Step 2: Reverse the remaining n-k elements.
 * - This reverses the order of elements that will stay at the front after rotations.
 * - Step 3: Reverse the entire array.
 * - This final reversal restores the correct order of both the blocks, resulting in the first k elements being placed at the end and the remaining elements appearing at the front in proper order.
 *
 * Time Complexity:
 * - O(n)
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - We only need to do (-k) % n rotations.
 */

package arrays;

import java.util.Arrays;

public class RotateArrayCounterClockwise {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6};
        int d = 2;
        rotateArr(arr, d);
        System.out.println(Arrays.toString(arr));
    }

    static void rotateArr(int arr[], int d) {
        int n  = arr.length;

        reverse(arr, 0, d-1);
        reverse(arr, d, n-1);
        reverse(arr, 0, n-1);
    }

    static void reverse(int[] arr, int left, int right){

        while(left < right){
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }
}

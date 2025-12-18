/**
 * Problem:
 * <Segregate 0s and 1s>
 *
 * Link:
 * <https://www.geeksforgeeks.org/problems/segregate-0s-and-1s5106/1/>
 *
 * Pattern:
 * <Two Pointer>
 *
 * Brute Force Intuition:
 * - Sort the array.
 *
 * - Why it is inefficient?
 * - Sorting takes time.
 *
 * Time Complexity:
 * - O(N log N)
 * Space Complexity:
 * - O(1)
 *
 * Better Approach Intuition:
 * - Count the number of 0s and 1s in a single pass.
 * - Create a new array.
 * - Put the zeroes first and the ones later.
 *
 * - Why it is still not optimal.
 * - Creates a new array and copies the number of zeroes and ones which is redundant.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Optimal Approach (Used Below):
 * - Move the left pointer until the first one.
 * - Move the right pointer until the first zero.
 * - left -> points to the first misplaced one.
 * - right -> points to the first misplaced zero.
 * - Everything before left is zero.
 * - Everything after right is one.
 * - Swap and then move the pointers again.
 * - This continues until the pointers cross, ensuring all 0s are on the left and all 1s are on the right.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - Performs in-place modifications in the array.
 */
package two_pointers;
import java.util.Arrays;

public class Segregate0sAnd1sInAnArray {
    public static void main(String[] args) {
        int[] arr = {0, 0, 1, 1, 0};
        segregate0and1(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void segregate0and1(int[] arr) {
        int n = arr.length, left = 0, right = n-1;

        while(left < right){

            while(left < right && arr[left] == 0) left++;
            while(left < right && arr[right] == 1) right--;

            if(left < right){

                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
                left++;
                right--;

            }

        }
    }
}

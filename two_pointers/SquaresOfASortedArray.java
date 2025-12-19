/**
 * Problem:
 * <Squares of a Sorted Array>
 *
 * Link:
 * <https://leetcode.com/problems/squares-of-a-sorted-array/description//>
 *
 * Pattern:
 * <Two Pointer>
 *
 * Brute Force Intuition:
 * - Create a new array and store the square of each number.
 * - Sort and return the new array.
 *
 * - Why it is inefficient?
 * - Loss of given information as the array was already sorted.
 * - Only the sign breaks the sorted order.
 *
 * Time Complexity:
 * - O(N log N)
 * Space Complexity:
 * - O(N)
 *
 * Optimal Approach (Used Below):
 * - Create a new array.
 * - Use the two-pointer approach to add the elements from the end of the array in linear time.
 * -
 * - What to say in questions where the better approach is the optimal one?
 * - The brute force approach is to square each element and sort the array, which takes O(n log n) time due to sorting.
 * - The bottleneck here is the sorting step.
 * - To improve the time complexity, we must eliminate sorting, and the only way to do that is by exploiting the fact that the input array is already sorted.
 * - In a sorted array, the maximum absolute value always lies at one of the ends, so the largest square also comes from the ends.
 * - Using two pointers, we can place squares in sorted order in linear time.
 * - Since we must process every element at least once, Ω(n) time is unavoidable, and this O(n) solution is optimal.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - Exploit the fact that the array is already sorted.
 * - After squaring the ends of the array produce the largest squares.
 * - And those ends can be tracked by using 2 pointers.
 * - Fill the array in linear time.
 */
package two_pointers;
import java.util.Arrays;
public class SquaresOfASortedArray {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(sortedSquares(new int[]{-4,-1,0,3,10})));
    }

    public static int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];

        int left = 0, right = n - 1, idx = n-1;

        while(left <= right){

            int leftSq = nums[left] * nums[left];
            int rightSq = nums[right] * nums[right];

            if(leftSq > rightSq){
                res[idx--] = leftSq;
                left++;
            }else{
                res[idx--] = rightSq;
                right--;
            }

        }
        return res;
    }
}

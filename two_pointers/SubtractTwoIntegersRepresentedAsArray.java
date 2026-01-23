/**
 * Problem:
 * <Subtract two integers represented as array>
 *
 * Link:
 * <https://leetcode.com/discuss/post/443621/google-phone-subtract-two-integers-repre-16fi/>
 *
 * Pattern:
 * <Two Pointer>
 *
 * Brute Force Intuition:
 * - Convert arr1 to a number.
 * - Convert arr2 to a number.
 * - Remember the sign if a bigger number is subtracted from a smaller number.
 *
 * - Why it is inefficient?
 * - Numbers can have up to 10^6 digits, which cannot be stored in primitive data types.
 * - Conversion causes overflow and it's not scalable.
 *
 * Time Complexity:
 * - O(n + m)
 * Space Complexity:
 * - O(n + m) {to store digits of the sum}
 *
 * Better and Optimal Approach (Used below):
 * - Swap if arr2 has to be subtracted from arr1 and arr1 < arr2.
 * - Remember to multiply the result by '-' if the above is true.
 * - Place two pointers at the end of both arrays.
 * - At each step compute:
 * - diff = arr1[j] - arr2[j] + borrow
 * - j is a pointer that points to the last element of the smaller array.
 * - if (diff < 10) add 10 to the difference, update the borrow as 1.
 * - Now, if there are digits yet to be processed in the bigger array, compute:
 * - diff = arr1[i] - borrow.
 * - Remove the trailing zeroes.
 * - Multiply the answer by -1 if negative.
 *
 * Time Complexity:
 * - O(n + m)
 * Space Complexity:
 * - O(n + m) (output), O(1) (auxiliary)
 *
 * Why better approach is the optimal one?
 * - Every digit must be processed at least once, so O(n + m) is the lower bound.
 *
 * Notes:
 * - diff = arr1[j] - arr2[j] - borrow.
 */
package two_pointers;
import java.util.Arrays;
public class SubtractTwoIntegersRepresentedAsArray {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(subtract(new int[]{3,2,1}, new int[]{1,2,3})));
    }

    public static int[] subtract(int[] arr1, int[] arr2){

        boolean isNegative = false;

        if(isSmaller(arr1, arr2)){
            isNegative = true;
            int[] temp = arr1;
            arr1 = arr2;
            arr2 = temp;
        }

        int n = arr1.length, m = arr2.length, i = n-1, j = m-1, k = n-1, borrow = 0;
        int[] res = new int[n];

        while(j >= 0){

            int diff = arr1[i] - arr2[j] - borrow;

            if(diff < 0){
                diff += 10;
                borrow = 1;
            }
            else{
                borrow = 0;
            }

            res[k] = diff;
            i--; j--; k--;
        }

        while(i >= 0){

            int diff = arr1[i] - borrow;

            if(diff < 0){
                diff += 10;
                borrow = 1;
            }
            else{
                borrow = 0;
            }
            res[k] = diff;
            i--; k--;
        }

        int start = 0;

        while(start < n && res[start] == 0) start++;
        res = Arrays.copyOfRange(res, start, res.length);
        if(isNegative) res[0] *= -1;
        return res;
    }

    public static boolean isSmaller(int[] arr1, int[] arr2){
        if(arr1.length < arr2.length) return true;
        if(arr1.length > arr2.length) return false;

        for(int i = 0; i < arr1.length; i++){
            if(arr1[i] < arr2[i]) return true;
            if(arr1[i] > arr2[i]) return false;
        }
        return true;
    }
}

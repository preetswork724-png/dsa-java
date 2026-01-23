/**
 * Problem:
 * <Add two numbers represented by two arrays>
 *
 * Link:
 * <https://www.geeksforgeeks.org/problems/add-two-numbers-represented-by-two-arrays2408/1>
 *
 * Pattern:
 * <Two Pointer>
 *
 * Brute Force Intuition:
 * - Convert arr1 to a number.
 * - Convert arr2 to a number.
 * - Sum the two numbers.
 * - Extract digits from the sum.
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
 * - Place two pointers at the end of both arrays.
 * - At each step compute:
 * - sum = arr1[i] + arr2[j] + carry
 * - Store sum % 10, update carry = sum / 10.
 * - Move pointers left.
 *  - Continue while i >= 0 or j >= 0 or carry > 0..
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
 * - sum = arr1[i] + arr2[j] + carry
 */
package two_pointers;

public class AddTwoNumbersRepresentedByTwoArrays {

    public static void main(String[] args) {
        System.out.println(calc_Sum(new int[]{1,2}, new int[]{2,1}));
    }

    public static String calc_Sum(int arr1[], int arr2[]) {
        int n = arr1.length, m = arr2.length, i = n-1, j = m-1, carry = 0;

        StringBuilder ans = new StringBuilder();

        while(i >= 0 || j >= 0 || carry > 0){
            int sum = carry;

            if(i >= 0) sum += arr1[i--];
            if(j >= 0) sum += arr2[j--];

            ans.append(sum % 10);
            carry = sum / 10;
        }

        return ans.reverse().toString();
    }
}

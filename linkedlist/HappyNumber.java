/**
 * Problem:
 * <Happy Number>
 *
 * Link:
 * <https://leetcode.com/problems/happy-number/description/>
 *
 * Pattern:
 * <Fast & Slow Pointer>
 *
 * Brute Force Intuition:
 * - Maintain a list of all the computed numbers.
 * - Check if you've seen the number before or the number that you've computed as both are the conditions for terminal of the loop.
 * - If the number is 1 then that number is a happy number, otherwise the number loops endlessly in a cycle and is not a happy number.
 *
 * - Why it is inefficient?
 * - Checking if each number has been computed before is a O(N) approach.
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(N)
 *
 * Better Approach Intuition:
 * - Make use of a HashSet to optimize the checking process.
 *
 * - Why it is still not optimal?
 * - Uses extra space to store the numbers.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Optimal Approach (Used Below):
 * - The above problems seems like a cycle detection problem.
 * - Hence, Floyd's cycle detection can be used.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - Remember to initialize the pointers properly.
 * - Initiate slow = n.
 * - Advance fast by one to prevent incorrect early exit i.e fast = next(n).
 */
package linkedlist;

public class HappyNumber {
    public static void main(String[] args) {
        System.out.println(isHappy(19));
    }

    public static boolean isHappy(int n) {
        int slow = n, fast = getNext(n);

        while(fast != 1 && slow != fast){
            slow = getNext(slow);
            fast = getNext(getNext(fast));
        }

        return fast == 1;
    }

    public static int getNext(int n){
        int sum = 0;

        while(n > 0){
            int digit = n % 10;
            sum += digit * digit;
            n /= 10;
        }

        return sum;
    }
}

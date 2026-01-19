/**
 * Problem:
 * <Largest Unique Number>
 *
 * Link:
 * <https://algo.monster/liteproblems/1133>
 *
 * Pattern:
 * <Frequency count>
 *
 * Brute Force Intuition:
 * - For all the numbers in the array, count the frequency of each number.
 * - For numbers whose frequency is 1, track the maximum number.
 *
 * - Why it is inefficient?
 * - Rescans the whole array for every single number.
 * - Quiet slow.
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(1)
 *
 * Better Approach Intuition:
 * - Use a map and store numbers as key and their frequency as the value to the key.
 * - Using a map gives the frequency of a number in O(1).
 * - Iterate over the mop, if the frequency of the number is 1, then update the maximum.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Why this is still not optimal?
 * - Map uses extra space proportional to input.
 * - Slower constants than array.
 *
 * Optimal Approach (used below):
 * - Small constraints allow array counting, therefore we can replace a map with a fixed-size array and achieve it in O(1) space.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - Frequency problems -> HashMap first, then optimize to array if constraints are small.
 */
package hashing;

public class LargestUniqueNumber {
    public static void main(String[] args) {
        System.out.println(largestUnque(new int[]{9, 9, 8, 8}));
    }

    public static int largestUnque(int[] nums){
        int[] freq = new int[1001];

        for(int num : nums) freq[num]++;

        for(int number = 1000 ; number >= 0; number--){
            if(freq[number] == 1) return number;
        }
        return -1;
    }
}

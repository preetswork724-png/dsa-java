/**
 * Problem:
 * <Longest Consecutive Sequence>
 *
 * Link:
 * <https://leetcode.com/problems/longest-consecutive-sequence/description/>
 *
 * Pattern:
 * <Hashing>
 *
 * Brute Force Intuition:
 * - For each number:
 * - Scan the entire array, try to find the current num + 1.
 * - If it exists, increment the number and the length.
 * - Then again repeat the process.
 * - Track the length of the consecutive sequence that you built using the current number.
 * - Track the max length globally.
 *
 * - Why it is inefficient?
 * - Repeated scanning of array.
 * - Inefficient for large arrays.
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(1)
 *
 * Better and Optimal Approach (Used below):
 * - Hashing.
 * - For a number:
 * - It is the start of the sequence if num - 1 doesn't exist.
 * - So, store all the numbers in a HashSet.
 * - For each number in the set:
 * - Check if num - 1 exist.
 * - If it exists, skip the number as it cannot be a start of the sequence.
 * - Else, take the current number and continue until num + 1 exists in the set.
 * - In each iteration, increment the current number and the current length.
 * - Track the max length globally.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Why better approach is the optimal one?
 * - Storing numbers in a Set acts as a pre-processing step.
 * - HashSet gives average O(1) check,
 *
 * Notes:
 * - Iterate over the set and not the input.
 */

package hashing;
import java.util.Set;
import java.util.HashSet;
public class LongestConsecutiveSequence {

    public static void main(String[] args) {
        System.out.println(longestConsecutive(new int[]{100,4,200,1,3,2}));
    }

    public static int longestConsecutive(int[] nums) {
        int n = nums.length;
        if(n == 0) return 0;

        Set<Integer> seen = new HashSet<>();

        for(int i : nums) seen.add(i);

        int maxLength = 1;

        for(int num : seen){

            if(!seen.contains(num - 1)){
                int current = num, currLength = 1;

                while(seen.contains(current + 1)){
                    current++;
                    currLength++;
                }

                maxLength = Math.max(currLength, maxLength);
            }
        }
        return maxLength;
    }
}

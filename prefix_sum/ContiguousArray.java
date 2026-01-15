/**
 * Problem:
 * <Contiguous Array>
 *
 * Link:
 * <https://leetcode.com/problems/contiguous-array/description/>
 *
 * Pattern:
 * <Prefix Sum>
 *
 * Brute Force Intuition:
 * - Try all the subarrays such that i ranges from 0 to n and j ranges from i to n.
 * - Calculate the number of zeroes and ones in that subarray.
 * - Track the maxLen.
 *
 * - Why it is inefficient?
 * - Calculating number of zeroes and ones takes O(N) time.
 * - Very slow for large arrays.
 *
 * Time Complexity:
 * - O(N^3)
 * Space Complexity:
 * - O(1)
 *
 * Better Approach Intuition:
 * - Cut down the innermost (third loop).
 * - Maintain the count of zeroes and ones while traversing in the second loop.
 * - Track the len when zeroes and ones are equal in the subarray.
 *
 * - Why it is still not optimal?
 * - Still very slow as we need to check all the subarrays.
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(1)
 *
 * Optimal Approach (Used Below):
 * - We maintain the frequency of zeroes and ones as we traverse the array.
 * - Now, just like subarray sum equals k, we need to check if the same number of zeroes and ones have occurred previously.
 * - So, we maintain a map.
 * - Calculate the difference of zeroes and ones at each index.
 * - If you have seen the difference before then compute the length as the current index - previously stored index.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(k)
 *
 * Notes:
 * - Include the case where the number of zeroes and ones are equal.
 */
package prefix_sum;
import java.util.Map;
import java.util.HashMap;
public class ContiguousArray {

    public static void main(String[] args) {
        System.out.println(findMaxLength(new int[]{0,1 }));
    }

    public static int findMaxLength(int[] nums) {
        int n = nums.length, maxLen = 0;
        int[] freq = new int[2];

        Map<Integer, Integer> diffMap = new HashMap<>();
        diffMap.put(0, -1);

        for(int right = 0; right < n; right++){
            freq[nums[right]]++;

            int diff = freq[0] - freq[1];

            if(diffMap.containsKey(diff)){
                maxLen = Math.max(maxLen, right - diffMap.get(diff));
            }
            else{
                diffMap.put(diff, diffMap.getOrDefault(diff, 0) + 1);
            }
        }
        return maxLen;
    }
}
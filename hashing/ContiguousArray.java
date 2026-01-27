/**
 * Problem:
 * <Contiguous Array>
 *
 * Link:
 * <https://leetcode.com/problems/contiguous-array/description/>
 *
 * Pattern:
 * <Hashing>
 *
 * Brute Force Intuition:
 * - Try every starting index i.
 * - Try every ending index j.
 * - Count the number of zeroes and ones in that subarray.
 * - Track the length, if the number of zeroes and ones are equal.
 *
 * - Why it is inefficient?
 * - Way too slow for large arrays.
 * - Re-checks many repeated sub-problems.
 *
 * Time Complexity:
 * - O(N^3)
 * Space Complexity:
 * - O(1)
 *
 * Better Approach Intuition:
 * - Create a new array.
 * - Iterate over the input array and store values in the new array like:
 * - newArr[i] = arr[i] == 1 ? 1 : -1.
 * - Now take prefix sum of the array.
 * - maxLen = 0
 * - case when subarray starts from index 0
 * - for j = 0 to n-1:
 * -    if pref[j] == 0:
 * -    maxLen = max(maxLen, j+1)
 * - case when subarray starts after 0
 * - for i = 0 to n-1:
 * -    for j = i+1 to n-1:
 * -        if pref[i] == pref[j]:
 * -        maxLen = max(maxLen, j - i)
 * - Values at some indexes in the prefix sum will be 0 indicating the subarrays whose sum start from 0.
 * - Then there exists indices such that prefix[j] = prefix[i], which indicates that the number if 0s and 1s between the i and j are equal.
 *
 * - Why it is still not optimal?
 * - Checks all the pairs.
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(N)
 *
 * Optimal Approach (Used Below):
 * - Use map to remember the count of 0s and 1s in a subarray.
 * - At each index, take the difference of number of 1s and 0s.
 * - If you have seen this difference previously, then there exists a point in the subarray such that the number of 0s and 1s are equal in that subarray.
 * - Track the maximum length.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Notes:
 * - Handle the edge case where the difference of 0s and 1s is zero.
 * - diffMap.put(0, -1);
 */

package hashing;
import java.util.Map;
import java.util.HashMap;
public class ContiguousArray {

    public static void main(String[] args) {
        System.out.println(findMaxLength(new int[]{0,1}));
    }

    public static int findMaxLength(int[] nums) {
        int n = nums.length, maxLen = 0;
        int[] count = new int[2];
        Map<Integer, Integer> diffMap = new HashMap<>();
        diffMap.put(0, -1);

        for(int right = 0; right < n; right++){
            count[nums[right]]++;

            int diff = count[0] - count[1];

            if(diffMap.containsKey(diff)){
                maxLen = Math.max(maxLen, right - diffMap.get(diff));
            }
            else{
                diffMap.put(diff, right);
            }
        }
        return maxLen;
    }
}

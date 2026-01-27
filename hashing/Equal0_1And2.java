/**
 * Problem:
 * <Equal 0, 1 and 2>
 *
 * Link:
 * <https://www.geeksforgeeks.org/problems/equal-0-1-and-23208/1>
 *
 * Pattern:
 * <Hashing>
 *
 * Brute Force Intuition:
 * - Try every starting index i.
 * - Try every ending index j.
 * - Count the number of zeroes, ones and twos in that subarray.
 * - Count if the number of zeroes, ones and twos are equal.
 *
 * - Why it is inefficient?
 * - Way too slow for large arrays.
 * - Checking the number of 0s, 1sand 2s take O(N) time.
 *
 * Time Complexity:
 * - O(N^3)
 * Space Complexity:
 * - O(1)
 *
 * Better Approach Intuition:
 * - Build 3 arrays:
 * - p0: number of 0s from (0...i).
 * - p1: number of 1s from (0...i).
 * - p2: number of 2s from (0...i).
 * - Count subarrays starting from 0.
 * - for j = 0 to n-1:
 * -    if p0[j] == p1[j] && p1[j] == p2[j]:
 * -    count++;
 * - Subarrays starting from i+1.
 * - for i = 0 to n-1:
 * -    for j = i+1 to n-1:
 * -        if ((p0[j] - p0[i]) == (p1[j] - p1[i]) && (p1[j] - p1[i]) == (p2[j] - p2[i])):
 * -        count++;
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
 * - Use map to remember the count of 0s, 1s and 2s in a subarray.
 * - At each index, take the difference of number of 1s and 0s && 1s and 2s.
 * - Compute the difference key.
 * - If you have seen this difference previously, then there exists a point in the subarray such that the number of 0s, 1s and 2s are equal in that subarray.
 * - Count them.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Notes:
 * - Handle the edge case where the difference of 0s, 1s and 2s is zero.
 * - diffMap.put("0#0", -1);
 */

package hashing;
import java.util.Map;
import java.util.HashMap;
public class Equal0_1And2 {

    public static void main(String[] args) {
        System.out.println(getSubstringWithEqual012("0102010"));
    }

    public static long getSubstringWithEqual012(String str) {
        char[] arr = str.toCharArray();
        int n = str.length();
        long countSubarrays = 0;

        int[] count = new int[3];

        Map<String, Integer> diffMap = new HashMap<>();

        diffMap.put("0#0", 1);

        for(int right = 0; right < n; right++){
            count[arr[right] - '0']++;

            int diff01 = count[0] - count[1], diff12 = count[1] - count[2];

            String diffKey = diff01 + "#" + diff12;

            countSubarrays += diffMap.getOrDefault(diffKey, 0);

            diffMap.put(diffKey, diffMap.getOrDefault(diffKey, 0) + 1);
        }
        return countSubarrays;
    }
}

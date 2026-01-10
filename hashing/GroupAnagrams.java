/**
 * Problem:
 * <Group Anagrams>
 *
 * Link:
 * <https://leetcode.com/problems/group-anagrams/description/>
 *
 * Pattern:
 * <Frequency count>
 *
 * Brute Force Intuition:
 * - For every pair of strings, check if they are anagrams of each other by comparing frequency arrays.
 *
 * - Why it is inefficient?
 * - Repeats the same frequency computation many times.
 * - Pair wise comparison explodes when n is large.
 *
 * Time Complexity:
 * - O(N^2 * k)
 * Space Complexity:
 * - O(N * k) {Output space}
 *
 * Better Approach Intuition:
 * - All anagrams become the same string after sorting.
 *
 * Time Complexity:
 * - O(N * k log k)
 * Space Complexity:
 * - O(N * k)
 *
 * Why this is still not optimal?
 * - The above approach requires sorting each array.
 *
 * Optimal Approach (used below):
 * - Compute the freq[] for each word.
 * - Make the frequency of characters in the string as a key.
 * - If the key is already present in the map then add the current string to the list stored corresponding to that key.
 *
 * Time Complexity:
 * - O(N * K)
 * Space Complexity:
 * - O(N * K)
 *
 * Notes:
 * - The above optimal and better approach exists because anagrams have a structure.
 * - The structure is :
 * - Two strings are anagrams of each other if they have the same characters with the same frequency.
 */
package hashing;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
public class GroupAnagrams {

    public static void main(String[] args) {
        System.out.println(groupAnagrams(new String[]{"eat","tea","tan","ate","nat","bat"}));
    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        for(String str : strs){

            int[] freq = new int[26];

            for(char ch : str.toCharArray()){
                freq[ch - 'a']++;
            }

            StringBuilder keyBuilder = new StringBuilder();

            for(int count : freq){
                keyBuilder.append("#").append(count);
            }

            String key = keyBuilder.toString();

            map.computeIfAbsent(key, k -> new ArrayList<>()).add(str);
        }
        return new ArrayList<>(map.values());
    }
}

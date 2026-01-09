/**
 * Problem:
 * <First Unique Character in a String>
 *
 * Link:
 * <https://leetcode.com/problems/first-unique-character-in-a-string/submissions/1879466357/>
 *
 * Pattern:
 * <Frequency count>
 *
 * Brute Force Intuition:
 * - For all the characters in the string, check their frequency one by one.
 * - If any character's freq = 1, then return it.
 * - Use a boolean array to prevent tracking frequency of the already tracked character.
 *
 * - Why it is inefficient?
 * - Rescans the whole string for every character.
 * - Quiet slow.
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(1)
 *
 * Better Approach Intuition:
 * - Use a map and store characters as key and their frequency as the value to the key.
 * - Using a map gives the frequency of a character in O(1).
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
 * - The inout consists of ony lowercase English letters, therefore we can replace a map with a fixed-size array and achieve it in O(1) space.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - Use array freq hashing when question has some fixed constraints like above.
 * - But only in cases where the range is small.
 */
package hashing;

public class FirstUniqueCharacterInAString {
    public static void main(String[] args) {
        System.out.println(firstUniqChar("leetcode"));
    }

    public static int firstUniqChar(String s) {
        int[] freq = new int[26];

        for(char ch : s.toCharArray()) freq[ch - 'a']++;

        for(int i = 0; i < s.length() ; i++){
            int index = s.charAt(i) - 'a';

            if(freq[index] == 1) return i;
        }
        return -1;
    }
}

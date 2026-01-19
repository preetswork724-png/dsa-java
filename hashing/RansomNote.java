/**
 * Problem:
 * <Ransom Note>
 *
 * Link:
 * <https://leetcode.com/problems/ransom-note/>
 *
 * Pattern:
 * <Frequency>
 *
 * Brute Force Intuition:
 * - Check if each character of the ransom note occurs exactly the same times in magazine.
 *
 * - Why it is inefficient?
 * - Comparing the characters in the magazine string for every character in ransom note is quiet inefficient.
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(N)
 *
 * Better Approach Intuition:
 * - Compute the freq[] for ransom note.
 * - Compute the freq[] for magazine.
 * - Check that freq of each character in ransom note >= freq of that character in magazine.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(1)
 *
 * Why this is still not optimal?
 * - The above approach is optimal but requires 3 passes.
 *
 * Optimal Approach (used below):
 * - Compute the freq[] for magazine.
 * - Traverse the ransom note string.
 * - For each char in ransom note, decrement its frequency in magazine.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - Remember to check if that character appears or not, before decrementing its frequency.
 */
package hashing;

public class RansomNote {

    public static void main(String[] args) {
        System.out.println(canConstruct("aa","ab"));
    }

    public static boolean canConstruct(String ransomNote, String magazine) {
        int[] freq = new int[26];

        for(char ch : magazine.toCharArray()) freq[ch - 'a']++;

        for(int i = 0; i < ransomNote.length(); i++){
            char ch = ransomNote.charAt(i);

            if(freq[ch - 'a'] == 0) return false;
            freq[ch - 'a']--;
        }
        return true;
    }
}

/**
 * Problem:
 * <Longest Palindrome>
 *
 * Link:
 * <https://leetcode.com/problems/longest-palindrome/description/>
 *
 * Pattern:
 * <Frequency>
 *
 * Brute Force Intuition:
 * - Generate all permutations of a string.
 * - For all the permutations :
 * - Check if it is a palindrome.
 * - Track the maximum length.
 *
 * - Why it is inefficient?
 * - Generating permutations of a string is O(N!).
 * - Checking if it is a palindrome takes another O(N).
 * - This is pure brute force and complexity is infeasible.
 *
 * Time Complexity:
 * - O(N! * N)
 * Space Complexity:
 * - O(1)
 *
 * Better Approach Intuition:
 * - Use a mop to store the characters with their corresponding frequency.
 * - If the frequency of character is even, then you can create a palindrome of its entire frequency.
 * - If the frequency of character is odd and none of the character before has been used as a centre, then only for this time add its complete freq to the length.
 * - If the frequency of character is odd centre is used, then only take the even frequency out of it and add to the length.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Why this is still not optimal?
 * - Map size is proportional to the input.
 * - Slower constants than array.
 *
 * Optimal Approach (used below):
 * - The input consists of ony lowercase and uppercase English letters, therefore we can replace a map with a fixed-size array and achieve it in O(1) space.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - If brute force involves permutations or subsets, you must abstract counts.
 */
package hashing;

public class LongestPalindrome {

    public static void main(String[] args) {
        System.out.println(longestPalindrome("abccccdd"));
    }

    public static int longestPalindrome(String s) {
        int[] freq = new int[52];

        for(char ch : s.toCharArray()){
            if(ch >= 'a' && ch <= 'z') freq[ch - 'a']++;
            else if(ch >= 'A' && ch <= 'Z') freq[ch - 'A' + 26]++;
        }

        int len = 0;
        boolean centreUsed = false;

        for(int i = 0; i < freq.length; i++){

            if(freq[i] == 0) continue;

            int currLen = freq[i];

            if(currLen % 2 == 0){
                len += currLen;
            }
            else{

                if(!centreUsed) {
                    centreUsed = true;
                    len += 1;
                }
                len += currLen - 1;
            }
        }
        return len;
    }
}

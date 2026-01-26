/**
 * Problem:
 * <Distinct palindromic substrings>
 *
 * Link:
 * <https://www.geeksforgeeks.org/problems/distinct-palindromic-substrings5141/1>
 *
 * Pattern:
 * <Expand around centre>
 *
 * Brute Force Intuition:
 * - Generate all the substrings.
 * - Check if the substring is palindromic.
 * - Store it in a list if the substring is distinct and palindromic .
 *
 * - Why it is inefficient?
 * - Trying out all the substrings is highly inefficient as every substring is not palindromic.
 * - Checking whether the palindromic substring is distinct or not takes O(N) time.
 *
 * Time Complexity:
 * - O(N^4)
 * Space Complexity:
 * - O(N^3) {All palindromic substrings in a list, Each substring can be O(N) length, Number of such substrings can be O(N^2)}
 *
 * Better Approach intuition:
 * - Instead of storing the palindromic substrings in a list, store them in a set.
 * - Checking whether a palindromic substring is distinct or not takes O(1) time.
 *
 * Time Complexity:
 * - O(N^3)
 * Space Complexity:
 * - O(N^3)
 *
 * Why this is still not optimal?
 * - Trying out all the substrings is highly inefficient as every substring is not palindromic
 *
 * Optimal Approach (Used below):
 * - For each index i in the string:
 * - Expand (i, i) for odd palindromes.
 * - Expand (i, i+1) for even palindromes.
 * - Each time you expand, add substring to the set.
 * - Each expansion is liner in worst case: O(N).
 * - Total number of centres: 2n.
 * - Use a Set to track all the distinct palindromic substrings.
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(N^3)
 *
 * Notes:
 * - Instead of generating all the possible substrings O(N^2) and checking each in O(N):
 * - We can generate all palindromes by expanding around every centre in O(N^2) and store them in a set for uniqueness.
 */

package strings;

import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

public class DistinctPalindromicSubstrings {

    public static void main(String[] args) {
        System.out.println(palindromicSubstr("abaaa"));
    }

    public static ArrayList<String> palindromicSubstr(String s) {

        Set<String> uniquePalindromicSubstrs = new HashSet<>();

        int n = s.length(), i = 0;


        while(i < n){
            int left = i, right = i;

            while(left >= 0 && right < n && s.charAt(left) == s.charAt(right)){

                uniquePalindromicSubstrs.add(s.substring(left, right + 1));
                left--;
                right++;

            }

            left = i; right = i + 1;

            while(left >= 0 && right < n && s.charAt(left) == s.charAt(right)){

                uniquePalindromicSubstrs.add(s.substring(left, right + 1));
                left--;
                right++;

            }

            i++;
        }
        return new ArrayList<>(uniquePalindromicSubstrs);
    }
}

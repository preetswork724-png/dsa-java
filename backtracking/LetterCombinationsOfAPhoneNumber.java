/**
 * Problem:
 * <Letter Combinations of a Phone Number>
 *
 * Link:
 * <https://leetcode.com/problems/letter-combinations-of-a-phone-number/description/>
 *
 * Approach:
 * - This is a backtracking / recursive generation problem
 * - At each digit:
 * - Choose one letter
 * - Move to next digit
 * - Build combinations character by character
 * - Function parameters:
 * - index → current digit position
 * - path → current built string
 * - Base case:
 * - If index == digits.length()
 * - Add path to result
 * - Recursive step:
 * - For each letter mapped to current digit:
 * - Append letter
 * - Recurse for next index
 * - Backtrack (remove letter)
 * - Why DP Is Not Needed
 * - No overlapping subproblems
 * - Every branch creates a unique string
 * - We must generate all results
 * - DP cannot reduce generation cost
 *
 * Time Complexities:
 * - O(4^N).
 *
 * Space Complexity:
 * - O(N)
 */

package backtracking;
import java.util.List;
import java.util.ArrayList;
public class LetterCombinationsOfAPhoneNumber {

    public static void main(String[] args) {
        System.out.println(letterCombinations("23"));
    }

    public static List<String> letterCombinations(String digits) {
        if(digits == null || digits.isEmpty()) return new ArrayList<>();

        String[] letters = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        List<String> combinations = new ArrayList<>();

        backtrack(0, new StringBuilder(), digits, letters, combinations);
        return combinations;
    }

    private static void backtrack(int index, StringBuilder path, String digits, String[] letters, List<String> combinations){

        if(index == digits.length()){
            combinations.add(path.toString());
            return;
        }

        String possibleLetters = letters[digits.charAt(index) - '0'];

        for(char ch : possibleLetters.toCharArray()){
            path.append(ch);
            backtrack(index + 1, path, digits, letters, combinations);
            path.deleteCharAt(path.length() - 1);
        }
    }
}

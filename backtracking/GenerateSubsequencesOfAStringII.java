/**
 * Generate subsequences of a string without using index as a parameter.
 *
 * Approach:
 * - For each char in the string, you have 2 choices.
 * - Concat it.
 * - Skip it.
 * - Recursive definition:
 * - findSubsequence(s.substring(1), path + s.charAt(0)).
 * - findSubsequence(s.substring(1), path).
 *
 * Time Complexities:
 * - O(2^N).
 *
 * Space Complexity:
 * - O(1)
 */

package backtracking;

public class GenerateSubsequencesOfAStringII {

    public static void main(String[] args) {
        printSubsequence("abc");
    }

    public static void printSubsequence(String s) {
        findSubSequence(s, "");
    }

    public static void findSubSequence(String str, String partialAns){
        if(str.isEmpty()){
            System.out.print(partialAns + " ");
            return;
        }

        findSubSequence(str.substring(1), partialAns + str.charAt(0));
        findSubSequence(str.substring(1), partialAns);
    }
}

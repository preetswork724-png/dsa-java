/**
 * Generate subsequences of a string.
 *
 * Approach:
 * - For each char in the string, you have 2 choices.
 * - Concat it.
 * - Skip it.
 * - Recursive definition:
 * - findSubsequence(s, index + 1, path + s.charAt(index)).
 * - findSubsequence(s, index + 1, path).
 *
 * Time Complexities:
 * - O(2^N).
 *
 * Space Complexity:
 * - O(1)
 */

package backtracking;

public class GenerateSubsequencesOfAString {
    public static void main(String[] args) {
        printSubsequences("abc");
    }

    public static void printSubsequences(String s){
        findSubsequences(s, 0, new StringBuilder());
    }

    public static void findSubsequences(String s, int index,  StringBuilder path){

        if(index == s.length()){
            System.out.print(path.toString() + " ");
            return;
        }

        findSubsequences(s, index  + 1, path.append(s.charAt(index)));
        path.deleteCharAt(path.length() - 1);
        findSubsequences(s, index + 1, path);
    }
}

/**
 * Problem:
 * <Count number of words>
 *
 * Link:
 * <https://www.geeksforgeeks.org/problems/count-number-of-words1500/1>
 *
 * Pattern:
 * <String>
 *
 * Brute Force Intuition:
 * - Split on the basis of whitespaces.
 * - Count tokens.
 *
 * - Why it is inefficient?
 * - Creates a new array.
 * - Creates many new substrings.
 * - Heavy memory.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Better Approach intuition:
 * - Scan char by char.
 * - Build current word using StringBuilder.
 * - When there is a separator, count word.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Why this is still not optimal?
 * - Storing word is unnecessary, we don't actually need the word.
 *
 * Optimal Approach (Used below):
 * - We don't need words.
 * - We only need to detect start of a word.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - On many platforms, the input string is passed as  "a\nhgjpfo" and not as an actual new line character.
 * - Instead, it is literally '\' + '\n', two characters.
 * - So what the code actually receives, 'a', '\\', 'h', 'g', 'j', 'p', 'f', 'o'.
 * - No '\n' character.
 * - So, the separator never triggers and whole string is counted as word.
 * - Therefore, we must treat "\n" and "\t" as separator.
 * - Replace sequences first, s.replace("\\n", " ").replace("\\t", " ").
 */

package strings;

public class CountNumberOfWords {

    public static void main(String[] args) {
        System.out.println(countWords("abc def"));
    }

    static int countWords(String s) {

        s = s.replace("\\n", " ")
                .replace("\\t", " ");

        int count = 0;
        boolean inWord = false;

        for(char ch : s.toCharArray()){

            if(ch == ' ' || ch == '\n' || ch == '\t'){
                inWord = false;
            }
            else if(!inWord){
                count++;
                inWord = true;
            }

        }
        return count;
    }
}

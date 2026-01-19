/**
 * Problem:
 * <Maximum Number of Balloons>
 *
 * Link:
 * <https://leetcode.com/problems/maximum-number-of-balloons/description/>
 *
 * Pattern:
 * <Frequency + minimum ratio>
 *
 * Brute Force Intuition:
 * - Convert the string to a mutable list of characters.
 * - Now try to form "balloon" one character at a time.
 * - For every character in balloon :
 * - Scan the list.
 * - Remove the character if found.
 * - Repeat until you fail.
 *
 * - Why it is inefficient?
 * - Rescans the whole list of characters for every single character in balloon.
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(1)
 *
 * Better Approach Intuition:
 * - Compute the frequency of characters in "balloon".
 * - Compute the frequency of characters in input string.
 * - Try tp repeatedly consume one balloon.
 * - Iterate over the map / freq[] of balloon :
 * - Try to form balloon by iterating over the freq[] / map of input string.
 * - Subtract the frequencies.
 *
 * Time Complexity:
 * - O(k * |balloon|)
 * Space Complexity:
 * - O(N)
 *
 * Why this is still not optimal?
 * - We are simulating the construction of "balloon" one by one.
 * - The loop runs as many times as say k.
 * - So, the real complexity becomes :
 * - O(K * |balloons|)
 * - In worst cases, k can be very large.
 * - Even though |balloon| is small, this is still unnecessary work.
 *
 * Optimal Approach (used below):
 * - This problem is not about the simulation.
 * - It is about the ratio of characters required to form as many instances of "balloon" as possible.
 * - The maximum number of "balloon" instances is limited by minimum ratio of required characters.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - How to think in problems like these ?
 * - Step 1 :
 * - Whenever the problem says :
 * - How many times can we form x?
 * - Maximum number of times.
 * - Repeated construction.
 * - Build string / word from characters.
 * - Your brain should immediately ask - What limits the repetition?
 * - Step 2 :
 * - Identify consumption, not order :
 * - In "balloon" :
 * - Order does not matter.
 * - Position does not matter.
 * - Only count matters.
 * - Then this is a frequency resource problem.
 * - Step 3 :
 * - Translate Problem -> Resource Model
 * - Characters in string -> resources, Balloon needs letters -> requirements, From multiple balloons -> repeated consumption.
 * - "Given resources, how many times can I fulfill a recipe?"
 * - This is a ratio / limiting factor problem.
 * - Step 4 :
 * - Find the bottleneck :
 * - Some characters will run out first - That's the bottleneck principle.
 * - Example:
 * - You have 10 b
 * - You have 3 l (but balloon needs 2 l)
 * - Even if everything else is abundant:
 * - l limits you to 1 balloon
 * - So the answer is:
 * - minimum over all required characters
 * - This is where ratio thinking comes from.
 * - Step 5 :
 * - Replace simulation with math :
 * - "Can I build one more balloon?"
 * - "How many does each character allow?"
 */
package hashing;

public class MaximumNumberOfBalloons {
    public static void main(String[] args) {
        System.out.println(maxNumberOfBalloons("loonbalxballpoon"));
    }

    public static int maxNumberOfBalloons(String text) {
        int[] freq = new int[26];

        for(char ch : text.toCharArray()) freq[ch - 'a']++;

        return Math.min(freq['b' - 'a'], Math.min(freq['a' - 'a'],
                Math.min(freq['l' - 'a'] / 2, Math.min(freq['o' - 'a'] / 2, freq['n' - 'a']))));
    }
}

/**
 * Problem:
 * <Remove All Adjacent Duplicates in String II>
 *
 * Link:
 * <https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string-ii/description/>
 *
 * Pattern:
 * <Stack>
 *
 * Brute Force Intuition:
 * - Simulate the problem.
 * - Scan the string from the start.
 * - Maintain a state to detect changes which prevents getting stuck.
 * - Remove the K adjacent duplicate characters.
 * - Reassign the intermediate string to the original string.
 * - Repeat the process.
 *
 * - Why it is inefficient?
 * - Even after removing the characters, the string has to be scanned from scratch.
 * - No use of previous work.
 * -
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(N)
 *
 * Better and Optimal Approach (Used Below):
 * - StringBuilder + Stack Simulation.
 * - Take a freq[] to compute the frequency of characters as you traverse the string.
 * - Take a StringBuilder to store the intermediate strings.
 * - If you find an adjacent duplicate character, then increment its frequency.
 * - frequency[last] = frequency[last-1] + 1.
 * - Here, the frequency gets added if the adjacent character is duplicate.
 * - Otherwise, frequency[last] = 1.
 * - After putting frequency, if frequency[last] == k then delete the substring from len-k to len.
 *
 * - Stack + pair (More standard)
 * - We make a custom class Pair which consists attributes as char and its current frequency.
 * - We push pair in the stack when the stack is empty or the top of the stack has a different char.
 * - When we encounter a duplicate adjacent char, we increment the character's frequency.
 * - Pop out the pair, when the char is duplicate and has adjacently occurred k times.
 * - Now traverse what's left in the stack and append the chars to a string according to their corresponding frequency.
 * - Reverse the string.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Why the better approach here is the optimal one?
 * - We need to process each character.
 * - We need to store the frequency of the character along with the character.
 * - Instead of rescanning the array, we utilize the previous work by using stack of pairs / frequency[] with StringBuilder to solve the problem efficiently.
 * 
 * Notes:
 * - When one value alone isn't enough to maintain an invariant, bundle the extra state with it.
 * - If you ever find yourself needing extra metadata per element, don't add parallel arrays - attach the metadata to the element itself.
 */
package stack;
import java.util.Deque;
import java.util.ArrayDeque;
public class RemoveAllAdjacentDuplicatesInStringII {

    public String removeDuplicates(String s, int k) {
        Deque<Pair> stack = new ArrayDeque<>();

        for(char c : s.toCharArray()){

            if(!stack.isEmpty() && stack.peek().ch == c){
                stack.peek().count++;
            }
            else{
                stack.push(new Pair(c, 1));
            }

            if(stack.peek().count == k) stack.pop();
        }

        StringBuilder res = new StringBuilder();

        for(Pair p : stack){
            for(int i = 0; i < p.count; i++){
                res.append(p.ch);
            }
        }

        return res.reverse().toString();
    }
}

class Pair{
    char ch;
    int count;

    Pair(char ch, int count){
        this.ch = ch;
        this.count = count;
    }
}

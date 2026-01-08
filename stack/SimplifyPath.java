/**
 * Problem:
 * <Simplify Path>
 *
 * Link:
 * <https://leetcode.com/problems/simplify-path/description/>
 *
 * Pattern:
 * <Stack>
 *
 * Brute Force Intuition:
 * - Simulate the problem.
 * - Scan the string from the start.
 * - Maintain a state to prevent from getting stuck.
 * - Replace "//" with "/".
 * - Replace "/./" with "/".
 * - For "/../", find the index of the last "/".
 * - If the last index > 0, then take the part before the last "/" and the part after skipping "/../'.
 * - Update the state as true id any of the above change takes place in that iteration.
 * - If no change takes place, then the state does not get updated, and you break out of the loop.
 * - Remove the trailing "/".
 *
 * - Why it is inefficient?
 * - Even after removing the characters, the string has to be scanned from scratch.
 * - No use of previous work.
 * - Inefficient.
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(1)
 *
 * Better and Optimal Approach (Used Below):
 * - The idea is simple :
 * - Split the string on the basis of "/".
 * - Ignore when you encounter a ".", because a period refers to the same directory.
 * - Ignore when a part is empty.
 * - Pop previously added directory when ".." part occurs.
 * - Push when it is a valid directory.
 *
 * - List + Stack simulation.
 * - Stack (Standard Approach)
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Why the better approach here is the optimal one?
 * - We need to process each character.
 * - We need to store the valid directories.
 * - Instead of rescanning the string, we utilize the previous work by using stack of valid directories to solve the problem efficiently.
 * - Then we append the directories while popping out of the stack.
 *
 * Notes:
 * - Inside the stack, we don't need to push everything that we see in the input.
 * - Sometimes, it is better to ignore some characters / elements for the simplicity of teh problem.
 *
 * Mistake log :
 * - I thought iterating from right to left is going to benefit me but that was totally unnecessary.
 * - Trying to push everything inside the stack and then writing some condition to pop them correctly.
 * - Didn't spend ,uch time in understanding a problem and tried to derive the stack solution directly.
 * - Should strictly stick to brute -> better -> optimal strategy.
 */
package stack;
import java.util.Stack;
public class SimplifyPath {

    public static void main(String[] args) {
        System.out.println(simplifyPath("/home/"));
    }

    public static String simplifyPath(String path) {
        String[] parts = path.split("/");

        Stack<String> stack = new Stack<>();

        for(String part :  parts){

            if(part.isEmpty() || part.equals(".")){
                continue;
            }

            if(part.equals("..")){
                if(!stack.isEmpty()){
                    stack.pop();
                }
            }
            else {
                stack.push(part);
            }
        }

        StringBuilder res = new StringBuilder();

        for(String dir : stack){
            res.append("/").append(dir);
        }

        return res.isEmpty() ? "/" : res.toString();
    }
}

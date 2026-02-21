/**
 * Problem:
 * <The Celebrity Problem>
 *
 * Link:
 * <https://www.geeksforgeeks.org/problems/the-celebrity-problem/1>
 *
 * Pattern:
 * <Elimination>
 *
 * Brute Force Intuition:
 * - For every person x:
 * - Check if he is known by everyone.
 * - Check if he knows anyone.
 *
 * - Why it is inefficient?
 * - We are comparing the relationship multiple times.
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(1)
 *
 * Better Approach:
 * - Pairwise elimination using stack.
 * - Push all the persons in the stack.
 * - A celebrity is the person who knows no one and is known by everyone.
 * - Everytime, pop out two persons from the stack.
 * - For example A and B:
 * - If A knows B then A cannot be celebrity, push B back in the stack.
 * - Else B cannot be celebrity as he is not known by A, push A back in the stack.
 * - In the end, either a single person is left or no person is left.
 * - If no one remains, then there is no celebrity.
 * - Else verify the potential candidate.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Why this is not optimal?
 * - Uses an extra space to check the potential candidates.
 *
 * Optimal Approach (used below):
 * - Find the potential candidate by checking if either he knows anyone or not.
 * - Verify the potential candidate.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Notes:
 * - Only check when i != x.
 */

package stack;

public class TheCelebrityProblem {

    public static void main(String[] args) {
        System.out.println(celebrity(new int[][]{{1,1,0}, {0,1,0}, {0,1,1}}));
    }

    public static int celebrity(int mat[][]) {
        int n = mat.length;

        int candidate = 0;

        // Find potential candidate

        for(int i = 1; i < n; i++){
            if(mat[candidate][i] == 1){
                candidate = i;
            }
        }

        // Verify candidate

        for(int i = 0; i < n; i++){

            if(i != candidate){

                if(mat[i][candidate] != 1) return -1;
                if(mat[candidate][i] != 0) return -1;
            }
        }
        return candidate;
    }
}

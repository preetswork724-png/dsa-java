/**
 * Problem:
 * <Tower Of Hanoi>
 *
 * Link:
 * <https://www.geeksforgeeks.org/problems/tower-of-hanoi-1587115621/1>
 *
 * Pattern:
 * <Recursion>
 *
 * Brute Force Intuition:
 * - To move n disks from source to destination:
 *
 *      Step 1:
 *      Move n - 1 disks from source → auxiliary
 *
 *      Step 2:
 *      Move largest disk from source → destination
 *
 *      Step 3:
 *      Move n - 1 disks from auxiliary → destination
 *
 * - The same problem repeats recursively for smaller disks.
 *
 *
 * Recursive Formula:
 *
 *      T(n) = T(n - 1) + 1 + T(n - 1)
 *
 * - Simplified:
 *
 *      T(n) = 2T(n - 1) + 1
 *
 *
 * Base Case:
 * - If only one disk exists:
 *
 *      Move directly from source → destination
 *
 * - Minimum moves:
 *
 *      1
 *
 *
 * Time Complexity:
 * - O(2^N)
 *
 * Space Complexity:
 * - O(N)
 *
 *
 * Notes:
 * - Tower of Hanoi is one of the most classic recursion problems.
 *
 *
 * - Core Recursive Insight:
 * - To move n disks:
 *      First solve the same problem for n - 1 disks.
 *
 *
 * - Why recursion works?
 * - We trust recursion to correctly move smaller disks.
 * - Once smaller disks are moved away,
 *   largest disk becomes movable.
 *
 *
 * - Important Constraint:
 * - Bigger disk cannot be placed on smaller disk.
 *
 * - However:
 * - Smaller disk CAN be placed on bigger disk.
 *
 *
 * - Important Clarification:
 * - During:
 *
 *      "Move n - 1 disks"
 *
 * - Largest disk remains untouched.
 * - Only smaller disks are being rearranged.
 *
 *
 * - Why is time complexity exponential?
 * - Every recursive call generates:
 *
 *      2 more recursive calls
 *
 * - Recursion tree keeps doubling.
 *
 *
 * - Recursive Growth:
 *
 *      Level 0 → 1 call
 *      Level 1 → 2 calls
 *      Level 2 → 4 calls
 *      Level 3 → 8 calls
 *
 * - Total calls:
 *
 *      2^N - 1
 *
 *
 * - Exact Minimum Moves Formula:
 *
 *      2^N - 1
 *
 *
 * - Why is space complexity O(N)?
 * - Recursive depth becomes:
 *
 *      n → n-1 → n-2 → ... → 1
 *
 * - Maximum active calls:
 *
 *      N
 *
 *
 * - Important Misconception:
 * - Time complexity is exponential,
 *   but space complexity is NOT exponential.
 *
 * - Why?
 * - Entire recursion tree is not stored simultaneously.
 * - Only one recursive path stays active at a time.
 *
 *
 * - Recursive Flow:
 *
 *      Move smaller disks away
 *      Move largest disk
 *      Bring smaller disks back
 *
 *
 * - This problem teaches:
 *      Recursive Decomposition
 *      Trust in Recursive Calls
 *      Divide and Conquer Thinking
 *      Recursive Tree Analysis
 *
 *
 * - Interview Insight:
 * - Tower of Hanoi is often used to test:
 *      Recursive reasoning
 *      Ability to visualize recursion
 *      Understanding of recursion tree growth
 *
 *
 * - Main recursive transition:
 *
 *      "Solve smaller identical problem first."
 */

package recursion;

public class TowerOfHanoi {
    public int towerOfHanoi(int n, int from, int to, int aux) {

        if(n == 1) return 1;

        return towerOfHanoi(n - 1, from, aux, to) + 1 +
                towerOfHanoi(n - 1, aux, from, to);
    }
}

/**
 * Problem:
 * <Gas Station>
 *
 * Link:
 * <https://leetcode.com/problems/gas-station/description/>
 *
 * Pattern:
 * <Greedy>
 *
 * Brute Force Intuition:
 * - Try out every possible starting position and simulate the entire circular trip.
 *
 * - Why it is inefficient?
 * - We never neglect the starting positions from where the next station could not be reached.
 * - If n = 10^5, worst case does 10^10 operations.
 *
 * Time Complexity:
 * - O(n^2)
 * Space Complexity:
 * - O(1)
 *
 * Better Approach intuition:
 * - Early pruning.
 * - Before simulating the entire trip, check whether it is even possible to make a circular trip.
 * - Sum up the gas at the gas stations and the costs.
 * - If totalGas < totalCost, then you will never be able to complete a circular trip from any starting index.
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(1)
 *
 * Why this is still not optimal?
 * - Still slow for large n.
 *
 * Optimal Approach (Used below):
 * - Imagine walking around a circle:
 * - If at some point you run iut of gas,
 * - That means the entire stretch that you just walked could not have a valid starting position.
 * - So, you jump ahead.
 * - Lets say you begin at start, and you run out of gas at some i:
 * - sum(totalGas) < sum(totalCost) or diff < 0.
 * - Lets say you choose some k between start and i.
 * - sum(start -> k-1) + sum(k -> i) < 0.
 * - Therefore, sum(start -> k - 1) >= 0.
 * - So, starting from k will also fail before or at the i.
 * - So, we eliminate the entire segment.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - If in the end, total >= 0:
 * - It means that we were able to complete the circular trip because we did not run out of gas despite the cost from one gas station to another.
 */

package greedy;

public class GasStation {
    public static void main(String[] args) {
        System.out.println(canCompleteCircuit(new int[]{1,2,3,4,5}, new int[]{3,4,5,1,2}));
    }

    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length, total = 0, tank = 0, start = 0;

        for(int i = 0; i < n; i++){

            int diff = gas[i] - cost[i];
            total += diff;
            tank += diff;

            if(tank < 0){
                start = i + 1;
                tank = 0;
            }
        }
        return total >= 0 ? start : -1;
    }
}

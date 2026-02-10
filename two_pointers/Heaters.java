/**
 * Problem:
 * <Heaters>
 *
 * Link:
 * <https://leetcode.com/problems/heaters/description/>
 *
 * Pattern:
 * <Sorting + two pointer>
 *
 * Brute Force Intuition:
 * - For each house in houses:
 * - Find the nearest heater.
 * - Track the maximum minimum distance globally.
 *
 * - Why it is inefficient?
 * - Very slow for large arrays.
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(1)
 *
 * Better Approach Intuition:
 * - Binary Search.
 * - Instead of linearly scanning all the positions of heaters repeatedly to find the nearest heater for a house:
 * - Sort the heaters array.
 * - Apply binary search to find the nearest heater to the left and the nearest heater to the right.
 * - Simply, find the ceil and floor for the current house position.
 * - Take the best out of both.
 * - Track the maximum minimum distance globally.
 *
 * - Why it is still not optimal?
 * - Because we are doing repeated work unnecessarily.
 * - From every house, the heaters are searched from scratch.
 * - Even though:
 * - Houses are processed in increasing order.
 * - Heater positions don't change.
 *
 * Time Complexity:
 * - O(H log T) {where H = houses, T = heaters}
 * Space Complexity:
 * - O(1)
 *
 * Optimal Approach (Used Below):
 * - Two-pointer / Greedy sweep.
 * - Sort houses & heaters.
 * - We discard a heater only if the future heater is closer.
 * - Heater pointer i:
 * - Never moves backward.
 * - Moves only when the future heater is closer.
 * - For each house:
 * - You compute the distance to the closest heater.
 * - Keep track of the maximum such distance.
 * - That max distance = minimum such radius.
 *
 * Time Complexity:
 * - O(N log N)
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - "We only discard a heater when a future heater is closer than or equal to the current closest distance from the house".
 * - "As arrays are sorted, due to monotonicity the current heater can be safely discarded."
 */

package two_pointers;

import java.util.Arrays;

public class Heaters {

    public static void main(String[] args) {
        System.out.println(findRadius(new int[]{1,2,3}, new int[]{2}));
    }

    public static int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);

        int i = 0, minRadius = 0;

        for(int house : houses){

            while(i + 1 < heaters.length && Math.abs(heaters[i+1] - house) <=
                    Math.abs(heaters[i] - house)){
                i++;
            }

            minRadius = Math.max(minRadius, Math.abs(heaters[i] - house));
        }
        return minRadius;
    }
}

/**
 * Problem:
 * <Boats to Save People>
 *
 * Link:
 * <https://leetcode.com/problems/boats-to-save-people/>
 *
 * Pattern:
 * <Two Pointer + Counting Sort>
 *
 * Brute Force Intuition:
 * - Greedy.
 * - For each unassigned person:
 * - Either it goes alone.
 * - Or pair with it every possible partner.
 * - Recursively minimize the number of boats.
 * - Explore all combinations.
 *
 * - Why it is inefficient?
 * - Exploring all combinations yields in exponential time.
 *
 * Time Complexity:
 * - O(2^N)
 * Space Complexity:
 * - O(N) {Recursion Stack}
 *
 * Better Approach Intuition:
 * - Sorting + Greedy + 2 pointers.
 * - Sort the array.
 * - Try to pair up the lightest person with the heaviest person.
 * - If their weight exceeds the limit, then the heaviest person gets to board the boat.
 * - If their weight doesn't exceed the limit, the pair gets to board the boat.
 *
 * Time Complexity:
 * - O(N log N)
 * Space Complexity:
 * - O(1)
 *
 * Why it is still not optimal?
 * - Though the above solution is optimal, it requires sorting the array when the constraints are small.
 *
 * Optimal Approach (Used Below):
 * - Two pointer + counting sort.
 * - Instead of sorting:
 * - Build frequency.
 * - Now, simulate with 2 pointers exactly like sorted 2 pointer, but using counts.
 * - light -> from 1 upward, heavy -> from limit downward.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(limit)
 *
 * Notes:
 * - Before pairing up, check if freq[light] > 0 and then only decrement freq[light].
 */

package two_pointers;

public class BoatsToSavePeople {
    public static void main(String[] args) {
        System.out.println(numRescueBoats(new int[]{1,2}, 3));
    }

    public static int numRescueBoats(int[] people, int limit) {
        int[] freq = new int[limit + 1];

        for(int weight : people){
            freq[weight]++;
        }

        int light = 1, heavy = limit, boats = 0;

        while(light <= heavy){

            while(light <= heavy && freq[light] == 0) light++;
            while(light <= heavy && freq[heavy] == 0) heavy--;

            if(light > heavy) break;

            freq[heavy]--;

            if(light + heavy <= limit && freq[light] > 0){
                freq[light]--;
            }
            boats++;
        }
        return boats;
    }
}

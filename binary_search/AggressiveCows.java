/**
 * Problem:
 * <Aggressive Cows>
 *
 * Link:
 * <https://www.geeksforgeeks.org/problems/aggressive-cows/1>
 *
 * Pattern:
 * <Binary Search on answer>
 *
 * Brute Force Intuition:
 * - Sort the array.
 * - Generate all the permutations of stalls of size k.
 * - For each permutation, calculate the minimum distance adjacent distance.
 * - Take maximum of those minimums.
 *
 * - Why it is inefficient?
 * - Brute force requires generating combinations where number of ways are C(n, k).
 * - For each, distance check = O(k).
 * - This is exponential and completely impractical.
 * - We are exploring arrangements, not answers.
 *
 * Time Complexity:
 * - O(C(n, k) * k)
 * Space Complexity:
 * - O(k)
 *
 * Better Approach Intuition:
 * - Binary search + greedy.
 * - Sort the array.
 * - Instead of generating the combinations, iterate over the range.
 * - lower bound = 1, upper bound = maxPosition - minPosition.
 * - Place cow at the first stall.
 * - Try all the possible minimum distances.
 * - Take maximum of these distances.
 * - To place the next cow, check its distance with the last cow placed.
 * - If the difference between their distances is greater than or equal to the current distance, place the cow at the current stall.
 *
 * Time Complexity:
 * - O(n * D) {where D = max(stalls) - min(stalls)}
 * Space Complexity:
 * - O(1)
 *
 * Why this is still not optimal?
 * - The range is monotonic and therefore linear search is not the most optimal one.
 *
 * Optimal Approach (Used below):
 * - Apply binary search on the range.
 * - For a distance d, you can place the cow.
 * - Try to maximize d.
 *
 * Time Complexity:
 * - O(D * log n) {where D = max(stalls) - min(stalls)}
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - Always check the distance before placing the current cow.
 * - Remember to update the lastPlaced after placing the cow.
 */

package binary_search;
import java.util.Arrays;
public class AggressiveCows {

    public static void main(String[] args) {
        System.out.println(aggressiveCows(new int[]{1,2,4,8,9}, 3));
    }

    public static int aggressiveCows(int[] stalls, int k) {

        Arrays.sort(stalls);

        int n = stalls.length, minPosition = stalls[0], maxPosition = stalls[n-1];

        int low = 1, high = maxPosition - minPosition, maxDist = 0;

        while(low <= high){

            int mid = low + (high - low) / 2;

            if(canPlace(mid, stalls, k)){
                maxDist = mid;
                low = mid + 1;
            }
            else{
                high = mid - 1;
            }
        }
        return maxDist;
    }

    public static boolean canPlace(int dist, int[] stalls, int k){
        int cows = 1, lastPlaced = stalls[0];

        for(int i = 1; i < stalls.length; i++){

            if(stalls[i] - lastPlaced >= dist){
                lastPlaced = stalls[i];
                cows++;
            }

            if(cows == k) return true;
        }
        return false;
    }
}

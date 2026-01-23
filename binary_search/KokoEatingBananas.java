/**
 * Problem:
 * <Koko Eating Bananas>
 *
 * Link:
 * <https://leetcode.com/problems/koko-eating-bananas/description/>
 *
 * Pattern:
 * <Binary Search on answer>
 *
 * Brute Force Intuition:
 * - Try all the possible speeds between 1 to max(piles).
 * - Return the earliest day when koko can eat all the bananas.
 * - Else, keep iterating.
 *
 * - Why it is inefficient?
 * - Brute force requires simulating each speed from 1 to max(piles) which could be as large as 10^9.
 * - Too slow.
 *
 * Time Complexity:
 * - O(max(piles) * N)
 * Space Complexity:
 * - O(1)
 *
 * Better and Optimal Approach (Used Below):
 * - Instead of linearly searching the entire range of speeds from 1 to max(piles), apply binary search.
 * - Binary search on speed k using monotonicity.
 * - Even if you find a speed, try to reduce it as Koko likes to eat the bananas slowly.
 *
 * Time Complexity:
 * - O(N log M) {where M = max(piles)}
 * Space Complexity:
 * - O(1)
 *
 * Why better approach is the optimal one?
 * - A better approach usually reduces the search space somehow but not optimally.
 * - Uses partial structure like sorting, prefix sums, hashing, etc.
 * - But in this problem, the search space is a contiguous integer range.
 * - The decision function canEat(k) is strictly monotonic.
 * - The only principled way to reduce a monotonic integer range is binary search.
 *
 * Notes:
 * - Whenever:
 * - You are summing divisions.
 * - Values go up to 10^9.
 * - You multiply or add many of them.
 * - Always use long for accumulators.
 *
 * Mistake log:
 * - Did not handle integer overflow.
 */
package binary_search;

public class KokoEatingBananas {

    public static void main(String[] args) {
        System.out.println(minEatingSpeed(new int[]{3,6,7,11}, 8));
    }

    public static int minEatingSpeed(int[] piles, int h) {
        int n  = piles.length, low = 1, high = maxSpeed(piles), minSpeed = high;

        while(low <= high){
            int mid = low + (high - low) / 2;

            if(canEat(mid, piles, h)){
                minSpeed = mid;
                high = mid - 1;
            }
            else{
                low = mid + 1;
            }
        }
        return minSpeed;
    }

    public static int maxSpeed(int[] piles){
        int max = Integer.MIN_VALUE;
        for(int x : piles) max = Math.max(max, x);
        return max;
    }

    public static boolean canEat(int speed, int[] piles, int h){
        long hoursTaken = 0;

        for(int bananas : piles){
            hoursTaken += (bananas + speed - 1L) / speed;
            if(hoursTaken > h) return false; // early break optimization
        }
        return hoursTaken <= h;
    }
}

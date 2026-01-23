/**
 * Problem:
 * <Capacity To Ship Packages Within D Days>
 *
 * Link:
 * <https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/description/>
 *
 * Pattern:
 * <Binary Search on answer>
 *
 * Brute Force Intuition:
 * - Try all the possible capacities between the heaviest package to sum of all packages.
 * - Return the earliest capacity when all the packages can be shipped.
 *
 * - Why it is inefficient?
 * - Brute force requires simulating each capacity from the heaviest package to sum of all packages which could be as large as 10^9.
 * - Too slow.
 *
 * Time Complexity:
 * - O((sum of all packages) * N)
 * Space Complexity:
 * - O(1)
 *
 * Better and Optimal Approach (Used Below):
 * - Instead of linearly searching the entire range of capacities from the heaviest package to sum of all packages, apply binary search.
 * - Binary search on capacity k using monotonicity.
 * - Even if you find a capacity, try to reduce prevent the ship from overloading.
 *
 * Time Complexity:
 * - O(N log M) {where M = sum of all packages}
 * - Space Complexity:
 * - O(1)
 *
 * Why better approach is the optimal one?
 * - A better approach usually reduces the search space somehow but not optimally.
 * - Uses partial structure like sorting, prefix sums, hashing, etc.
 * - But in this problem, the search space is a contiguous integer range.
 * - The decision function canShip(capacity) is strictly monotonic.
 * - The only principled way to reduce a monotonic integer range is binary search.
 *
 * Notes:
 * - Remember that the weights should be loaded in the order they are given.
 *
 * Mistake log:
 * - Overcomplicated the process of calculating the sum.
 * - Before adding weight to the sum, remember to check if it exceeds the capacity.
 * - If it does, increment the day and clear the sum.
 * - Then add the current weight to the sum.
 */

package binary_search;

public class CapacityToShipPackagesWithinDDays {

    public static void main(String[] args) {
        System.out.println(shipWithinDays(new int[]{3,2,2,4,1,4}, 3));
    }

    public static int shipWithinDays(int[] weights, int days) {
        int n = weights.length, heaviestPackage = 0, sumOfAllPackages = 0;

        for(int wt : weights){
            heaviestPackage = Math.max(heaviestPackage, wt);
            sumOfAllPackages += wt;
        }

        int low = heaviestPackage, high = sumOfAllPackages, minDays = sumOfAllPackages;

        while(low <= high){
            int mid = low + (high - low) / 2;

            if(canShip(mid, weights, days)){
                minDays = mid;
                high = mid - 1;
            }
            else{
                low = mid + 1;
            }
        }
        return minDays;
    }

    public static boolean canShip(int capacity, int[] weights, int days){
        int sum = 0, minDays = 0;

        for(int wt : weights){

            if(sum + wt > capacity){
                minDays++;
                sum = 0;
            }

            sum += wt;
        }
        if(sum <= capacity) minDays++;
        return minDays <= days;
    }
}

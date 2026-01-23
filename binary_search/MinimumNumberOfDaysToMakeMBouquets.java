/**
 * Problem:
 * <Minimum Number of Days to Make m Bouquets>
 *
 * Link:
 * <https://leetcode.com/problems/minimum-number-of-days-to-make-m-bouquets/description/>
 *
 * Pattern:
 * <Binary Search on answer>
 *
 * Brute Force Intuition:
 * - Simulate day by day.
 * - See all the flowers that have grown before or on that day.
 * - Try to make m bouquets from k consecutive flowers.
 * - Return the day if you are able to make m or greater than m bouquets.
 * - Else, keep iterating.
 *
 * - Why it is inefficient?
 * - Brute force requires simulating each day from 1 to max(bloomDay) which could be as large as 10^9.
 * - Too slow.
 *
 * Time Complexity:
 * - O(max(bloomDay) * N)
 * Space Complexity:
 * - O(1)
 *
 * Better Approach Intuition:
 * - Instead of searching the entire range of days from 1 to max(bloomDay).
 * - Search only for the days that have occurred in bloomDay.
 * - Clone and sort the cloned array.
 * - Now you have to do at max N searches.
 * - For each day in the sorted array, try to make m bouquets from k consecutive flowers.
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(N)
 *
 * Why this is still not optimal?
 * - Requires sorting the array.
 * - Uses extra space to create clone of the array.
 *
 * Optimal Approach (Used below):
 * - Sorting or brute force tells us that the answer lies in a range and that range is monotonic.
 * - Therefore, we find the lower bound which is min(bloomDay) and the upper bound which is max(bloomDay).
 * - Now apply binary search on the range.
 * - For a day, if you are able to make m bouquets from k consecutive flowers then store that day as a potential answer and reduce the upper bound further.
 * - Because the question asks for minimum number of days.
 *
 * Time Complexity:
 * - O(N log M) {where M =  max(bloomDay)}
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - Edge case:
 * - If the length of the array / number of flowers < number of bouquets * flowers per bouquet then you will never able to make m bouquets from k consecutive flowers.
 */
package binary_search;

public class MinimumNumberOfDaysToMakeMBouquets {
    public static void main(String[] args) {
        System.out.println(minDays(new int[]{1,10,3,10,2}, 3, 1));
    }

    public static int minDays(int[] bloomDay, int m, int k) {
        int n = bloomDay.length, low = Integer.MAX_VALUE, high = Integer.MIN_VALUE;

        if((long)m * k > n) return -1;

        for(int day : bloomDay){
            low = Math.min(low, day);
            high = Math.max(high, day);
        }

        int minDays = high;

        while(low <= high){

            int mid = low + (high - low) / 2;

            if(canMake(mid, bloomDay, m, k)){
                minDays = mid;
                high = mid - 1;
            }
            else{
                low = mid + 1;
            }
        }
        return minDays;
    }

    public static boolean canMake(int day, int[] bloomDay, int m, int k){
        int flowers = 0, bouquets = 0;

        for(int bloomTime : bloomDay){
            if(bloomTime <= day){
                flowers++;

                if(flowers == k){
                    bouquets++;
                    flowers = 0;
                }

            }
            else{
                flowers = 0;
            }
        }
        return bouquets >= m;
    }
}

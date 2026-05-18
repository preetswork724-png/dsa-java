/**
 * Problem:
 * <Minimum Platforms>
 *
 * Link:
 * <https://www.geeksforgeeks.org/problems/minimum-platforms-1587115620/1>
 *
 * Pattern:
 * <Greedy / Sweep Line / Overlapping Intervals>
 *
 *
 * Brute Force Intuition:
 * - For every train:
 *      - Compare it with every other train.
 *      - Count how many trains overlap with it.
 * - Maximum overlap among all trains
 *   becomes the minimum platforms required.
 *
 * - Why?
 * - Every overlapping train needs a separate platform.
 *
 * - Why it is inefficient?
 * - For every train we scan all other trains.
 * - Repeated overlap computations occur.
 *
 * Time Complexity:
 * - O(N^2)
 *
 * Space Complexity:
 * - O(1)
 *
 *
 * Better Approach Intuition:
 * - Treat every arrival and departure as events.
 *
 * - Arrival:
 *      +1 platform occupied
 *
 * - Departure:
 *      -1 platform occupied
 *
 * - Sort trains based on timings and process chronologically.
 * - Track:
 *      - Current occupied platforms
 *      - Maximum occupied platforms
 *
 * - Why does this work?
 * - At any moment:
 *      occupied platforms =
 *      trains currently present at station
 *
 * - Therefore:
 *      maximum occupied platforms =
 *      minimum platforms required
 *
 * Time Complexity:
 * - O(N log N)
 *
 * Space Complexity:
 * - O(1)
 *
 *
 * Optimal Approach (Used Below):
 * - Sort:
 *      - arrival[]
 *      - departure[]
 *
 * - Use two pointers:
 *      i -> arrivals
 *      j -> departures
 *
 * - Traverse chronologically:
 *
 *      If next train arrives before
 *      earliest departure:
 *
 *          - Need extra platform
 *          - platforms++
 *          - Move arrival pointer
 *
 *      Else:
 *
 *          - One train departed
 *          - Platform becomes free
 *          - platforms--
 *          - Move departure pointer
 *
 * - Track maximum platforms at any time.
 *
 *
 * Time Complexity:
 * - O(N log N)
 * - Sorting dominates.
 *
 * Space Complexity:
 * - O(1)
 *
 *
 * Key Greedy / Sweep-Line Insight:
 * - We are NOT maximizing non-overlapping intervals.
 *
 * - We are tracking:
 *      maximum simultaneous overlaps
 *
 * - Because:
 *      simultaneous trains =
 *      simultaneous platform usage
 *
 *
 * Core Observation:
 *
 * - When overlap occurs:
 *      platforms++
 *
 * - When train departs:
 *      platforms--
 *
 * - Final answer:
 *      maximum value of platforms
 *
 *
 * Important Boundary Condition:
 *
 * - If:
 *      arrival <= departure
 *
 * - Then overlap exists.
 *
 * - Why?
 * - A platform cannot serve two trains
 *   at the same exact time.
 *
 * - Therefore:
 *      arr[i] <= dep[j]
 *
 * - NOT:
 *      arr[i] < dep[j]
 *
 *
 * Dry Run:
 *
 * arr = [900, 940, 950, 1100, 1500, 1800]
 * dep = [910, 1120, 1130, 1200, 1900, 2000]
 *
 * Sorted:
 *
 * arr = [900, 940, 950, 1100, 1500, 1800]
 * dep = [910, 1120, 1130, 1200, 1900, 2000]
 *
 * Flow:
 *
 * 900 arrives:
 *      platforms = 1
 *
 * 910 departs:
 *      platforms = 0
 *
 * 940 arrives:
 *      platforms = 1
 *
 * 950 arrives:
 *      platforms = 2
 *
 * 1100 arrives:
 *      platforms = 3
 *
 * Maximum = 3
 *
 * Answer = 3
 *
 *
 * Real-Life Analogy:
 * - Think of platforms like:
 *      - Parking slots
 *      - Hotel rooms
 *      - CPU resources
 *      - Meeting rooms
 *
 * - Answer always equals:
 *      maximum simultaneous occupancy
 *
 *
 * Similar Problems:
 * - Meeting Rooms II
 * - Maximum Overlapping Intervals
 * - Car Pooling
 * - Number of Airplanes in the Sky
 * - Sweep Line Problems
 *
 *
 * Common Mistakes:
 *
 * 1. Thinking this is Activity Selection
 * - Wrong.
 * - We are NOT maximizing compatible intervals.
 *
 * 2. Tracking minimum platforms instead of maximum
 * - We need peak overlap.
 *
 * 3. Using:
 *      arr[i] < dep[j]
 * - Wrong boundary handling.
 *
 * 4. Updating answer during departure
 * - Peak occurs during arrivals.
 *
 *
 * Key Difference from N Meetings In One Room:
 *
 * - N Meetings In One Room:
 *      Maximize NON-overlapping intervals
 *
 * - Minimum Platforms:
 *      Find MAXIMUM overlapping intervals
 *
 * - One removes conflicts.
 * - Other measures conflicts.
 */

package intervals;

import java.util.Arrays;

public class MinimumPlatforms {
    public int minPlatform(int arr[], int dep[]) {
        Arrays.sort(arr);
        Arrays.sort(dep);

        int i = 0, j = 0, n = arr.length, platforms = 0,
                maxPlatforms = 0;

        while (i < n && j < n) {

            if (arr[i] <= dep[j]) {
                platforms++;
                maxPlatforms = Math.max(maxPlatforms, platforms);
                i++;
            } else {
                platforms--;
                j++;
            }
        }
        return maxPlatforms;
    }
}

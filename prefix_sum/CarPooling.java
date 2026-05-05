/**
 * Problem:
 * <Car Pooling>
 *
 * Link:
 * <https://leetcode.com/problems/car-pooling/>
 *
 * Pattern:
 * <Prefix Sum / Difference Array / Sweep Line>
 *
 * -----------------------------------------------------
 *
 * Brute Force Intuition:
 * - Simulate the journey kilometer by kilometer.
 * - For every trip [numPassengers, from, to):
 *      Add passengers to every point from 'from' to 'to - 1'.
 * - Track current passengers at each location.
 * - If at any point passengers exceed capacity → return false.
 *
 * - Why it is inefficient?
 * - For each trip, we iterate over a range of locations.
 * - Repeated updates on overlapping segments.
 *
 * Time Complexity:
 * - O(N * maxDistance) ≈ O(N * 1000)
 *
 * Space Complexity:
 * - O(1000)
 *
 * -----------------------------------------------------
 *
 * Better Approach Intuition:
 * - Treat each trip as two events:
 *      Pickup  → +numPassengers at 'from'
 *      Dropoff → -numPassengers at 'to'
 *
 * - Store all events and sort them by location.
 * - Traverse events in order and maintain current passengers.
 *
 * - Important Edge Case:
 * - If pickup and drop occur at the same location,
 *   drop must be processed BEFORE pickup.
 *
 * - Why it is still not optimal?
 * - Sorting events takes O(N log N).
 * - We are not utilizing the bounded constraint (location ≤ 1000).
 *
 * Time Complexity:
 * - O(N log N)
 *
 * Space Complexity:
 * - O(N)
 *
 * -----------------------------------------------------
 *
 * Optimal Approach (Used Below):
 * - Use Difference Array (Prefix Sum Technique).
 *
 * - Key Idea:
 *      Instead of updating every point in a range,
 *      mark only the changes:
 *
 *      timeline[from] += passengers   (pickup)
 *      timeline[to]   -= passengers   (drop)
 *
 * - After marking all trips:
 *      Take prefix sum over timeline to simulate actual passenger count.
 *
 * - While computing prefix sum:
 *      If passengers exceed capacity → return false.
 *
 * - Steps:
 *      1. Initialize array of size 1001 (locations range).
 *      2. Apply +passengers at 'from'.
 *      3. Apply -passengers at 'to'.
 *      4. Traverse and accumulate passengers.
 *      5. Check capacity at each step.
 *
 * -----------------------------------------------------
 *
 * Time Complexity:
 * - O(N + 1000) ≈ O(N)
 *
 * Space Complexity:
 * - O(1000) ≈ O(1)
 *
 * -----------------------------------------------------
 *
 * Why optimal approach is the best?
 * - Converts range updates into point updates.
 * - Avoids repeated work (unlike brute force).
 * - Avoids sorting (unlike sweep line).
 * - Leverages constraints for linear-time solution.
 *
 * -----------------------------------------------------
 *
 * Notes:
 * - This is a classic "range addition + prefix sum" problem.
 * - Trips are half-open intervals: [from, to)
 *      → passengers leave at 'to'
 * - Order of events matters in sweep line:
 *      drop (-) must come before pickup (+)
 * - Prefix sum simulates real-time accumulation.
 * - Works because passenger changes are additive.
 *
 * -----------------------------------------------------
 *
 * Mistake Log:
 * - Tried merging intervals → incorrect abstraction.
 * - Ignored ordering of events at same location.
 * - Forgot that drop happens before pickup at same index.
 * - Missed difference array optimization.
 *
 */

package prefix_sum;

public class CarPooling {
    public boolean carPooling(int[][] trips, int capacity) {
        int[] timeLine = new int[1001];

        for(int[] trip : trips){
            timeLine[trip[1]] += trip[0];
            timeLine[trip[2]] -= trip[0];
        }

        int current = 0;

        for(int passengers : timeLine){
            current += passengers;

            if(current > capacity) return false;
        }

        return true;
    }
}

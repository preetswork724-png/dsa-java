/**
 * Problem:
 * <Non-overlapping Intervals>
 *
 * Link:
 * <https://leetcode.com/problems/non-overlapping-intervals/description/>
 *
 * Pattern:
 * <Greedy / Merge Intervals>
 *
 * Brute Force Intuition:
 * - Generate all possible subsets of intervals.
 * - For each subset:
 * - Check whether intervals overlap or not.
 * - Keep track of the largest valid non-overlapping subset.
 * - Final answer:
 * - total intervals - largest valid subset.
 *
 * - Why it is inefficient?
 * - Every interval has 2 choices:
 * - Include or Exclude.
 * - Generates exponential number of subsets.
 *
 * Time Complexity:
 * - O(2^N * N)
 * Space Complexity:
 * - O(N)
 *
 * Better Approach Intuition:
 * - Sort intervals by start time.
 * - For every overlapping pair:
 * - Remove one interval greedily.
 * - If intervals overlap:
 * - Keep the interval with smaller ending time.
 * - Because smaller ending time leaves more room for future intervals.
 *
 * - Why it is still not fully optimal?
 * - Although the greedy choice is correct,
 * - Sorting by start time makes overlap handling slightly less intuitive.
 * - We still repeatedly compare active interval endings.
 *
 * Time Complexity:
 * - O(N log N)
 * Space Complexity:
 * - O(1)
 *
 * Optimal Approach (Used Below):
 * - Sort intervals by ending time.
 * - Always keep the interval that ends earliest.
 * - Why?
 * - Smaller ending time maximizes future flexibility.
 * - Traverse intervals:
 * - If current interval starts before previous interval ends:
 * - Overlap exists.
 * - Remove current interval.
 * - Else:
 * - Keep current interval and update previous ending time.
 * - Track total removals.
 *
 * Time Complexity:
 * - O(N log N)
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - Minimum removals problem =
 * - Maximum non-overlapping intervals problem.
 * - Touching intervals are valid:
 * - [1,2] and [2,3] do NOT overlap.
 * - Greedy works because:
 * - Keeping smaller ending interval can never reduce future possibilities.
 * - This is an interval scheduling optimization problem.
 * - Sort by end time is the key greedy insight.
 */

package intervals;

import java.util.Arrays;

public class NonOverLappingIntervals {
    public int eraseOverlapIntervals(int[][] intervals) {

        Arrays.sort(intervals, (a, b) -> a[1] - b[1]);

        int n = intervals.length;
        int prevEnd = intervals[0][1], removals = 0;

        for(int i = 1; i < n; i++){

            int currStart = intervals[i][0];
            int currEnd = intervals[i][1];

            if(currStart >= prevEnd){
                prevEnd = currEnd;
            }
            else{
                removals++;
            }
        }
        return removals;
    }
}

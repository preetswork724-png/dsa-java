/**
 * Problem:
 * <N Meetings In One Room>
 *
 * Link:
 * <https://www.geeksforgeeks.org/problems/n-meetings-in-one-room-1587115620/1>
 *
 * Pattern:
 * <Greedy / Merge Intervals>
 *
 * Brute Force Intuition:
 * - Generate all possible subsets of meetings.
 * - For every subset:
 * - Check whether meetings overlap or not.
 * - If meetings do not overlap:
 * - Update the maximum number of meetings.
 *
 * - Why it is inefficient?
 * - Every meeting has two choices:
 * - Include or Exclude.
 * - Generates exponential number of subsets.
 *
 * Time Complexity:
 * - O(2^N * N)
 * Space Complexity:
 * - O(N)
 *
 * Better Approach Intuition:
 * - Sort meetings by start time.
 * - For every overlapping meeting:
 * - Try selecting one meeting greedily.
 * - Prefer meetings with smaller ending times.
 * - Because earlier finishing meetings leave more room for future meetings.
 *
 * - Why it is still not optimal?
 * - Sorting by start time does not directly optimize future compatibility.
 * - Overlap handling becomes less intuitive.
 *
 * Time Complexity:
 * - O(N log N)
 * Space Complexity:
 * - O(1)
 *
 * Optimal Approach (Used Below):
 * - Sort meetings by ending time.
 * - Always select the meeting that finishes earliest.
 * - Why?
 * - Earlier finishing meetings maximize future scheduling opportunities.
 * - Traverse all meetings:
 * - If current meeting starts strictly after previous selected meeting ends:
 * - Select the meeting.
 * - Update previous ending time.
 * - Track total selected meetings.
 *
 * Time Complexity:
 * - O(N log N)
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - This is a classic Interval Scheduling Optimization problem.
 * - Greedy works because:
 * - Keeping the meeting with smaller end time can never reduce future possibilities.
 * - Important constraint:
 * - start time cannot equal previous end time.
 * - Therefore:
 * - currStart > prevEnd
 * - NOT:
 * - currStart >= prevEnd
 * - This problem maximizes compatible intervals,
 * - not simultaneous intervals.
 */

package intervals;

import java.util.Arrays;

public class NMeetingsInOneRoom {
    public int maxMeetings(int start[], int end[]) {

        int n = start.length;

        int[][] intervals = new int[n][2];

        for(int i = 0; i < n ; i++){
            intervals[i][0] = start[i];
            intervals[i][1] = end[i];
        }

        Arrays.sort(intervals, (a, b) -> a[1] - b[1]);

        int meetings = 1;

        int prevEnd = intervals[0][1];

        for(int i = 1; i < n; i++){
            int currStart = intervals[i][0];
            int currEnd = intervals[i][1];

            if(currStart > prevEnd){
                meetings++;
                prevEnd = currEnd;
            }
        }
        return meetings;
    }
}

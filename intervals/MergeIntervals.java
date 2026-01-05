/**
 * Problem:
 * <Merge Intervals>
 *
 * Link:
 * <https://leetcode.com/problems/merge-intervals/description/>
 *
 * Pattern:
 * <Merge intervals>
 *
 * Brute Force Intuition:
 * - Try all the intervals.
 * - Compare each i interval with every j interval.
 * - If they overlap -> merge -> update.
 * - Repeat the process until nothing changes.
 *
 * - Why it is inefficient?
 * - Redundant comparisons.
 * - Hard to maintain cleanly.
 * - Very slow for large N.
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(1)
 *
 * Better & Optimal Approach (Used below):
 * - If we sort the intervals by the start time, then we only need to compare it with the adjacent intervals.
 * - You never look backward.
 *
 * Time Complexity:
 * - O(N log N)
 * Space Complexity:
 * - O(N)
 *
 * Notes:
 * - Maintain a list, if the list is empty push the current interval.
 * - Else, compare it with the current interval.
 * - Two cases :-
 * - If the start of the next interval is less than or equal to the end of the current interval, it is an overlapping one,
 * - Merge and update the interval.
 * - Else, make the next interval as the current interval and continue.
 *
 */
package intervals;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
public class MergeIntervals {

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(merge(new int[][]{{1,4}, {4,5}})));
    }

    public static int[][] merge(int[][] intervals) {
        int n = intervals.length;

        if(n == 0) return new int[0][0];

        Arrays.sort(intervals, (a,b) -> a[0] - b[0]);

        List<int[]> merged = new ArrayList<>();

        int[] curr = intervals[0];
        merged.add(curr);

        for(int i = 1; i < n; i++){
            int[] next = intervals[i];

            if(next[0] <= curr[1]){
                curr[1] = Math.max(curr[1], next[1]);
            }
            else{
                curr = next;
                merged.add(curr);
            }
        }
        return merged.toArray(new int[merged.size()][2]);
    }
}

/**
 * Problem:
 * <Overlapping Intervals>
 *
 * Link:
 * <https://www.geeksforgeeks.org/problems/overlapping-intervals--174556/1>
 *
 * Pattern:
 * <Merge Intervals>
 *
 * Brute Force Intuition:
 * - Check all the intervals.
 *
 * - Why it is inefficient?
 * - Too many redundant comparisons.
 * - Very slow for large arrays.
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(1)
 *
 * Optimal Approach Intuition:
 * - Sort the list by their start time.
 * - Only check adjacent intervals.
 *
 * Time Complexity:
 * - O(N log N)
 * Space Complexity:
 * - O(1)
 *
 * What to say in questions where better approach is the optimal one?
 * - In this question, we sort the array in increasing order of its start time.
 * - And generally in better approaches we perform sorting or storing prefix sums.
 * - Sorting helps to reduce the time complexity as we don't need to compare backwards.
 *
 * Notes:
 * - When no overlap exists, update the current List / array correctly.
 */
package intervals;
import java.util.Arrays;
public class OverlappingIntervals {

    public static void main(String[] args) {
        System.out.println(isIntersect(new int[][]{{1,3},{5,7},{2,4},{6,8}}));
    }

    public static boolean isIntersect(int[][] intervals) {
        int n = intervals.length;

        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        int[] curr = intervals[0];

        for(int i = 1; i < n; i++){
            int[] next = intervals[i];

            if(next[0] <= curr[1]){
                return true;
            }else{
                curr = next;
            }
        }
        return false;
    }
}

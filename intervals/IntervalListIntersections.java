/**
 * Problem:
 * <Interval List Intersections>
 *
 * Link:
 * <https://leetcode.com/problems/interval-list-intersections/description/>
 *
 * Pattern:
 * <Merge Intervals>
 *
 * Brute Force Intuition:
 * - The intersection is when :-
 * - start = Math.max(A.start, B.start).
 * - end = Math.min(A.end, B.end).
 * - if start <= end -> intersection exists.
 * - Try all the intervals of list 1 and take intersections from list2.
 *
 * - Why it is inefficient?
 * - Too many redundant comparisons.
 * - Very sloe if the list is large.
 *
 * Time Complexity:
 * - O(N * M)
 * Space Complexity:
 * - O(N)
 *
 * Better Approach Intuition:
 * - As list is sorted, for each interval in first list :-
 * - Binary search candidate overlaps in the second list.
 * - Only check neighbors for possible overlap.
 *
 * - Why it is still not optimal ?
 * - This reduces comparison with everything but this is still not optimal.
 *
 * Time Complexity:
 * - O(N log M)
 * Space Complexity:
 * - O(N)
 *
 * Optimal Approach (Used below) :
 * - Use 2-pointers as the list is sorted.
 * - Take the intersection.
 * - Move the pointer with the small end.
 *
 * Time Complexity:
 * - O(N + M)
 * Space Complexity:
 * - O(Math.min(N,M)
 *
 * Notes:
 * - Advance the pointers in the same way as it is done in merge sort.
 * - But do not merge the remainder of any of the lists.
 */
package intervals;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
public class IntervalListIntersections {

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(intervalIntersection(new int[][]{{1,3}, {5,9}}, new int[][]{})));
    }

    public static int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        List<int[]> merged = new ArrayList<>();

        int n = firstList.length, m = secondList.length, i = 0, j = 0;

        while (i < n && j < m) {
            int start = Math.max(firstList[i][0], secondList[j][0]);
            int end = Math.min(firstList[i][1], secondList[j][1]);

            if (start <= end) {
                merged.add(new int[]{start, end});
            }

            if (firstList[i][1] < secondList[j][1]) i++;
            else j++;
        }
        return merged.toArray(new int[merged.size()][2]);
    }
}

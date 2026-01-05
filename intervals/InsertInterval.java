/**
 * Problem:
 * <Insert Interval>
 *
 * Link:
 * <https://leetcode.com/problems/insert-interval/description/>
 *
 * Pattern:
 * <Merge Intervals>
 *
 * Brute Force Intuition:
 * - Copy the array to a List.
 * - Insert the new interval anywhere in the list.
 * - Sort the List by start time.
 * - Merge the overlapping intervals.
 *
 * - Why it is inefficient?
 * - To insert one interval, we end up sorting the entire list.
 * - This step adds additional time complexity which also result in loss of information like the array is already sorted by start time and merged.
 *
 * Time Complexity:
 * - O(N Log N)
 * Space Complexity:
 * - O(N)
 *
 * Better & Optimal Approach (Used below):
 * - Just figure out the insertion position of the new interval in the existing interval and add it there.
 * - Merge the overlapping ones.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Notes:
 * - Remember to sort the new interval before adding it to the existing intervals.
 *
 */
package intervals;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
public class InsertInterval {

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(insert(new int[][]{{1,3},{6,9}}, new int[]{2,5})));
    }

    public static int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> merged = new ArrayList<>();

        for(int[] interval : intervals){
            merged.add(interval);
        }

        boolean inserted = false;

        int[] sortedNewInterval = {Math.min(newInterval[0], newInterval[1]), Math.max(newInterval[0], newInterval[1])};

        if(merged.size() == 0) return new int[][]{sortedNewInterval};

        for(int i = 0; i < merged.size(); i++){
            if(merged.get(i)[0] > sortedNewInterval[0]){
                merged.add(i, sortedNewInterval);
                inserted = true;
                break;
            }
        }

        if(!inserted) merged.add(sortedNewInterval);

        List<int[]> ans = new ArrayList<>();
        int[] curr = merged.get(0);
        ans.add(curr);

        for(int i = 1; i < merged.size(); i++){
            int[] next = merged.get(i);

            if(next[0] <= curr[1]){
                curr[1] = Math.max(curr[1], next[1]);
            }else{
                curr = next;
                ans.add(curr);
            }
        }

        return ans.toArray(new int[ans.size()][2]);
    }
}

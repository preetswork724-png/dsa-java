/**
 * Problem:
 * <Meeting Rooms II>
 *
 * Link:
 * <https://www.geeksforgeeks.org/problems/attend-all-meetings-ii/1>
 *
 * Pattern:
 * <Merge Intervals>
 *
 * Brute Force Intuition:
 * - Check the end time of a meeting with every start interval.
 * - Increment the number of rooms if there is an overlap.
 * - Repeat the process for each interval.
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
 * Better Approach Intuition:
 * - Sort both the arrays.
 * - For each starting time, count how many meetings have started and not ended yet using binary search.
 *
 * - Why it is still not optimal?
 * - Binary search has to be performed for every start.
 * - Better than brute, still not the simplest.
 *
 * Time Complexity:
 * - O(N log N)
 * Space Complexity:
 * - O(1)
 *
 * Optimal Approach Intuition:
 * - Sort both the arrays.
 * - Use 2-pointers to check if a meeting has ended.
 * - If the meeting is ongoing, increment the number of rooms.
 * - If the meeting has ended, decrement the number of rooms.
 * - Track the maximum number of rooms.
 *
 * Time Complexity:
 * - O(N log N)
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - Why sorting doesn't destroy the natural order ?
 * - Let it be a start of the meeting or end of the meeting, both events happen on a timeline.
 * - Therefore, we don't need to sort them by start time.
 * - We just need to check if the meeting has ended or not before another meeting starts.
 */
package intervals;
import java.util.Arrays;
public class MeetingRoomsII {

    public static void main(String[] args) {
        System.out.println(minMeetingRooms(new int[]{1, 10, 7}, new int[]{4, 15, 10}));
    }

        public static int minMeetingRooms(int[] start, int[] end) {
            Arrays.sort(start);
            Arrays.sort(end);

            int n = start.length, i = 0, j = 0, rooms = 0, maxRooms = 0;

            while(i < n && j < n){

                if(start[i] < end[j]){
                    rooms++;
                    maxRooms = Math.max(maxRooms, rooms);
                    i++;
                }
                else{
                    rooms--;
                    j++;
                }
            }
            return maxRooms;
        }
}

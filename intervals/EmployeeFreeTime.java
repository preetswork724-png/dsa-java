/**
 * Problem:
 * <Employee Free Time>
 *
 * Link:
 * <https://algo.monster/liteproblems/759>
 *
 * Pattern:
 * <Merge Intervals>
 *
 * Brute Force Intuition:
 * - Figure out the timeline.
 * - Take a boolean array and mark as true on the indices between start and end as the employees are busy at that time.
 * - Track the continuous free chunks of time as they represent the common free time for the employees.
 *
 * - Why it is inefficient?
 * - Not scalable if time values are large.
 * - Heavy memory.
 * - Inefficient.
 *
 * Time Complexity:
 * - O(N + R)
 * Space Complexity:
 * - O(R)
 *
 * Better Approach Intuition:
 * - Put the first interval of each employee into a min-heap sorted by start time.
 * - Pop the earliest interval.
 * - Maintain a prevEnd for merged timeline.
 * - If next interval starts > prevEnd - we found a free time.
 * - Push same interval from same employee into heap.
 * - Repeat.
 *
 * - Why it is still not optimal?
 * - A simpler approach can be used.
 *
 * Time Complexity:
 * - O(N log K)
 * Space Complexity:
 * - O(K)
 *
 * Optimal Approach Intuition:
 * - Add the schedules represented by different employees altogether.
 * - Sort by the start time.
 * - Merge all the overlapping intervals as the employees are busy at that time.
 * - Maintain a prevEnd.
 * - If the prevEnd is lesser than the start of the next interval, then there is a gap between them where the employees are free.
 *
 * Time Complexity:
 * - O(N log K)
 * Space Complexity:
 * - O(K)
 *
 * Notes:
 * - Merging all the intervals is logical as the employees are busy together at that time.
 *
 * Mistake log :
 * - Again assumed that merging two intervals will solve the problem.
 * - But instead, it is a timeline problem.
 * - Where each interval affects all the present intervals and not just the adjacent intervals.s
 */
package intervals;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
class Interval{
    int start, end;

    Interval(){
        start = 0;
        end = 0;
    }

    Interval(int s, int e){
        start = s;
        end = e;
    }
}
public class EmployeeFreeTime {

    public List<Interval> freeTime(List<List<Interval>> schedule){
        List<Interval> res = new ArrayList<>();
        List<Interval> intervals = new ArrayList<>();

        for(List<Interval> employee : schedule){
            for(Interval interval : employee){
                intervals.add(interval);
            }
        }

        Collections.sort(intervals, (a, b) -> a.start - b.start);
        int prevEnd = intervals.get(0).end;

        for(Interval interval : intervals){
            if(interval.start > prevEnd){
                res.add(new Interval(prevEnd,  interval.start));
            }
            prevEnd = Math.max(prevEnd, interval.end);
        }

        return res;
    }
}

/**
 * Problem:
 * <Maximum CPU Load from the given list of jobs>
 *
 * Link:
 * <https://www.geeksforgeeks.org/dsa/maximum-cpu-load-from-the-given-list-of-jobs/>
 *
 * Pattern:
 * <Merge Intervals>
 *
 * Brute Force Intuition:
 * - For every relevant time points.
 * - Check all jobs.
 * - Sum active loads.
 * - Track maximum.
 *
 * - Why it is inefficient?
 * - We try ~2n time points.
 * - For each time point, we check all the jobs.
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(1)
 *
 * Better Approach Intuition:
 * - Split each array into two parts :
 * - start[] -> {startTime, load} and end[] -> {endTime, load}.
 * - Sort both the arrays.
 * - Use 2-pointer approach and check one by one, for overlapping intervals or not.
 * - Track the maximum load.
 *
 * - Why it is still not optimal?
 * - It is less preferred as at a time point, we assume only one end candidate.
 *
 * Time Complexity:
 * - O(N log N)
 * Space Complexity:
 * - O(N)
 *
 * Optimal Approach Intuition:
 * - Sort the array by their start time.
 * - Maintain a minHeap which stores the arrays by their end times in increasing order.
 * - So that the job with the earliest end time is removed before adding a new task.
 * - Subtract the current load when the job is removed.
 * - Sum the current load when job is added.
 * - Track the maximum load.
 *
 * Time Complexity:
 * - O(N log N)
 * Space Complexity:
 * - O(N)
 *
 * Notes:
 * - Why 2-pointer approach is less preferred than the minHeap approach even when they have the same asymptotic complexities ?
 * - 2-pointer approach destroys the original job identity.
 * - How starts / end arrays correspond.
 * - Order of equal time stamps.
 * - Making sure while-loop correctness holds.
 * - Ensuring i and j don't break.
 * - Edge cases like start == end.
 * - Zero duration tasks.
 * - Anything that looks tricky is assumed as risky.
 *
 * - Heap is more general / universally correct.
 * - It reads like english :
 * - "There are multiple active jobs, always remove the earliest finishing one."
 * - Remove finished job.
 * - Add current job.
 * - Update load.
 * - It works even when :
 * - Overlaps get complicated.
 * - End times are very irregular.
 * - Future variations of the problem appear.
 * - Anything that is obviously correct is loved.
 */
package intervals;
import java.util.Arrays;
import java.util.PriorityQueue;

public class MaximumCPULoadFromTheGivenListOfJobs {

    public static void main(String[] args) {
        System.out.println(maxLoadCPU(new int[][]{{6, 7, 10}, {2, 4, 11}, {8, 12, 15}}));
    }

    public static int maxLoadCPU(int[][] jobs){

        Arrays.sort(jobs, (a,b) -> Integer.compare(a[0], b[0]));

        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a,b) -> Integer.compare(a[1], b[1]));
        int currLoad = 0, maxLoad = 0;

        for(int[] job : jobs){
            int start = job[0], end = job[1], load = job[2];

            while(!minHeap.isEmpty() && minHeap.peek()[1] <= start){
                currLoad -= minHeap.peek()[2];
                minHeap.poll();
            }

            minHeap.offer(job);
            currLoad += load;

            maxLoad = Math.max(maxLoad, currLoad);
        }

        return maxLoad;
    }
}

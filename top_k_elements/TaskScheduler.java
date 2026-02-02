/**
 * Problem:
 * <Task Scheduler>
 *
 * Link:
 * <https://leetcode.com/problems/task-scheduler/description/>
 *
 * Pattern:
 * <Max Heap>
 *
 * Brute Force Intuition:
 * - Scan all the 26 tasks.
 * - Find the most frequent task not in cooldown.
 * - If no remaining -> idle.
 * - Decrease frequency.
 * - Set cooldown timer.
 *
 * - Why it is inefficient?
 * - Because we check every task every second until no task is remaining which is a lot of repeated work.
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(1)
 *
 * Better Approach intuition:
 * - Max Heap + map.
 * - Use a map to compute frequencies of each task.
 * - Order the priority queue in decreasing order of the frequency of the tasks.
 * - Now, after completing each task, we have to wait for n intervals to complete the same labelled task again.
 * - Therefore, poll() out the top n+1 tasks.
 * - Complete them and decrement each task's frequency.
 * - Now, only add the tasks whose frequency is greater than 0 back to the heap.
 * - In each iteration, if your heap becomes empty, then total number of tasks completed is the number of tasks you polled() out of the heap.
 * - Else, n+1 tasks were completed.
 *
 * Time Complexity:
 * - O(N log N)
 * Space Complexity:
 * - O(N)
 *
 * Why this is still not optimal?
 * - The above solution is good, but it's a simulation.
 *
 * Optimal Approach (Used below):
 * - What causes idles?
 * - The most frequent task.
 * - Therefore, the most frequent tasks define the skeleton of the structure.
 * - After placing most frequent tasks, each at a distance of n from another, we have maxFreq - 1 blocks.
 * - Size of 1 block is n + 1, including the most frequent element.
 * - Also, the characters with the same freq as maxFreq, will occupy the last positions of the last cycle.
 * - So total intervals = (maxFreq - 1) * (n + 1) + countMax
 * - What if many tasks exists?
 * - So, we don't need idles.
 * - Formula might give smaller answer.
 * - Therefore, we must take max(ans, tasks.length).
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - When the question says:
 * - Same item needs gap, minimize time.
 * - Most frequent element dictates the layout.
 */

package top_k_elements;

public class TaskScheduler {

    public static void main(String[] args) {
        System.out.println(leastInterval(new char[]{'A', 'A', 'A', 'B', 'B', 'B'}, 2));
    }

    public static int leastInterval(char[] tasks, int n) {
        int[] freq = new int[26];

        for(char ch : tasks){
            freq[ch - 'A']++;
        }

        int maxFreq = 0;

        for(int i : freq){
            maxFreq = Math.max(maxFreq, i);
        }

        int countMax = 0;

        for(int i : freq){

            if(i == maxFreq){
                countMax++;
            }
        }

        int ans = (maxFreq - 1) * (n + 1) + countMax;
        return Math.max(ans, tasks.length);
    }
}

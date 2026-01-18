/**
 * Problem:
 * <Daily Temperatures>
 *
 * Link:
 * <https://leetcode.com/problems/daily-temperatures/description/>
 *
 * Pattern:
 * <Stack>
 *
 * Brute Force Intuition:
 * - For each element, we repeatedly scan the remaining array.
 * - Find an index where the temperature is greater than the current temperature.
 * - The difference of the indexes is the number of days you need to wait for the next warmer day.
 *
 * - Why it is inefficient?
 * - Redundantly finding the next greater temperature for each element.
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(1)
 *
 * Better and Optimal Approach ( Used Below ):
 * - Maintain a monotonically decreasing stack to store the temperatures.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Why the better approach is the optimal one?
 * - We must process every element once.
 * - Monotonic decreasing stack lets us find the next greater element for all the elements in O(N).
 *
 * Notes:
 * - If the stack is not empty, then there exists the elements which don't have a next greater element.
 * - ArrayDeque works faster than stack.
 */
package stack;
import java.util.Arrays;
import java.util.Deque;
import java.util.ArrayDeque;
public class DailyTemperatures {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(dailyTemperatures(new int[]{73,74,75,71,69,72,76,73})));
    }

    public static int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;

        int[] timeForWarmDays = new int[n];

        Deque<Integer> dq = new ArrayDeque<>();

        for(int i = 0; i < n; i++){

            int currTemp = temperatures[i];

            while(!dq.isEmpty() && currTemp > temperatures[dq.peek()]){
                timeForWarmDays[dq.peek()] = i - dq.peek();
                dq.pop();
            }

            dq.push(i);
        }
        return timeForWarmDays;
    }
}

/**
 * Problem:
 * <Stock span problem>
 *
 * Link:
 * <https://www.geeksforgeeks.org/problems/stock-span-problem-1587115621/1>
 *
 * Pattern:
 * <Monotonic Stack>
 *
 * Brute Force Intuition:
 * - For every element in the array :
 * - Find the index of the previous greater element to it such as j < i and nums[j] > nums[i].
 * - If the stack becomes empty for a specific element, then the span for that element is its current index + 1.
 * - Else, the span for that element is its current index - index of the last greater price of the stock.
 *
 * - Why it is inefficient?
 * - For every day, we need to search for its previous greater price which takes O(N) time.
 * - Slower for large arrays.
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(1)
 *
 * Better Approach:
 * - Instead of scanning the entire again and again to find previous greater price, we:
 * - Maintain a monotonic decreasing stack.
 * - Precompute the day when the price is greater than te current day:
 * - Basically, a pge[].
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Why this is not optimal?
 * - Multiple passes.
 * - Uses an extra array to compute previous day with greater stock price.
 *
 * Optimal Approach (used below):
 * - One stack, one pass.
 * - Instead of computing a separate pge[], compute it on the fly.
 *
 * Notes:
 * - For days when you cannot find a day with previously with greater price then the span of that day should be 1.
 */

package stack;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Collections;

public class StockSpanProblem {

    public static void main(String[] args) {
        System.out.println(calculateSpan(new int[]{100, 80, 90, 120}));
    }

    public static ArrayList<Integer> calculateSpan(int[] arr) {
        int n = arr.length;
        ArrayList<Integer> span = new ArrayList<>(Collections.nCopies(n, 1));
        Deque<Integer> dq = new ArrayDeque<>();

        for(int i = 0; i < n; i++){

            int prevIdx = i;

            while(!dq.isEmpty() && arr[i] >= arr[dq.peek()]){
                prevIdx = dq.pop();
            }

            if(dq.isEmpty()){
                span.set(i, i + 1);
            }
            else{
                span.set(i, i - dq.peek());
            }

            dq.push(i);
        }
        return span;
    }
}

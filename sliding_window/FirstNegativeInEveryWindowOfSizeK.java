/**
 * Problem:
 * <First Negative Integer in Every Window of Size K>
 *
 * Link:
 * <https://www.geeksforgeeks.org/problems/first-negative-integer-in-every-window-of-size-k3345/1>
 *
 * Pattern:
 * <Sliding Window + Queue>
 *
 * Brute Force Intuition:
 * - For every window of size k:
 *     - Traverse all k elements.
 *     - Find the first negative number.
 * - If no negative exists, return 0.
 *
 * - Why it is inefficient?
 * - For every starting index, we scan k elements again.
 * - Repeated work across overlapping windows.
 *
 * Time Complexity:
 * - O(N * K)
 * Space Complexity:
 * - O(1)
 *
 * Better Approach Intuition:
 * - Maintain a list (or queue) of negative elements for each window.
 * - For every window:
 *     - Store all negative elements.
 *     - Pick the first one.
 *
 * - Why it is still not optimal?
 * - You may end up rebuilding or filtering the list repeatedly.
 * - Still not leveraging overlap efficiently.
 *
 * Time Complexity:
 * - O(N * K) in worst case
 * Space Complexity:
 * - O(K)
 *
 * Optimal Approach (Used Below):
 * - Use Sliding Window with a Queue.
 *
 * - Key Idea:
 *   - Store indices of negative elements in a queue.
 *   - The front of the queue always gives the first negative element in the current window.
 *
 * - Steps:
 *   1. Process first window:
 *        - Add indices of negative elements to queue.
 *
 *   2. For each next element:
 *        - Remove indices that are out of the current window (i - k).
 *        - If current element is negative → add its index.
 *        - If queue is empty → answer = 0
 *          else → answer = arr[q.peek()]
 *
 * - Why this works:
 *   - Each element is added and removed at most once.
 *   - We reuse previous window information instead of recomputing.
 *
 * Time Complexity:
 * - O(N)
 *
 * Space Complexity:
 * - O(K)  (queue stores at most k indices)
 *
 * Notes:
 * - Always store indices instead of values in sliding window problems.
 * - Never remove (poll) unless element is out of window.
 * - Peek gives the current answer.
 */

package sliding_window;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FirstNegativeInEveryWindowOfSizeK {
    static List<Integer> firstNegInt(int arr[], int k) {

        int n = arr.length;

        List<Integer> res = new ArrayList<>();

        if (k > n) return res;

        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < k; i++) {
            if (arr[i] < 0) {
                q.offer(i);
            }
        }

        if (q.isEmpty()) res.add(0);
        else res.add(arr[q.peek()]);

        for (int i = k; i < n; i++) {

            while (!q.isEmpty() && q.peek() <= i - k) {
                q.poll();
            }

            if (arr[i] < 0) {
                q.offer(i);
            }

            if (q.isEmpty()) res.add(0);
            else res.add(arr[q.peek()]);
        }
        return res;
    }
}

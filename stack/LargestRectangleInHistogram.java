/**
 * Problem:
 * <Largest Rectangle in Histogram>
 *
 * Link:
 * <https://leetcode.com/problems/largest-rectangle-in-histogram/description/>
 *
 * Pattern:
 * <Monotonic Stack>
 *
 * Brute Force Intuition:
 * - The intuition is that a rectangle is stuck between 2 smaller bars.
 * - For every element in the array :
 * - Find the next smaller element to it such as j > i and nums[j] < nums[i].
 * - Find the previous smaller element to it such as j < i and nums[j] < nums[i].
 * - Compute the total width as:
 * - width = right - left - 1.
 * - Compute the area.
 * - Track maximum area globally.
 *
 * - Why it is inefficient?
 * - For every index, we need to search for its next smaller and previous smaller element which takes O(N) time.
 * - Slower for large arrays.
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(1)
 *
 * Better Approach:
 * - Instead of scanning the entire again and again to find previous and next smaller we:
 * - Precompute the next smaller elements and previous smaller elements for every element of the array:
 * - nse[] and pse[].
 * - right = (nse[i] == -1) ? n : nse[i].
 * - left = (pse[i] == -1) ? -1 : pse[i].
 * - The formula for the width and the rest of the process remains the same.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Why this is not optimal?
 * - Multiple passes.
 * - Uses two extra arrays to compute previous smaller and next smaller elements.
 *
 * Optimal Approach (used below):
 * - One stack, one pass.
 * - A rectangle is stuck between two smaller bars.
 * - We maintain a monotonically increasing stack.
 * - We keep pushing indices where the current Height is greater than the last seen height.
 * - Pop when the last seen height is greater than the current height.
 * - This is how we find the next smaller element.
 * - Now, for each element in the stack:
 * - We have a right boundary which is the current index and the left boundary which is before the index of the height to be popped.
 * - Compute width and area, track maximum area globally.
 *
 * Notes:
 * - right and left boundaries are not inclusive.
 * - To prevent double-counting the width, always subtract one as it is already included in the boundary.
 * - The rectangle is stuck between strictly smaller bars.
 * - Equal bars can merge to form a larger rectangle.
 * - So, popping when > is conceptually cleaner.
 * - The idea that a rectangle must be "stuck between strictly smaller bars" is slightly incomplete.
 * - The correct rule is:
 * - The rectangle extends until we hit a smaller bar or the end of the array.
 * - Even in ascending order:
 * - No popping happens during traversal.
 * - All the bars stay in the stack.
 * - At the end (when we hit sentinel 0), we pop everything and compute areas.
 */

package stack;

import java.util.Deque;
import java.util.ArrayDeque;

public class LargestRectangleInHistogram {

    public static void main(String[] args) {
        System.out.println(largestRectangleArea(new int[]{2,1,5,6,2,3}));
    }

    public static int largestRectangleArea(int[] heights) {
        int n = heights.length;
        Deque<Integer> dq = new ArrayDeque<>();
        int maxArea = 0;

        for (int i = 0; i <= n; i++) {
            int currHeight = (i == n) ? 0 : heights[i];

            while (!dq.isEmpty() && heights[dq.peek()] > currHeight) {
                int rightBoundary = i;
                int height = heights[dq.pop()];
                int leftBoundary = dq.isEmpty() ? -1 : dq.peek();
                int width = rightBoundary - leftBoundary - 1;
                maxArea = Math.max(maxArea, height * width);
            }
            dq.push(i);
        }
        return maxArea;
    }
}

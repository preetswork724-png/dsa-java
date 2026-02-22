/**
 * Problem:
 * <Trapping Rain Water>
 *
 * Link:
 * <https://leetcode.com/problems/trapping-rain-water/description/>
 *
 * Pattern:
 * <Two pointer>
 *
 * Brute Force Intuition:
 * - Water trapping requires both the left boundary and thr right boundary.
 * - And water trapped always depends on the smaller of the two boundaries.
 * - For every index:
 * - Scan left to find maxLeft.
 * - Scan right to find maxRight.
 * - Add water.
 *
 * - Why it is inefficient?
 * - Rescans the entire array repeatedly.
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(1)
 *
 * Better Approach:
 * - Prefix + Suffix Arrays.
 * - Instead of rescanning the entire array every time we:
 * - Precompute the leftMax and the rightMax.
 *
 * - Monotonic Stack.
 * - Maintain a monotonically decreasing stack.
 * - Push when the current bar is lesser than or equal to the previous bar.
 * - Pop otherwise.
 * - Pop out the gap i.e valley.
 * - If stack is empty, then break out of the loop as there is no leftBoundary for the valley.
 * - Now for this valley, we have a leftBoundary and a right boundary which is i.
 * - Width = i - leftBoundary - 1.
 * - Bounded Height = Math.min(height[leftBoundary], height[i]) - height[valley].
 * - Water = Width * Bounded Height.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Why this is not optimal?
 * - Uses extra space.
 *
 * Optimal Approach (used below):
 * - 2-pointer approach.
 * - We place two pointers, one at the left end and other at the right end, and maintain leftMax and rightMax while moving inward.
 * - At every step, we compare height[left] and height[right].
 * - The smaller side is the boundary which limits the trapped water.
 * - If height[left] <= height[right],
 * - water at left only depend on leftMax.
 * - If leftMax > height[left], add leftMax - height[left].
 * - Else update leftMax.
 * - move left.
 * - Otherwise,
 * - water at right depends only on rightMax.
 * - If rightMax > height[right], add rightMax - height[right].
 * - Else, update rightMax,
 * - move right.
 * - This works because the water is limited by only smaller side of the two boundaries.
 * - So, once one side is smaller, the opposite side cannot affect the water level for that index.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - In the brute force, always subtract the contribution of the current bar.
 * - In the better approach, always subtract the contribution of the popped bar.
 */

package two_pointers;

public class TrappingRainWater {
    public static void main(String[] args) {
        System.out.println(trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
    }

    public static int trap(int[] height) {
        int n = height.length, left = 0, right = n-1, leftMax = 0, rightMax = 0, waterTrapped = 0;

        while(left <= right){

            if(height[left] <= height[right]){

                if(height[left] >= leftMax){
                    leftMax = height[left];
                }
                else{
                    waterTrapped += leftMax - height[left];
                }
                left++;
            }
            else{

                if(height[right] >= rightMax){
                    rightMax = height[right];
                }
                else{
                    waterTrapped += rightMax - height[right];
                }
                right--;
            }
        }
        return waterTrapped;
    }
}

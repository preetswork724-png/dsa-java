/**
 * Problem:
 * <Next Greater Element I>
 *
 * Link:
 * <https://leetcode.com/problems/next-greater-element-i/description/>
 *
 * Pattern:
 * <Stack>
 *
 * Brute Force Intuition:
 * - For every element in first array :
 * - Find its index in second array.
 * - And find the next greater element of that number after that index.
 *
 * - Why it is inefficient?
 * - For every index, we need to search for its index which takes O(M) time.
 * - Slower for large arrays.
 *
 * Time Complexity:
 * - O(N * M)
 * Space Complexity:
 * - O(1)
 *
 * Better and Optimal Approach ( Used Below ):
 * - Instead of searching fpr the index again and again, remember it by using HashMap.
 * - Precompute the next greater element for every element in second array which saves time while iterating elements in the array one by one.
 * - Retrieve the index in O(1) and the next greater element to it in O(1) too.
 *
 * Time Complexity:
 * - O(M + N)
 * Space Complexity:
 * - O(M)
 *
 * Why the better approach is the optimal one?
 * - We must process every element once.
 * - Monotonic decreasing stack lets us find the next greater element for all the elements in O(M).
 * - HashMap gives O(1) lookup for nums1 answers.
 *
 * Notes:
 * - If the stack is not empty, then there exists the elements which don't have a next greater element.
 */
package stack;

import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.Stack;

public class NextGreaterElementI {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(nextGreaterElement(new int[]{4,1,2}, new int[]{1,3,4,2})));
    }

    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {

        int n = nums1.length, m = nums2.length;

        // 1. Use HashMap to retrieve the indexes of elements in nums2 in O(1)
        Map<Integer, Integer> eleIdx = new HashMap<>();

        for(int i = 0; i < m ; i++){
            eleIdx.put(nums2[i], i);
        }

        // 2. Find next greater element for each element io nums[2]

        int[] nextGreater = nextGreat(nums2, m);

        int[] ans =  new int[n];

        for(int i = 0; i < n; i++){
            int index = eleIdx.get(nums1[i]);
            ans[i] = nextGreater[index];
        }

        return ans;
    }

    public static int[] nextGreat(int[] nums2, int m){

        Stack<Integer> stack = new Stack<>();
        int[] res = new int[m];

        for(int i = 0; i < m ; i++){

            int element = nums2[i];

            while(!stack.isEmpty() && element > nums2[stack.peek()]){
                res[stack.peek()] = element;
                stack.pop();
            }

            stack.push(i);
        }

        while(!stack.isEmpty()){
            res[stack.peek()] = -1;
            stack.pop();
        }
        return res;
    }
}

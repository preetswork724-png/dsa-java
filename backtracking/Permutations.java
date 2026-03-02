/**
 * Problem:
 * <Permutations>
 *
 * Link:
 * <https://leetcode.com/problems/permutations/description/>
 *
 * Approach 1 (boolean[] method):
 * - This is a backtracking / recursive generation problem
 * - For every position:
 * - We try all the elements.
 * - Skip the ones already taken in the poth.
 *
 * Template:
 * void helper(int[] nums, boolean[] used, List<Integer> path) {
 *
 *     if (path.size() == nums.length) {
 *         add to result
 *         return;
 *     }
 *
 *     for (int i = 0; i < nums.length; i++) {
 *
 *         if (used[i]) continue;
 *
 *         used[i] = true;
 *         path.add(nums[i]);
 *
 *         helper(nums, used, path);
 *
 *         path.remove(path.size() - 1);
 *         used[i] = false;
 *     }
 * }
 *
 * Time Complexity:
 * - O(N * N!) {Permutation generation takes O(N!) and copying takes O(N) time}
 *
 * Space Complexity:
 * - O(N * N!) {Output storage} + O(N) {Recursion Stack} + O(N) {boolean[]}
 *
 * Approach 2 (boolean[] method):
 * - Instead of tracking used element:
 * - Fix one position at a time.
 * - Swap with every possible indices.
 * - Recurse.
 * - backtrack.
 * - For every index:
 * - We decide what goes on that index.
 * - Everything before index is already fixed.
 *
 * Time Complexity:
 * - O(N * N!) {Permutation generation takes O(N!) and copying takes O(N) time}
 *
 * Space Complexity:
 * - O(N * N!) {Output storage} + O(N) {Recursion Stack}
 */

package backtracking;
import java.util.List;
import java.util.ArrayList;
public class Permutations {

    public static void main(String[] args) {
        System.out.println(permute(new int[]{1,2,3}));
    }

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> permutations = new ArrayList<>();
        int n = nums.length;
        helper(0, n, nums, permutations);
        return permutations;
    }

    public static void helper(int index, int n, int[] nums, List<List<Integer>> permutations){

        if(index == n){
            List<Integer> temp = new ArrayList<>();
            for(int i : nums) temp.add(i);
            permutations.add(new ArrayList<>(temp));
            return;
        }

        for(int i = index; i < n; i++){
            swap(index, i, nums);
            helper(index + 1, n, nums, permutations);
            swap(index, i, nums);
        }
        // TC :- O(N * N!)
        // SC :- O(N * N!) {Output Space} + O(N) {Recursion Stack}
    }

    public static void swap(int index1, int index2, int[] nums){
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }
}

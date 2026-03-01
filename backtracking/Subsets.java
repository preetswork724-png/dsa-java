/**
 * Problem:
 * <Subsets>
 *
 * Link:
 * <https://leetcode.com/problems/subsets/description/>
 *
 * Approach:
 * - This is a backtracking / recursive generation problem
 * - For each element in the array, we have 2 choices:
 * - Pick arr[i].
 * - Skip arr[i].
 * - By picking, we explore the left subtree first.
 * - Backtrack.
 * - Then, explore the right subtree.
 *
 * Time Complexities:
 * - O(N * 2^N) {Subset generation takes O(2^N) and copying takes O(N) time}
 *
 * Space Complexity:
 * - O(N) {Recursion depth} + O(N * 2^N) {Output storage :- Total subsets = 2^N, each subset can have up to N elements}
 */

package backtracking;
import java.util.List;
import java.util.ArrayList;
public class Subsets {

    public static void main(String[] args) {
        System.out.println(subsets(new int[]{1,2,3}));
    }

    public static List<List<Integer>> subsets(int[] nums) {
        int n = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        helper(0, n, nums, new ArrayList<>(), res);
        return res;
    }

    public static void helper(int index, int n, int[] nums, List<Integer> partialAns,
                       List<List<Integer>> res){

        if(index == n){
            res.add(new ArrayList<>(partialAns));
            return;
        }

        partialAns.add(nums[index]);
        helper(index + 1, n, nums, partialAns, res);
        partialAns.remove(partialAns.size() - 1);
        helper(index + 1, n, nums, partialAns, res);
    }
}

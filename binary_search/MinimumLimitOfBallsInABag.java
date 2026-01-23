/**
 * Problem:
 * <Minimum Limit of Balls in a Bag>
 *
 * Link:
 * <https://leetcode.com/problems/minimum-limit-of-balls-in-a-bag/description/>
 *
 * Pattern:
 * <Binary Search on answer>
 *
 * Brute Force Intuition:
 * - We must try all possible sequences of splits up to maxOperations and track the minimum possible maximum bag size.
 * - DFS(state of bags, opsLeft):
 * - if opsLeft == 0:
 * - update answer = min(answer, max(bags)) and return
 * - for each bag i in bags:
 * - for each possible split (a, b) of bags[i]:
 * - newState = replace bags[i] with a and b.
 * - DFS(newState, opsLeft - 1)
 * - The above algorithm does not assume equal splits.
 * - It tries all (x - 1) ways to split a bag of size x.
 * - It explores all operation orders.
 *
 * - Why it is inefficient?
 * - It is impossible as the time complexity is super exponential.
 *
 * Time Complexity:
 * - O(branching ^ maxOperations)
 * Space Complexity:
 * - O(n + maxOperations)
 *
 * Better and Optimal Approach (Used Below):
 * - Range for penalty is from 1 to max(nums).
 * - As the range is monotonic, we apply binary search on the range.
 * - For a candidate penalty:
 * - For a bag of size m, we need to make every piece <= penalty.
 * - If we are able to divide the bags in such a way that each bag has lesser than or equal balls than penalty under maxOperations.
 * - Then, we can say that for that penalty we were able to split the bags.
 * - Minimize the penalty.
 *
 * Time Complexity:
 * - O(N log M) {where M = max(nums)}
 * Space Complexity:
 * - O(1)
 *
 * Why better approach is the optimal one?
 * - A better approach usually reduces the search space somehow but not optimally.
 * - Uses partial structure like sorting, prefix sums, hashing, etc.
 * - But in this problem, the search space is a contiguous integer range.
 * - The decision function canSplit(penalty) is strictly monotonic.
 * - The only principled way to reduce a monotonic integer range is binary search.
 *
 * Notes:
 * - number of pieces = ceil(num / penalty).
 * - Number of splits (operations) = pieces - 1.
 */

package binary_search;

public class MinimumLimitOfBallsInABag {

    public static void main(String[] args) {
        System.out.println(minimumSize(new int[]{9}, 2));
    }

    public static int minimumSize(int[] nums, int maxOperations) {
        int low = 1, high = max(nums), ans = 0;

        while(low <= high){

            int mid = low + (high - low) / 2;

            if(canSplit(nums, maxOperations, mid)){
                ans = mid;
                high = mid - 1;
            }
            else{
                low = mid + 1;
            }
        }
        return ans;
    }

    public static boolean canSplit(int[] nums, int maxOperations, int penalty){
        int ops = 0;

        for(int num : nums){
            ops += (num - 1) / penalty;
            if(ops > maxOperations) return false;
        }

        return true;
    }

    public static int max(int[] nums){
        int max = Integer.MIN_VALUE;

        for(int num : nums) max = Math.max(num, max);
        return max;
    }
}

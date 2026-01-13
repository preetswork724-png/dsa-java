/**
 * Problem:
 * <Circular Array Loop>
 *
 * Link:
 * <https://leetcode.com/problems/circular-array-loop/description/>
 *
 * Pattern:
 * <Fast & Slow Pointer>
 *
 * Brute Force Intuition:
 * - From every index, simulate the movement and see if we come back to the same index.
 * - For each index i:
 * - Start from i.
 * - Maintain a visited set local to this start.
 * - Keep moving according to the rules:
 * - Stop if:
 * - You revisit a node - cycle starts.
 * - Direction changes - invalid.
 * - You hit a self loop - invalid.
 *
 * - Why it is inefficient?
 * - From each index, you may walk O(n) steps.
 * - Doing this for N indices.
 * - You repeatedly traverse the same paths again and again.
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(N)
 *
 * Better Approach Intuition:
 * - If a path starting from an index i is invalid, don't start from it again.
 * - Maintain a global visited array.
 * - Once a path is invalid, mark all the nodes in that path as processed.
 * - For every index i:
 * - If already globally visited, skip.
 * - Same simulation as brute.
 * - If no valid cycle, mark all the nodes in the path as globally visited.
 *
 * - Why it is still not optimal?
 * - Uses extra memory.
 * - We are detecting cycles by using extra storage, not structure.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Optimal Approach (Used Below):
 * - Each index points exactly to one next index.
 * - This is a Linked List in disguise.
 * - And as we want to detect cycles, Floyd's Cycle Detection can be used.
 * - Fix a direction.
 * - Run fast and slow pointers.
 * - At every move:
 * - Direction must be consistent.
 * - No self loops are allowed.
 * - If fast meets slow, valid cycle.
 * - After finishing from one index, mark all the nodes as dead path.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Notes:
 * - All the nodes in the path should be processed as dead nodes but the direction must be the same.
 */
package linkedlist;

public class CircularArrayLoop {
    public boolean circularArrayLoop(int[] nums) {
        int n = nums.length;
        boolean[] visited = new boolean[n];

        for(int i = 0; i < n; i++){

            if(visited[i]) continue;

            boolean isForward = (nums[i] > 0);
            int slow = i, fast = i;

            while(true){

                slow = next(nums, slow, n, isForward);
                if(slow == -1) break;

                fast = next(nums, fast, n, isForward);
                if(fast == -1) break;

                fast = next(nums, fast, n, isForward);
                if(fast == -1) break;

                if(slow == fast) return true;
            }

            int curr = i;

            while(nums[curr] != 0 && (nums[curr] > 0 == isForward)){
                int next = ((curr + nums[curr]) % n + n) % n;
                nums[curr] = 0;
                curr = next;
            }
        }
        return false;
    }

    public int next(int[] nums, int curr, int n, boolean isForward){
        if((nums[curr] > 0) != isForward) return -1;

        int next = ((curr + nums[curr]) % n + n) % n;
        if(curr == next) return -1;

        return next;
    }
}

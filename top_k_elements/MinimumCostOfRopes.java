/**
 * Problem:
 * <Minimum Cost of ropes>
 *
 * Link:
 * <https://www.geeksforgeeks.org/problems/minimum-cost-of-ropes-1587115620/1>
 *
 * Pattern:
 * <Min Heap>
 *
 * Brute Force Intuition:
 * - Sort the array.
 * - Store the elements of the array in a mutable form of the list.
 * - Run a loop until only one rope ie left in the list.
 * - In each iteration, we need to sort the list.
 * - Sum up the cost of two ropes with minimum cost.
 * - Remove the ropes from the list.
 * - And add their sum back to the list.
 *
 * - Why it is inefficient?
 * - We repeatedly sort the list to ensure that at every step only the ropes with minimal cost gets added.
 * - Massive repeated work.
 *
 * Time Complexity:
 * - O(N^2 log N)
 * Space Complexity:
 * - O(N)
 *
 * Better Approach Intuition:
 * - Instead of sorting the lists every time:
 * - Sort the array once.
 * - Store it in a list.
 * - Each iteration:
 * - Remove the first two ropes.
 * - Add their sum at the correct position in the list.
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(N)
 *
 * - Why this is still not optimal?
 * - Insertions are linear.
 * - We are doing what a heap already does.
 * - We pay O(N) per merge.
 *
 *
 * Optimal Approach (Used below):
 * - Use the above approach but using a min heap.
 *
 * Time Complexity:
 * - O(N log N)
 * Space Complexity:
 * - O(N)
 *
 * Notes:
 * - Stop the iteration when there is one or no ropes left.
 */

package top_k_elements;
import java.util.PriorityQueue;
public class MinimumCostOfRopes {

    public static void main(String[] args) {
        System.out.println(minCost(new int[]{4,2,7,6,9}));
    }

    public static int minCost(int[] arr) {
        if(arr.length == 1) return 0;

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for(int num : arr) minHeap.add(num);

        int totalCost = 0;

        while(minHeap.size() > 1){

            int costRope1 = minHeap.poll(), costRope2 = minHeap.poll();

            int cost = costRope1 + costRope2;

            totalCost += cost;

            minHeap.add(cost);
        }

        return totalCost;
    }
}

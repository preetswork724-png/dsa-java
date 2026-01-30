/**
 * Problem:
 * <Top 'k' numbers>
 *
 * Link:
 * <https://github.com/dipjul/Grokking-the-Coding-Interview-Patterns-for-Coding-Questions/blob/master/13.-pattern-top-k-elements/02.top-k-numbers.md>
 *
 * Pattern:
 * <Min Heap>
 *
 * Brute Force Intuition:
 * - Sort the array.
 * - Iterate from n-k to n-1 and pickup the top K numbers.
 *
 * - Why it is inefficient?
 * - We sort the entire array when we need only k numbers.
 *
 * Time Complexity:
 * - O(N log N)
 * Space Complexity:
 * - O(1)
 *
 * Better Approach Intuition:
 * - Maintain a maxHeap.
 * - Store all the elements of the array in a maxHeap.
 * - Extract the top K using poll().
 *
 * Time Complexity:
 * - O(N + K log N)
 * Space Complexity:
 * - O(N)
 *
 * - Why this is still not optimal?
 * - We end up storing N elements in the heap.
 * - The above solution is better, but it is memory heavy.
 *
 * Optimal Approach (Used below):
 * - Maintain a minHeap of size k.
 * - As soon as the size of the minHeap becomes greater than k.
 * - Remove the smallest one.
 * - This ensures that after the traversal of the complete array, only the K largest elements remain in the heap.
 *
 * Time Complexity:
 * - O(K log K)
 * Space Complexity:
 * - O(K)
 *
 * Notes:
 * - log K is far better than log N.
 * - If K < N, always sue a minHeap of size k to keep track of all the largest numbers efficiently.
 * - Heap does not guarantee sorted order. it guarantees the correct elements.
 * - If sorted output is required, sort the final k elements.
 */

package top_k_elements;
import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;
public class TopKNumbers {

    public static void main(String[] args) {
        System.out.println(findKLargestNumbers(new int[]{3,1,5,12,2,11}, 3));
    }

    public static List<Integer> findKLargestNumbers(int[] nums, int k){

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for(int num : nums){
            minHeap.add(num);
            if(minHeap.size() > k){
                minHeap.poll();
            }
        }

        return new ArrayList<>(minHeap);
    }
}

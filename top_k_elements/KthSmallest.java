/**
 * Problem:
 * <Kth Smallest>
 *
 * Link:
 * <https://www.geeksforgeeks.org/problems/kth-smallest-element5635/1>
 *
 * Pattern:
 * <Max Heap>
 *
 * Brute Force Intuition:
 * - Sort the array.
 * - Return the Kth element from the start.
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
 * - Maintain a minHeap.
 * - Store all the elements of the array in a minHeap.
 * - poll() the top K-1.
 * - Return the kth element.
 *
 * Time Complexity:
 * - O(N log N)
 * Space Complexity:
 * - O(N)
 *
 * - Why this is still not optimal?
 * - We end up storing N elements in the heap.
 * - The above solution is better, but it is memory heavy.
 *
 * Optimal Approach (Used below):
 * - Maintain a maxHeap of size k.
 * - As soon as the size of the maxHeap becomes greater than k.
 * - poll() the largest element in the heap.
 * - This ensures that after the traversal of the complete array, only the K smallest elements remain in the heap.
 *
 * Time Complexity:
 * - O(N log K)
 * Space Complexity:
 * - O(K)
 *
 * Notes:
 * - log K is far better than log N.
 * - If K < N, always use a maxHeap of size k to keep track of all the k smallest numbers efficiently.
 */

package top_k_elements;

import java.util.PriorityQueue;
import java.util.Collections;
public class KthSmallest {

    public static void main(String[] args) {
        System.out.println(kthSmallest(new int[]{10, 5, 4, 3, 48, 6, 2, 33, 53, 10}, 4));
    }

    public static int kthSmallest(int[] arr, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        for(int num : arr){
            if(maxHeap.size() < k){
                maxHeap.add(num);
            }else if(num < maxHeap.peek()){
                maxHeap.poll();
                maxHeap.add(num);
            }
        }
        return maxHeap.peek();
    }
}

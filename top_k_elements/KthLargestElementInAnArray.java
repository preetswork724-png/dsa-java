/**
 * Problem:
 * <Kth Largest Element in an Array>
 *
 * Link:
 * <https://leetcode.com/problems/kth-largest-element-in-an-array/description/>
 *
 * Pattern:
 * <Min Heap>
 *
 * Brute Force Intuition:
 * - Sort the array.
 * - Return the n-k element.
 *
 * - Why it is inefficient?
 * - We sort the entire array when we only need the kth largest number.
 * - Violates the constraint of the problem.
 *
 * Time Complexity:
 * - O(N log N)
 * Space Complexity:
 * - O(log N) {Java implementation dependent}
 *
 * Better Approach Intuition:
 * - Maintain a maxHeap.
 * - Store all the elements of the array in a maxHeap.
 * - poll() k-1 elements.
 * - poll() and return the kth element.
 *
 * Time Complexity:
 * - O(N log N) {Insert N elements one by one, heapify takes O(N)}
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
 * - poll() the smallest one.
 * - This ensures that after the traversal of the complete array, only the K largest elements remain in the heap.
 * - poll() the kth element.
 *
 * Time Complexity:
 * - O(N log K) {N insertions, Heap size is capped at k, Each operation costs log K}
 * Space Complexity:
 * - O(K)
 *
 * Notes:
 * - log K is far better than log N.
 * - If K < N, always use a minHeap of size k to keep track of all the largest numbers efficiently.
 * - Java's Priority Queue is relatively slow:
 * - Object boxing/unboxing.
 * - Comparator overhead.
 * - Bounds Check.
 * - The heap solution is perfect in terms of interview.
 * - If allowed to mutate input, QuickSelect gives average O(N).
 */

package top_k_elements;

import java.util.PriorityQueue;

public class KthLargestElementInAnArray {

    public static void main(String[] args) {
        System.out.println(findKthLargest(new int[]{3,2,1,5,6,4}, 2));
    }

    public static int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int num : nums) {

            if (minHeap.size() < k) {
                minHeap.add(num);
            } else if (num > minHeap.peek()) {
                minHeap.poll();
                minHeap.add(num);
            }
        }

        return minHeap.poll();
    }
}

/**
 * Problem:
 * <Kth Largest Element in a Stream>
 *
 * Link:
 * <https://leetcode.com/problems/kth-largest-element-in-a-stream/description/>
 *
 * Pattern:
 * <Min Heap>
 *
 * Brute Force Intuition:
 * - Maintain a List<Integer> and add elements of the given array to initialize the list.
 * - Sort the list.
 * - Add an element in the add().
 * - Sort the list after adding element to the list.
 * - Return the n-kth element.
 *
 * - Why it is inefficient?
 * - List is sorted after every step.
 *
 * Time Complexity:
 * - O(N^2 log N)
 * Space Complexity:
 * - O(N)
 *
 * Better Approach Intuition:
 * - Instead of sorting the list everytime:
 * - Just sort the list once, when it is initialized with the elements of the array.
 * - While adding a new element, search for its index using binary search.
 * - Add the new element at that index.
 * - Return the n-kth element.
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(N)
 *
 * - Why this is still not optimal?
 * - We maintain more data than needed, only the top k elements are relevant.
 *
 * Optimal Approach (Used below):
 * - Use min heap of size k.
 * - Cap the size of the min heap at k.
 * - When the size of the min heap exceeds k, poll() an element.
 * - Return the kth largest element as it will be maintained at the root of the min heap.
 *
 * Time Complexity:
 * - O(N log K)
 * Space Complexity:
 * - O(k)
 *
 * Notes:
 * - We maintain a min heap of size k.
 * - The root of the heap always represents the kth largest number in the stream.
 */

package top_k_elements;
import java.util.PriorityQueue;

class KthLargest {

    private PriorityQueue<Integer> minHeap;
    private int k;

    public KthLargest(int k, int[] nums) {
        minHeap = new PriorityQueue<>();
        this.k = k;
        for (int num : nums) {
            minHeap.add(num);

            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
    }

    public int add(int val) {
        minHeap.add(val);
        if(minHeap.size() > k){
            minHeap.poll();
        }
        return minHeap.peek();
    }
}

public class KthLargestElementInAStream {
    public static void main(String[] args) {
        KthLargest obj = new KthLargest(3, new int[]{4,5,8,2});
    }
}

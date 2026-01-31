/**
 * Problem:
 * <Sum of elements between k1'th and k2'th smallest elements>
 *
 * Link:
 * <https://www.geeksforgeeks.org/problems/sum-of-elements-between-k1th-and-k2th-smallest-elements3133/1>
 *
 * Pattern:
 * <Max Heap>
 *
 * Brute Force Intuition:
 * - Sort the array.
 * - Find the indices of the k1th smallest and the k2th smallest element in the array.
 * - Iterate in this range in the sorted array.
 * - Sum up the elements and return them.
 *
 * - Why it is inefficient?
 * - Sorts the entire array when we only want the k2 smallest elements at max.
 *
 * Time Complexity:
 * - O(N log N)
 * Space Complexity:
 * - O(1)
 *
 * Better Approach intuition:
 * - Maintain a minHeap.
 * - Insert all the elements in the minHeap.
 * - poll() the first k-1() elements.
 * - poll() the elements between k2 and k1 and sum them.
 *
 * Time Complexity:
 * - O(N log N)
 * Space Complexity:
 * - O(N)
 *
 * Why this is still not optimal?
 * - Does O(N) insertions while we only need k2 elements at max.
 *
 * Optimal Approach (Used below):
 * - Maintain a maxHeap of size k.
 * - poll() the k2th smallest element.
 * - poll() k2-k1 elements from the heap and sum them.
 *
 * Time Complexity:
 * - O(N log K)
 * Space Complexity:
 * - O(K)
 *
 * Notes:
 * - Remember to exclude the k1th smallest and the k2th smallest element from the sum.
 */

package top_k_elements;

import java.util.Collections;
import java.util.PriorityQueue;

public class SumOfElementsBetweenk1thAndk2thSmallestElements {
    public static void main(String[] args) {
        System.out.println(sumBetweenTwoKth(new long[]{20, 8, 22, 4, 12, 10, 14}, 7, 3, 6));;
    }

    public static long sumBetweenTwoKth(long A[], long N, long K1, long K2) {
        long sum = 0;
        PriorityQueue<Long> maxHeap  = new PriorityQueue<>(Collections.reverseOrder());

        for(long num : A){

            maxHeap.add(num);

            if(maxHeap.size() > K2){
                maxHeap.poll();
            }
        }

        maxHeap.poll();

        for(int i = 1; i < K2-K1; i++){
            sum += maxHeap.poll();
        }

        return sum;
    }
}

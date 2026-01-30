/**
 * Problem:
 * <K Closest Points to Origin>
 *
 * Link:
 * <https://leetcode.com/problems/k-closest-points-to-origin/description/>
 *
 * Pattern:
 * <Max Heap + Comparator>
 *
 * Brute Force Intuition:
 * - Create a custom Pair class.
 * - The Pair class has attributes like int[] points and int distance.
 * - Now, iterate over the given input, compute distance for each array in points[][].
 * - Create a List<Pair>, store the points[] and distance together.
 * - Sort the list based on the distance attribute.
 * - Fetch the k closest points[] from the start.
 *
 * - Why it is inefficient?
 * - We sort the entire points[][] when we only need the k closest points.
 *
 * Time Complexity:
 * - O(N log N)
 * Space Complexity:
 * - O(N)
 *
 * Better Approach Intuition:
 * - Maintain a maxHeap.
 * - Pass a custom comparator inside the constructor indicating that all the arrays that will be added to the heap will be sorted according to the farthest distance.
 * - Store all the n points in the heap.
 * - poll() the n-k farthest distances.
 *
 * Time Complexity:
 * - O(N log N)
 * Space Complexity:
 * - O(N)
 *
 * - Why this is still not optimal?
 * - We end up storing all the N points[] in the heap.
 * - The above solution is better, but it is memory heavy.
 *
 * - Why is it still better than brute when the complexities are the same?
 * - Heap is algorithmically better than sorting, not in terms of final Big-O.
 * - Sorting:
 * - Does a global operation, it needs all the data before it can result.
 * - Sorting cannot work when data comes one by one or when you can't store everything at once.
 * - Heaps:
 * - heaps process data incrementally.
 * - Can stop early.
 * - They work in both the cases when data comes one by one or when you can't store everything at once.
 *
 * Optimal Approach (Used below):
 * - Maintain a maxHeap of size k.
 * - Add when the size of the maxHeap < k.
 * - poll() when size >= k and when the currDist < farthestDist.
 *
 * Time Complexity:
 * - O(N log K)
 * Space Complexity:
 * - O(K)
 *
 * Notes:
 * - If comparison is not directly on an element value, you must define custom comparator logic.
 * - For example:
 * - Points -> distance.
 * - Characters -> Frequency
 * - Words -> frequency + lexicographic.
 * - Objects -> score/weights/priority.
 * - We don't have to store numbers, we have to store derived keys.
 * - Never think custom class first in java.
 * - First think, can I compute the key inside a comparator.
 */

package top_k_elements;

import java.util.Arrays;
import java.util.PriorityQueue;

public class KClosestPointsToOrigin {

    public static void main(String[] args) {
        int[][] points = {{1,3}, {-2,2}};
        System.out.println(Arrays.deepToString(kClosest(points, 1)));
    }

    public static int[][] kClosest(int[][] points, int k) {
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(
                (a, b) -> (b[0]*b[0] + b[1]*b[1]) - (a[0]*a[0] + a[1]*a[1])
        );

        for(int[] point : points){

            if(maxHeap.size() < k){
                maxHeap.add(point);
            }
            else{
                int[] farthest = maxHeap.peek();
                int farthestDist = farthest[0]*farthest[0] + farthest[1]*farthest[1];
                int currDist = point[0]*point[0] + point[1]*point[1];

                if(currDist < farthestDist){
                    maxHeap.poll();
                    maxHeap.add(point);
                }
            }
        }

        int[][] kClosestPoints = new int[k][2];

        for(int i = 0; i < k; i++){
            kClosestPoints[i] = maxHeap.poll();
        }

        return kClosestPoints;
    }
}

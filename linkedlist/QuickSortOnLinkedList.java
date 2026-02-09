/**
 * Problem:
 * <Quick Sort for Linked List>
 *
 * Link:
 * <https://www.geeksforgeeks.org/problems/quick-sort-on-linked-list/1>
 *
 * Pattern:
 * <Sorting>
 *
 * Intuition:
 * - Quick Sort is an algorithm which works by typically selecting pivot element from the array, typically the last element.
 * - The array is then partitioned such that all the elements smaller than the pivot lie on the left side of the pivot.
 * - All the elements greater than or equal to the pivot lie on the right side of the pivot.
 * - Once the partition is completed, the algorithm recursively applies the same process to left and right subarrays.
 * - The recursive sorting continues until the entire array is sorted.
 *
 * Approach:
 * - Call partition function to get a pivot node at its correct position.
 * - Then traverse the current list.
 * - If a node has a value greater than the pivot then move it at the end of the tail.
 * - Else, keep it at its current position.
 * - Return pivot node.
 * - Find the tail of the list which is on the left side of the pivot, then recur for the left list.
 * - Similarly, after the left list recur on the right side of the pivot.
 * - Now, return the head of the linked list after joining the left list and right list, as the whole list is linked and sorted.
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(N) {Recursion Stack}
 *
 * Notes:
 * - Key points:
 * - Nodes never move.
 * - Only data values swap.
 * - prev marks boundary:
 * - Everything before prev < pivot.
 * - pivot lands exactly at prev.
 *
 * - Why the time complexity for the worst case is O(N^2)?
 * - Worst case happens when the pivot is smallest or the largest.
 * - Even if the list os completely sorted, partition still scans the entire list.
 *
 * - What if we choose the middle element directly like we do in merge sort?
 * - Even if we pick the middle node, quick sort can still degrade to O(N^2) in worst case.
 * - Because value distribution matters, not position.
 *
 * - Why merge sort is better?
 * - Merge sort:
 * - You split by position (middle of the list).
 * - You don't care about the values during the split.
 * - Merge step is always linear.
 * - That's why, merge sort is always O(N log N).
 *
 * - Quick sort:
 * - You split by value comparison.
 * - Pivot position after partition depends on values.
 * - Quick sort's balance depends on pivot VALUES, not pivot POSITION.
 * - If you could choose a true median by value, Quick sort will always be balanced.
 * - Choosing first node, last node or middle doesn't guarantee balanced partitions.
 *
 * - Choosing the middle node as pivot in Quick Sort does not guarantee balanced partitions, because partitioning depends on values, not positions.
 * - Merge Sort guarantees balance by splitting purely on position.
 */

package linkedlist;

public class QuickSortOnLinkedList {
    public static Node quickSort(Node node) {

        if(node == null || node.next == null) return node;

        Node tail = getTail(node);
        quickSortHelper(node, tail);

        return node;
    }

    public static Node getTail(Node head){

        Node curr = head;

        while(curr != null && curr.next != null) curr = curr.next;

        return curr;
    }

    public static void quickSortHelper(Node head, Node tail){

        if(head == null || head == tail) return;

        Node pivot = partition(head, tail);

        quickSortHelper(head, pivot);

        quickSortHelper(pivot.next, tail);

    }

    public static Node partition(Node head, Node tail){

        if(head == null || head == tail) return head;

        Node pivot = head, prev = head, curr = head;

        while(curr != tail.next){

            if(curr.data < pivot.data){

                int temp = curr.data;
                curr.data = prev.next.data;
                prev.next.data = temp;

                prev = prev.next;
            }
            curr = curr.next;
        }

        int temp = pivot.data;
        pivot.data = prev.data;
        prev.data = temp;

        return prev;
    }
}

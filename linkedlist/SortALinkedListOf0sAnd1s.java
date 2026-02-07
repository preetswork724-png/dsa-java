/**
 * Problem:
 * <Sort a Linked List of 0s and 1s>
 *
 * Link:
 * <https://www.geeksforgeeks.org/dsa/sort-a-linked-list-of-0s-and-1s/>
 *
 * Pattern:
 * <Partitioning / Segregating>
 *
 * Brute Force Intuition:
 * - Use ArrayList to store the values of the linked list.
 * - Sort the ArrayList.
 * - Re-assign value to each node in a linked list.
 *
 * - Why it is inefficient?
 * - Uses extra space and avoid pointer manipulation.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Better Approach Intuition:
 * - Two pass.
 * - Counting and overwriting.
 * - Count the number of zeroes and ones.
 * - Iterate iver the LinkedList and update the value of the nodes based on the count of zeroes and ones.
 *
 * - Why it is still not optimal?
 * - Uses two pass.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(1)
 *
 * Optimal Approach (Used Below):
 * - Split -> Collect -> Merge.
 * - Create two dummy lists:
 * - zero list.
 * - one list.
 * - Traverse once.
 * - Append nodes into the correct bucket.
 * - Concat the buckets.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - Check for edge cases like the number of zeroes or the number of ones is 0.
 */

package linkedlist;

public class SortALinkedListOf0sAnd1s {

    static ListNode sort01(ListNode head){
        ListNode zeroHead = new ListNode(-1), zeroTail = zeroHead;
        ListNode oneHead = new ListNode(-1), oneTail = oneHead;
        ListNode temp = head;

        while(temp != null){

            ListNode next = temp.next;
            temp.next = null;

            if(temp.val == 0){
                zeroTail.next = temp;
                zeroTail = temp;
            }
            else{
                oneTail.next = temp;
                oneTail = temp;
            }

            temp = next;
        }

        zeroTail.next = oneHead.next;
        if(zeroHead.next == null) return oneHead.next;
        return zeroHead.next;
    }
}

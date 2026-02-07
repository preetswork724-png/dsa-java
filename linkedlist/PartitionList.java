/**
 * Problem:
 * <Partition List>
 *
 * Link:
 * <https://leetcode.com/problems/partition-list/description/>
 *
 * Pattern:
 * <Partitioning / Segregating>
 *
 * Brute Force Intuition:
 * - Group the nodes with val < x using an ArrayList.
 * - Group the nodes with val >= x using another ArrayList.
 * - Create a new linked list and add all the nodes with val < x.
 * - Add all the nodes with val >= x.
 *
 * - Why it is inefficient?
 * - Uses extra space and avoids pointer manipulation.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Better and Optimal Approach (Used below):
 * - Create two dummy lists.
 * - One for values < x.
 * - Another for values >= x.
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
 * - Check for edge cases like when the number of values < x or number of values >= x are 0.
 */

package linkedlist;

public class PartitionList {

    public ListNode partition(ListNode head, int x) {
        ListNode leftHead = new ListNode(-1), leftTail = leftHead;
        ListNode rightHead = new ListNode(-1), rightTail = rightHead;
        ListNode curr = head;

        while(curr != null){

            ListNode next = curr.next;
            curr.next = null;

            if(curr.val < x){
                leftTail.next = curr;
                leftTail = curr;
            }
            else{
                rightTail.next = curr;
                rightTail = curr;
            }

            curr = next;
        }

        leftTail.next = rightHead.next;

        if(leftHead.next == null) return rightHead.next;
        return leftHead.next;
    }
}

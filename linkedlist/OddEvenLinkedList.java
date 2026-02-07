/**
 * Problem:
 * <Odd Even Linked List>
 *
 * Link:
 * <https://leetcode.com/problems/odd-even-linked-list/description/>
 *
 * Pattern:
 * <Partitioning / Segregating>
 *
 * Brute Force Intuition:
 * - Group the nodes with odd indices using an ArrayList.
 * - Group the nodes with even indices using another ArrayList.
 * - Create a new linked list and add all the values at odd indices.
 * - Add all the values at even indices.
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
 * - oddList.
 * - evenList.
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
 * - Check for edge cases like when the size of list is 1.
 */

package linkedlist;

public class OddEvenLinkedList {

    public ListNode oddEvenList(ListNode head) {
        ListNode oddIdxHead = new ListNode(-1),  oddIdxTail = oddIdxHead;
        ListNode evenIdxHead = new ListNode(-1),  evenIdxTail = oddIdxHead;
        ListNode curr = head;
        int currIdx = 1;

        while(curr != null){

            ListNode next = curr.next;
            curr.next = null;

            if(currIdx % 2 == 0){
                evenIdxTail.next = curr;
                evenIdxTail = curr;
            }
            else{
                oddIdxTail.next = curr;
                oddIdxTail = curr;
            }

            curr = next;
            currIdx++;
        }

        oddIdxTail.next = evenIdxHead.next;
        if(oddIdxHead.next == null) return evenIdxHead.next;
        return oddIdxHead.next;
    }

}

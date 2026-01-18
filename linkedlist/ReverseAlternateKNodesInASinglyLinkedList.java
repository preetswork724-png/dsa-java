/**
 * Problem:
 * <Reverse alternate K nodes in a Singly Linked List>
 *
 * Link:
 * <https://www.geeksforgeeks.org/dsa/reverse-alternate-k-nodes-in-a-singly-linked-list/>
 *
 * Pattern:
 * <Reverse Linked List pattern with group boundaries>
 *
 * Brute Force Intuition:
 * - Convert the linked list to an array, process in chunks, then rebuild the list.
 *
 * - Why it is inefficient?
 * - Requires extra space.
 * - Avoids direct pointer manipulation.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Better Approach Intuition:
 * - Calculate the size of the Linked List.
 * - Divide the size by the number of groups that you can form.
 * - Reverse sub-List in groups of K until the number of group becomes 0.
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
 * - Extract sub-List of K nodes, reverse it and reattach it.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - If there is no next kth node, return head immediately.
 * - While finding out the next kth Node, always check if the curr node is null or not.
 * - The prev variable represents the tail of the reversed sub-List.
 */
package linkedlist;

public class ReverseAlternateKNodesInASinglyLinkedList {

    public ListNode kAltReverse(ListNode head, int k){

        if(head == null || k == 1) return head;

        boolean rev = true;
        ListNode prev = null, curr = head;

        while(curr != null){

            if(rev){

                ListNode kthNode = getKthNode(curr, k);

                if(kthNode == null){
                    ListNode newHead = reverse(curr);
                    if(prev == null) head = newHead;
                    else prev.next = newHead;
                    return head;
                }

                ListNode nextNode = kthNode.next;
                kthNode.next = null;

                ListNode newHead = reverse(curr);

                if(prev == null){
                    head = newHead;
                }
                else{
                    prev.next = newHead;
                }

                curr.next = nextNode;
                prev = curr;
                curr = curr.next;
                rev = false;
            }
            else {
                ListNode kth = getKthNode(curr, k);
                if(kth == null) return head;

                prev = kth;
                curr = kth.next;
                rev = true;
            }
        }
        return head;
    }

    public ListNode reverse(ListNode head){
        ListNode curr = head, prev = null;

        while(curr != null){
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return prev;
    }

    public ListNode getKthNode(ListNode head, int k){
        ListNode curr = head;

        for(int i = 0; i < k-1; i++){
            if(curr == null) return null;
            curr = curr.next;
        }
        return curr;
    }

}

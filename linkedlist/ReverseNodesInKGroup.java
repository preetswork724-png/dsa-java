/**
 * Problem:
 * <Reverse Nodes in k-Group>
 *
 * Link:
 * <https://leetcode.com/problems/reverse-nodes-in-k-group/description/>
 *
 * Pattern:
 * <Reverse Linked List pattern with group boundaries>
 *
 * Brute Force Intuition:
 * - Store all the nodes of a Linked List in to some another type of List.
 * - Reverse nodes in the List in groups of K.
 * - Create a new Linked List out of the nodes stored in the List.
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

public class ReverseNodesInKGroup {

    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null || k == 1) return head;

        ListNode curr = head, prev = null;

        while(curr != null){

            ListNode kthNode = getKthNode(curr, k);

            if(kthNode == null) return head;

            ListNode nextNode = kthNode.next;
            kthNode.next = null;

            reverse(curr);

            if(prev == null){
                head = kthNode;
            }
            else{
                prev.next = kthNode;
            }

            curr.next = nextNode;
            prev = curr;
            curr = curr.next;
        }
        return head;

        // TC :- O(N)
        // SC :- O(1)
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

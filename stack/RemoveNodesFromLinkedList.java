/**
 * Problem:
 * <Remove Nodes From Linked List>
 *
 * Link:
 * <https://leetcode.com/problems/remove-nodes-from-linked-list/description/>
 *
 * Pattern:
 * <Stack>
 *
 * Brute Force Intuition:
 * - Maintain a list to store all the nodes.
 * - For every node, find the next greater node to it.
 * - If you find the next greater node then simply don't store that node.
 * - If you don't store that node.
 * - Form a new list out of those nodes and return its head.
 *
 * - Why it is inefficient?
 * - Redundantly finding the next greater node for each node.
 *
 * Time Complexity:
 * - O(N^2)
 * Space Complexity:
 * - O(N)
 *
 * Better Approach Intuition:
 * - Maintain a monotonically decreasing stack to store all the nodes.
 * - Push the node if the stack is empty and if the node is the greatest one till that index.
 * - Pop when you find a next greater node to the existing nodes in the stack.
 * - Create a list out of all the nodes remaining in the stack and return its head.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Why this is not optimal?
 * - It is a standard approach but list modification can be done in place without using extra memory.
 *
 * Optimal Approach (used below) :
 * - If you find a node with value smaller than the max, delete that node by skipping it.
 * - Otherwise, update the max and move the pointer.
 * - This builds a list where each node's value is greater than the previous one.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * Reverse the list in the end.
 */
package stack;

class ListNode {
     int val;
     ListNode next;
     ListNode() {}
     ListNode(int val) { this.val = val; }
     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 }

public class RemoveNodesFromLinkedList {

    public ListNode removeNodes(ListNode head){
        head = reverse(head);

        ListNode curr = head;
        int max = curr.val;

        while(curr != null && curr.next != null){
            if(curr.next.val < max){
                curr.next = curr.next.next;
            }else{
                curr = curr.next;
                max = curr.val;
            }
        }
        return reverse(head);
    }

    public ListNode reverse(ListNode head){
        ListNode prev = null, curr = head;

        while(curr != null){
            ListNode nxt = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nxt;
        }

        return prev;
    }

}

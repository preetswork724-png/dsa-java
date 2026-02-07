/**
 * Problem:
 * <Add Number Linked Lists>
 *
 * Link:
 * <https://www.geeksforgeeks.org/problems/add-two-numbers-represented-by-linked-lists/1>
 *
 * Pattern:
 * <Linked List Digit Simulation>
 *
 * Brute Force Intuition:
 * - Remove the leading zeroes from both of the lists.
 * - Convert the lists to ArrayList.
 * - Add two numbers as if they have been represented by arrays.
 * - Create a new linked list of the elements stored in the ArrayList.
 *
 * - Why it is inefficient?
 * - Uses extra space and avoids pointer manipulation.
 *
 * Time Complexity:
 * - O(N + M)
 * Space Complexity:
 * - O(N + M)
 *
 * Better and Optimal Approach (Used below):
 * - Remove the leading zeroes from both of the lists.
 * - Reverse the lists.
 * - Create a dummy list to store the sum digit by digit.
 * - Reverse the final list.
 *
 * Time Complexity:
 * - O(N + M)
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - Whenever the keywords are:
 * - “each node contains a digit”.
 * - “add / subtract / multiply numbers represented by linked lists”.
 * - “most significant digit first”.
 * - The immediate thought should be: "I need to align digits -> process from LSD -> manage carry".
 *
 * Mistake log:
 * - Moved the dummy pointer and lost the reference to the head.
 */

package linkedlist;

public class AddNumberLinkedLists {

    public Node addTwoLists(Node head1, Node head2) {

        while(head1 != null && head1.data == 0){
            head1 = head1.next;
        }

        while(head2 != null && head2.data == 0){
            head2 = head2.next;
        }

        Node first = reverse(head1), second = reverse(head2);

        Node dummy = new Node(-1), tail = dummy;
        int carry = 0;

        while(first != null || second != null || carry != 0){

            int sum = carry;

            sum += (first != null) ? first.data : 0;
            sum += (second != null) ? second.data : 0;

            tail.next = new Node(sum % 10);
            tail = tail.next;

            carry = sum / 10;

            if(first != null) first = first.next;
            if(second != null) second = second.next;
        }

        return reverse(dummy.next);
    }

    public Node reverse(Node head){

        Node prev = null, curr = head;

        while(curr != null){
            Node temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }

        return prev;
    }
}

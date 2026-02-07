/**
 * Problem:
 * <Subtraction in Linked List>
 *
 * Link:
 * <http://geeksforgeeks.org/problems/subtraction-in-linked-list/1>
 *
 * Pattern:
 * <Linked List Digit Simulation>
 *
 * Brute Force Intuition:
 * - Convert the lists to ArrayList.
 * - Subtract two numbers as if they have been represented by arrays.
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
 * - Compare the lists to check which list is the smaller one.
 * - Reverse the lists.
 * - Create a dummy list to store the result digit by digit.
 * - Reverse the final list.
 *
 * Time Complexity:
 * - O(N + M)
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - Edge case:
 * - When the difference between two numbers is 0, handle that case separately.
 * - Return a node with value 0.
 *
 * Mistake log:
 * - Reversed the list before even comparing them.
 */

package linkedlist;

public class SubtractionInLinkedList {
    
    static Node subLinkedList(Node head1, Node head2) {

        if(isSmaller(head1, head2)){
            Node temp = head1;
            head1 = head2;
            head2 = temp;
        }

        Node revL1 = reverse(head1), revL2 = reverse(head2);

        Node dummy = new Node(-1), tail = dummy;
        Node curr1 = revL1, curr2 = revL2;
        int borrow = 0;

        while(curr1 != null){

            int num1 = curr1.data, num2 = (curr2 != null) ? curr2.data : 0;
            int diff = num1 - num2 - borrow;

            if(diff < 0){
                diff += 10;
                borrow = 1;
            }
            else{
                borrow = 0;
            }

            tail.next = new Node(diff % 10);
            tail = tail.next;

            curr1 = curr1.next;
            if(curr2 != null) curr2 = curr2.next;
        }

        Node temp = reverse(dummy.next);

        while(temp != null && temp.data == 0) temp = temp.next;

        if(temp == null) return new Node(0);

        return temp;
    }

    static boolean isSmaller(Node head1, Node head2){

        int size1 = 0, size2 = 0;

        Node curr = head1;

        while(curr != null){
            size1++;
            curr = curr.next;
        }

        curr = head2;

        while(curr != null){
            size2++;
            curr = curr.next;
        }

        if(size1 < size2) return true;
        if(size1 > size2) return false;

        Node curr1 = head1, curr2 = head2;

        while(curr1 != null && curr2 != null){

            if(curr1.data < curr2.data) return true;
            if(curr1.data > curr2.data) return false;

            curr1 = curr1.next;
            curr2 = curr2.next;
        }
        return false;
    }

    static Node reverse(Node head){
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

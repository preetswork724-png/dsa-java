/**
 * Problem:
 * <Palindromic Linked List>
 *
 * Link:
 * <https://leetcode.com/problems/palindrome-linked-list/description/>
 *
 * Pattern:
 * <Fast & Slow Pointer>
 *
 * Brute Force Intuition:
 * - Duplicate the list.
 * - Reverse it.
 * - Compare the two lists.
 *
 * - Why it is inefficient?
 * - Uses extra memory.
 * - Avoids pointer manipulation which is the core of operations in Linked List.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Better Approach Intuition:
 * - Use fast and slow approach to traverse till the middle of the List.
 * - Store the first half of the list in the stack.
 * - In palindromes of odd length, the middle is skipped. When fast pointer is not null, advance the slow just once to skip the middle.
 * - Pop and keep comparing.
 *
 * - Why it is still not optimal?
 * - Uses extra space to store the first half of the list.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(N)
 *
 * Optimal Approach (Used Below):
 * - Find the middle of the Linked List.
 * - Reverse the sublist from the middle.
 * - Compare it.
 * - And then reattach to the original list in order to maintain a good practice of not modifying the input.
 *
 * Time Complexity:
 * - O(N)
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - Update fast pointer carefully, in cases of odd and even lengths of list.
 * - You only store a copy of reference to the reversed subList and not the entire list.
 * - Iterate only till the length of the second half because palindromes are of equal length.
 */
package linkedlist;

public class PalindromeLinkedList {
    public boolean isPalindrome(ListNode head) {
        ListNode slow = head, fast = head;

        while(fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode secondHalfPtr = reverse(slow.next);
        ListNode copySecondHalf = secondHalfPtr;
        ListNode firstHalfPtr = head;

        while(secondHalfPtr != null){
            if(firstHalfPtr.val != secondHalfPtr.val){
                return false;
            }
            firstHalfPtr = firstHalfPtr.next;
            secondHalfPtr = secondHalfPtr.next;
        }

        slow.next = reverse(copySecondHalf);
        return true;
    }

    public ListNode reverse(ListNode head){
        ListNode prev = null, curr = head;

        while(curr != null){
            ListNode tempNode = curr.next;
            curr.next = prev;
            prev = curr;
            curr = tempNode;
        }

        return prev;
    }
}

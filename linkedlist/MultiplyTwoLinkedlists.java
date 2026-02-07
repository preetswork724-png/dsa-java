/**
 * Problem:
 * <Multiply two linked lists>
 *
 * Link:
 * <https://www.geeksforgeeks.org/problems/multiply-two-linked-lists/1>
 *
 * Pattern:
 * <Linked List digit simulation>
 *
 * Brute Force Intuition:
 * - Convert both linked list into actual two numbers.
 * - Multiply them.
 * - Return the result.
 *
 * - Why it is inefficient?
 * - Numbers can exceed long.
 * - Overflow for large lists.
 * - Ignores the modulo constraint properly.
 *
 * Time Complexity:
 * - O(N + M)
 * Space Complexity:
 * - O(1)
 *
 * Better Approach Intuition:
 * - Traverse the lists one by one.
 * - Form the numbers digit-by-digit.
 * - Apply modulo at each step as it prevents overflow.
 *
 * Time Complexity:
 * - O(N + M)
 * Space Complexity:
 * - O(1)
 *
 * Why it is still not optimal?
 * - Uses two passes.
 *
 * Optimal Approach (Used Below):
 * - Process both lists in a single loop.
 * - Avoids two separate traversals.
 *
 * Time Complexity:
 * - O(N + M)
 * Space Complexity:
 * - O(1)
 *
 * Notes:
 * - Apply modulo at each step to prevent overflow.
 */

package linkedlist;

public class MultiplyTwoLinkedlists {

    public long multiplyTwoLists(Node first, Node second) {
        Node temp1 = first, temp2 = second;
        long num1 = 0, num2 = 0;
        long mod = 1000000007;

        while(temp1 != null || temp2 != null){

            if(temp1 != null){
                num1 = (num1 * 10 + temp1.data) % mod;
                temp1 = temp1.next;
            }

            if(temp2 != null){
                num2 = (num2 * 10 + temp2.data) % mod;
                temp2 = temp2.next;
            }

        }
        return (num1 * num2) % mod;
    }
}

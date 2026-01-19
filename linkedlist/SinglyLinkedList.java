/**
 * Singly Linked List Implementation
 *
 * Supports:
 * - Insertion (head, tail, index)
 * - Deletion (head, tail, index, node)
 *
 * Time complexities:
 * - Insert at head: O(1)
 * - Insert at tail: O(1)
 * - Insert at index: O(N)
 * - Delete at tail: O(N)
 * - Delete at index: O(N)
 */

package linkedlist;

class Node{

    int data;
    Node next;

    public Node(int data){
        this.data = data;
    }
}

public class SinglyLinkedList {

    Node head, tail;
    int size;

    // 1. Add at head
    public void addFirst(int data){

        Node newNode = new Node(data);

        if(head == null){
            head = tail = newNode;
            size++;
            return;
        }

        newNode.next = head;
        head = newNode;
        size++;
    }

    // 2. Add at tail
    public void addLast(int data){

        if(head == null) {
            addFirst(data);
            return;
        }

        Node newNode = new Node(data);
        tail.next = newNode;
        tail = newNode;
        size++;
    }

    // 3. Add at index
    public void addAt(int index, int data){

        if(index < 0 || index > size){
            System.out.println("Index out of bounds");
            return;
        }

        if(index == 0){
            addFirst(data);
            return;
        }

        if(index == size){
            addLast(data);
            return;
        }

        Node curr = head;
        Node newNode = new Node(data);

        for(int i = 0; i < index - 1; i++){
            curr = curr.next;
        }

        Node temp = curr.next;
        curr.next = newNode;
        newNode.next = temp;
        size++;
    }

    // Delete
    public void deleteAt(int index){

        if(index < 0 || index >= size){
            System.out.println("Index out of bounds");
            return;
        }

        if(head == null){
            System.out.println("Empty List");
            return;
        }

        // 4. Delete at head
        if(index == 0){
            head = head.next;
            if(size == 1) tail = null;
            size--;
            return;
        }

        // 5. Delete at index
        Node curr = head;

        for(int i = 0; i < index - 1; i++){
            curr = curr.next;
        }

       if(curr == tail){
           tail = curr;
       }

       curr.next = curr.next.next;
       size--;
    }

    // 6. Delete node without head or position (it is guaranteed that the node is not the last node in the linked list)
    public void delete(Node node) {

        if(head == null){
            System.out.println("Empty list");
            return;
        }

        if(node == null || node.next == null) return;

        node.data = node.next.data;
        node.next = node.next.next;
        size--;

    }
}

package xyz.squadlabs.linkedList;

public class SinglyLinkedList {

  private Node head;

  // Node definition
  static class Node {
    int data;
    Node next;

    Node(int data) {
      this.data = data;
      this.next = null;
    }
  }

  // Insert at end
  public void insertAtEnd(int data) {
    Node newNode = new Node(data);

    if (head == null) {
      head = newNode;
      return;
    }

    Node current = head;
    while (current.next != null) {
      current = current.next;
    }
    current.next = newNode;
  }

  // Insert at beginning
  public void insertAtBeginning(int data) {
    Node newNode = new Node(data);
    newNode.next = head;
    head = newNode;
  }

  // Delete at beginning
  public void deleteAtBeginning() {
    if (head != null) {
      head = head.next;
    }
  }

  // Delete at end
  public void deleteAtEnd() {
    if (head == null || head.next == null) {
      head = null;
      return;
    }

    Node current = head;
    while (current.next.next != null) {
      current = current.next;
    }
    current.next = null;
  }

  // Reverse Linked List
  public void reverse() {
    Node prev = null;
    Node curr = head;

    while (curr != null) {
      Node next = curr.next; // save next
      curr.next = prev; // reverse link
      prev = curr; // move prev
      curr = next; // move curr
    }

    head = prev; // update head
  }

  // Print list
  public void printList() {
    Node current = head;
    while (current != null) {
      System.out.print(current.data + " -> ");
      current = current.next;
    }
    System.out.println("null");
  }

  public static void main(String[] args) {

    SinglyLinkedList list = new SinglyLinkedList();

    list.insertAtEnd(10);
    list.insertAtEnd(20);
    list.insertAtEnd(30);
    list.insertAtBeginning(5);

    System.out.println("Original list:");
    list.printList();

    list.reverse();
    System.out.println("After reversing:");
    list.printList();

    list.deleteAtBeginning();
    System.out.println("After deleting at beginning:");
    list.printList();

    list.deleteAtEnd();
    System.out.println("After deleting at end:");
    list.printList();
  }
}

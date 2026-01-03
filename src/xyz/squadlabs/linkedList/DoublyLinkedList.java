package xyz.squadlabs.linkedList;

public class DoublyLinkedList {

  // Node definition
  static class Node {
    int data;
    Node prev;
    Node next;

    Node(int data) {
      this.data = data;
      this.prev = null;
      this.next = null;
    }
  }

  // Head of the list
  private Node head;

  // ================= INSERT AT BEGINNING =================
  public void insertAtBeginning(int data) {
    Node newNode = new Node(data);

    if (head != null) {
      newNode.next = head;
      head.prev = newNode;
    }

    head = newNode;
  }

  // ================= INSERT AT END =================
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
    newNode.prev = current;
  }

  // ================= DELETE AT BEGINNING =================
  public void deleteAtBeginning() {
    if (head == null) {
      return;
    }

    if (head.next != null) {
      head = head.next;
      head.prev = null;
    } else {
      head = null;
    }
  }

  // ================= DELETE AT END =================
  public void deleteAtEnd() {
    if (head == null) {
      return;
    }

    if (head.next == null) {
      head = null;
      return;
    }

    Node current = head;
    while (current.next != null) {
      current = current.next;
    }

    // current is last node
    current.prev.next = null;
  }

  // ================= PRINT LIST (FORWARD) =================
  public void printForward() {
    Node current = head;
    while (current != null) {
      System.out.print(current.data + " <->");
      current = current.next;
    }
    System.out.println("null");
  }

  // ================= PRINT LIST (BACKWARD) =================
  public void printBackward() {
    if (head == null) {
      System.out.println("null");
      return;
    }

    Node current = head;
    while (current.next != null) {
      current = current.next;
    }

    while (current != null) {
      System.out.print(current.data + " <-> ");
      current = current.prev;
    }
    System.out.println("null");
  }

  public static void main(String[] args) {

    DoublyLinkedList list = new DoublyLinkedList();

    // Insert operations
    list.insertAtBeginning(20);
    list.insertAtBeginning(10);
    list.insertAtEnd(30);
    list.insertAtEnd(40);

    System.out.println("List after insertions (forward):");
    list.printForward();

    System.out.println("List after insertions (backward):");
    list.printBackward();

    // Delete at beginning
    list.deleteAtBeginning();
    System.out.println("After delete at beginning:");
    list.printForward();

    // Delete at end
    list.deleteAtEnd();
    System.out.println("After delete at end:");
    list.printForward();
  }
}

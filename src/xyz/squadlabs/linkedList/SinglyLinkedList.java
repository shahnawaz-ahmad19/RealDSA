package xyz.squadlabs.linkedList;

public class SinglyLinkedList {
  private Node head;

  static class Node {
    int data;
    Node next;

    Node(int data) {
      this.data = data;
      this.next = null;
    }
  }

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

  public void insertAtBeginning(int data) {
    Node newNode = new Node(data);
    newNode.next = head;
    head = newNode;
  }

  public void deleteAtBeginning() {
    if (head != null) {
      head = head.next;
    }
  }

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

    list.printList();

    list.deleteAtBeginning();
    list.printList();

    list.deleteAtEnd();
    list.printList();
  }

}

package xyz.squadlabs.linkedList;

public class SinglyLinkedList {

  // Internal Class representing a single Node in the list
  public class Node {
    int value; // The data stored in the node
    Node next; // Reference (pointer) to the next node in the list
  }

  // Pointers to maintain the list state
  private Node head; // Points to the very first node (start of list)
  private int size; // Keeps track of the total number of nodes
  private Node tail; // Points to the very last node (end of list)

  // Returns current size of the list
  public int size() {
    return size;
  }

  // O(1) - Constant Time
  // Adds a new item to the BEGINNING of the list
  public void addfirst(int item) {
    // Step 1: Create the new node
    Node nn = new Node();
    nn.value = item;

    // Step 2: Check if list is empty
    if (size == 0) {
      // If empty, this new node is both the head and the tail
      head = nn;
      tail = nn;
      size++;
    } else {
      // Step 3: Link new node to the current head
      nn.next = head; // New node points to old head
      head = nn; // Move head pointer to the new node
      size++;
    }
  }

  // O(1) - Constant Time (because we have a tail pointer)
  // Adds a new item to the END of the list
  public void addLast(int item) {
    if (size == 0) {
      addfirst(item); // Reuse logic if list is empty
    } else {
      // Step 1: Create new node
      Node nn = new Node();
      nn.value = item;

      // Step 2: Link current tail to new node
      tail.next = nn; // Old tail points to new node

      // Step 3: Update tail pointer
      tail = nn; // Tail now points to the new last node
      size++;
    }
  }

  // O(n) - Linear Time
  // Helper method: returns the actual Node object at a specific index
  private Node getNode(int idx) throws Exception {
    if (idx < 0 || idx >= size) {
      throw new Exception("Index out of bound");
    }

    // Start at head and jump 'idx' times
    Node temp = head;
    for (int i = 0; i < idx; i++) {
      temp = temp.next;
    }
    return temp; // Returns the reference to the node at that index
  }

  // O(n) - Linear Time (due to getNode traversal)
  // Inserts a new item at a specific index
  public void addAtIndex(int item, int idx) throws Exception {
    if (idx < 0 || idx > size) {
      throw new Exception("Index out of bound");
    }

    // Handle edge cases efficiently
    if (idx == 0) {
      addfirst(item);
    } else if (idx == size) {
      addLast(item);
    } else {
      // Step 1: Get the node immediately BEFORE the target index (idx - 1)
      Node k_1th = getNode(idx - 1);

      // Step 2: Create new node
      Node nn = new Node();
      nn.value = item;

      // Step 3: Stitch the new node into the chain
      // Order is crucial here!
      nn.next = k_1th.next; // New node points to the node ahead
      k_1th.next = nn; // Previous node points to new node

      size++;
    }
  }

  // Prints the entire list
  public void display() {
    Node temp = head;
    while (temp != null) {
      System.out.print(temp.value + "->");
      temp = temp.next; // Move to next node
    }
    System.out.println(".");
  }

  // O(1)
  public int getFirst() {
    if (head == null) {
      throw new RuntimeException("LinkedList is empty");
    }
    return head.value;
  }

  // O(1)
  public int getLast() {
    if (tail == null) {
      throw new RuntimeException("LinkedList is empty");
    }
    return tail.value;
  }

  // O(n)
  public int getAtIndex(int idx) throws Exception {
    return getNode(idx).value;
  }

  // O(1)
  // Removes the first node and returns its value
  public int removeFirst() throws Exception {
    // FIXED: Added check for empty list to prevent crash
    if (size == 0) {
      throw new Exception("LinkedList is empty");
    }

    Node temp = head; // Backup the node we are about to delete

    if (size == 1) {
      // If only one node, list becomes empty
      head = null;
      tail = null;
      size--;
      return temp.value;
    } else {
      // Step 1: Move head forward
      head = head.next;

      // Step 2: Disconnect the old head
      temp.next = null;

      size--;
      return temp.value;
    }
  }

  // O(n) - Linear Time (must traverse to find second-to-last node)
  // Removes the last node and returns its value
  public int removeLast() throws Exception {
    if (size < 1) {
      throw new Exception("LinkedList is empty");
    }

    if (size == 1) {
      return removeFirst(); // Efficiently handle single element
    } else {
      // Step 1: Find the second to last node (size - 2)
      Node secondLast = getNode(size - 2);

      // Step 2: Backup the current tail (to return its value)
      Node temp = tail;

      // Step 3: Update tail
      tail = secondLast;
      tail.next = null; // Remove the link to the old tail

      size--;
      return temp.value;
    }
  }

  // O(n)
  // Removes node at specific index
  public int removeAtIndex(int idx) throws Exception {
    if (idx < 0 || idx >= size) {
      throw new Exception("Index out of bound");
    }

    if (idx == 0) {
      return removeFirst();
    } else if (idx == size - 1) {
      return removeLast();
    } else {
      // Step 1: Get the node BEFORE the one to be deleted
      Node previous = getNode(idx - 1);

      // Step 2: Identify the node to delete
      Node current = previous.next;

      // Step 3: Bypass the current node
      // Link previous directly to the node AFTER current
      previous.next = current.next;

      // Step 4: Disconnect current node completely
      current.next = null;

      size--;
      return current.value;
    }
  }

  // Time Complexity: O(n) - We visit every node once
  // Reverses the linked list in-place
  public void reverseList() {
    Node current = head; // Start processing from the beginning
    Node previous = null; // Initially, there is no node before the head

    // Traverse through the list until we reach the end (null)
    while (current != null) {

      // Step 1: Backup the next node
      // Important: If we don't save this, we lose access to the rest of the list
      // when we change current.next in the step below.
      Node ahead = current.next;

      // Step 2: Reverse the link
      // Point the current node backward to the previous node instead of forward.
      current.next = previous;

      // Step 3: Move pointers forward
      // The 'current' node becomes the 'previous' node for the next iteration.
      previous = current;

      // Move to the next node (which we saved in 'ahead') to continue processing.
      current = ahead;
    }

    // Step 4: Update Head
    // After the loop, 'current' is null and 'previous' is at the last node.
    // The old last node is now the new head of the reversed list.
    head = previous;
  }

  public static void main(String[] args) throws Exception {
    SinglyLinkedList ll = new SinglyLinkedList();

    ll.addfirst(10); // List: 10
    ll.addfirst(20); // List: 20->10
    ll.addfirst(30); // List: 30->20->10

    ll.addLast(-9); // List: 30->20->10->-9
    ll.addLast(11); // List: 30->20->10->-9->11

    // Insert -88 at index 2 (between 20 and 10)
    ll.addAtIndex(-88, 2);
    ll.display(); // Output: 30->20->-88->10->-9->11->.

    System.out.println(ll.removeFirst()); // Removes 30
    ll.display(); // Output: 20->-88->10->-9->11->.

    System.out.println(ll.removeLast()); // Removes 11
    ll.display(); // Output: 20->-88->10->-9->.

    System.out.println(ll.removeAtIndex(2)); // Removes 10 (index 2)
    ll.display(); // Output: 20->-88->-9->.

    ll.reverseList();
    ll.display(); // Output: -9->-88->20->.
  }

}
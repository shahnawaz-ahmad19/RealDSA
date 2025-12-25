package xyz.squadlabs.heaps;

import java.util.ArrayList;

public class MaxHeap {

  private ArrayList<Integer> heap;

  public MaxHeap() {
    heap = new ArrayList<>();
  }

  // Insert a value into the max heap
  public void insert(int value) {

    // 1. Add element at the end
    heap.add(value);

    // 2. Start heapifying-up from the last index
    int i = heap.size() - 1;

    // 3. Move up until heap property is restored
    while (i > 0) {
      int parent = (i - 1) / 2;

      // If parent is already larger, stop
      if (heap.get(parent) >= heap.get(i)) {
        break;
      }
      // otherwise swap with the parent
      swap(i, parent);

      // move to parent index
      i = parent;
    }
  }

  // ==================DELETE OPERATION==============
  public int delete() {
    if (heap.isEmpty()) {
      throw new IllegalStateException("Heap is empty");
    }

    // 1. store the root value
    int root = heap.get(0);

    // 2. Move the last element to root
    int last = heap.remove(heap.size() - 1);

    // 3. Heapify down if heap is not empty
    if (!heap.isEmpty()) {
      heap.set(0, last);

      int i = 0;
      int size = heap.size();

      while (2 * i + 1 < size) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int largest = left;
        if (right < size && heap.get(right) > heap.get(left)) {
          largest = right;
        }
        if (heap.get(i) >= heap.get(largest)) {
          break;
        }
        swap(i, largest);
        i = largest;

      }
    }
    return root;

  }

  public void swap(int i, int j) {
    int temp = heap.get(i);
    heap.set(i, heap.get(j));
    heap.set(j, temp);

  }

}

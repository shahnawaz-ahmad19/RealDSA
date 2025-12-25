package xyz.squadlabs.heaps;

public class HeapifyExample {
  // Main function to build a heap from an array
  public static void buildHeap(int[] arr) {
    int n = arr.length;

    // start from last non-leaf node
    for (int i = (n / 2) - 1; i >= 0; i--) {
      heapifyDown(arr, n, i);
    }

  }

  // Heapify down function
  public static void heapifyDown(int[] arr, int n, int i) {
    while (2 * i + 1 < n) {
      int left = 2 * i + 1;
      int right = 2 * i + 2;
      int largest = left;

      if (right < n && arr[right] > arr[left]) {
        largest = right;
      }

      if (arr[i] >= arr[largest]) {
        break;
      }
      swap(arr, i, largest);

      i = largest;
    }
  }

  private static void swap(int[] arr, int i, int largest) {
    int temp = arr[i];
    arr[i] = arr[largest];
    arr[largest] = temp;
  }

  public static void main(String[] args) {
    int[] arr = { 10, 20, 30, 40, 50, 60 };

    buildHeap(arr);

    // Print heap
    for (int val : arr) {
      System.out.print(val + " ");
    }
  }

}

package xyz.squadlabs.graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GraphRepresentationAsAdjacencyLists {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    System.out.print("Enter number of nodes: ");
    int n = sc.nextInt();

    System.out.print("Enter number of edges: ");
    int m = sc.nextInt();

    // Create adjacency list of size n+1
    List<List<Integer>> graph = new ArrayList<>();

    // Initialize each list
    for (int i = 0; i <= n; i++) {
      graph.add(new ArrayList<>());
    }

    // Read edges
    System.out.println("Enter edges (u v):");
    for (int i = 0; i < m; i++) {
      int u = sc.nextInt();
      int v = sc.nextInt();

      // Since graph is undirected
      graph.get(u).add(v);
      graph.get(v).add(u);
    }

    // Print adjacency list
    System.out.println("\nAdjacency List:");
    for (int i = 1; i <= n; i++) {
      System.out.print(i + ": " + graph.get(i));
      System.out.println();
    }
  }

}

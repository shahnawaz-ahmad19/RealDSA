package xyz.squadlabs.graphs;

import java.util.Scanner;

public class GraphRepresentationAsAdjacencyMatrix {
  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    System.out.print("Enter the number of nodes in the graph: ");
    // Enter the number of nodes in the graph
    int n = sc.nextInt();

    System.out.println("Enter the number of edges in the graph: ");
    // Enter the number of edges in the graph
    int m = sc.nextInt();

    // Create an adjacency matrix
    int[][] adjacencyMatrix = new int[n + 1][n + 1];

    System.out.println("Enter the edges (u v):");
    for (int i = 0; i < m; i++) {
      int u = sc.nextInt();
      int v = sc.nextInt();
      adjacencyMatrix[u][v] = 1;
      adjacencyMatrix[v][u] = 1; // For undirected graph
    }

    System.out.println("\nAdjacency Matrix:");
    // Printing
    // the adjacency matrix
    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= n; j++) {
        System.out.print(adjacencyMatrix[i][j] + " ");
      }
      System.out.println();
    }
  }

}

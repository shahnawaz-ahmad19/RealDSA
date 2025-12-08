package xyz.squadlabs.graphs;

import java.util.HashMap;
import java.util.HashSet;

public class GraphImplementationUsingHashmap {
  private HashMap<Integer, HashMap<Integer, Integer>> map;

  public GraphImplementationUsingHashmap(int v) {
    map = new HashMap<>();
    for (int i = 0; i <= v; i++) {
      map.put(i, new HashMap<>());
    }

  }

  // adding edges to the graph
  public void AddEdge(int u, int v, int wt) {
    map.get(u).put(v, wt);
    map.get(v).put(u, wt);
  }

  // checking if edge exists in graph
  public boolean containsEdge(int u, int v) {
    return map.get(u).containsKey(v) && map.get(v).containsKey(u);
  }

  // checking if vertex exists in graph
  public boolean containsVertex(int u) {
    return map.containsKey(u);
  }

  // checking no of edges in graph
  public int noOfEdges() {
    int sum = 0;
    for (int key : map.keySet()) {
      sum += map.get(key).size();
    }
    return sum / 2;
  }

  // removing edge from graph
  public void removeEdge(int u, int v) {
    if (containsEdge(u, v)) {
      map.get(u).remove(v);
      map.get(v).remove(u);
    }

  }

  // removing vertex from graph
  public void removeVertex(int u) {
    for (int key : map.get(u).keySet()) {
      map.get(key).remove(u);
    }
    map.remove(u);
  }

  // printing graph
  public void printGraph() {
    for (int key : map.keySet()) {
      System.out.println(key + " -> " + map.get(key));
    }
  }

  // checking a path between two vertices in graph
  public boolean hasPath(int src, int dest, HashSet<Integer> visited) {
    if (src == dest) {
      return true;
    }
    visited.add(src);
    for (int neighbours : map.get(src).keySet()) {
      if (!visited.contains(neighbours)) {
        boolean ans = hasPath(neighbours, dest, visited);
        if (ans) {
          return ans;
        }
      }
    }
    visited.remove(src);
    return false;
  }
}

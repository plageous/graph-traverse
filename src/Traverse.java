import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Traverse {
  public static void main(String[] args) {
    // See below site for visualization of this graph
    // https://auberonedu.github.io/graph-explore/graph_site/viz.html
    Vertex<Integer> v3  = new Vertex<>(3);
    Vertex<Integer> v7  = new Vertex<>(7);
    Vertex<Integer> v12 = new Vertex<>(12);
    Vertex<Integer> v34 = new Vertex<>(34);
    Vertex<Integer> v56 = new Vertex<>(56);
    Vertex<Integer> v78 = new Vertex<>(78);
    Vertex<Integer> v91 = new Vertex<>(91);
    Vertex<Integer> v45 = new Vertex<>(45);
    Vertex<Integer> v23 = new Vertex<>(23);
    Vertex<Integer> v67 = new Vertex<>(67);

    v3.neighbors  = new ArrayList<>(List.of(v7, v34));
    v7.neighbors  = new ArrayList<>(List.of(v12, v45, v34, v56));
    v12.neighbors = new ArrayList<>(List.of(v7, v56, v78));
    v34.neighbors = new ArrayList<>(List.of(v34, v91)); 
    v56.neighbors = new ArrayList<>(List.of(v78));
    v78.neighbors = new ArrayList<>(List.of(v91));
    v91.neighbors = new ArrayList<>(List.of(v56));
    v45.neighbors = new ArrayList<>(List.of(v23));
    v23.neighbors = new ArrayList<>(List.of());
    v67.neighbors = new ArrayList<>(List.of(v91));

    printVertices(v7);
    System.out.println(sum(v7));
  }

  public static void printVertices(Vertex<?> current) {
    // create Set of visited nodes
    Set<Vertex<?>> visited = new HashSet<>();
    visited.add(current);
    // output
    System.out.println(current.data);
    // helper
    for (Vertex<?> neighbor : current.neighbors) {
        printVertices(neighbor, visited);
      }
  }

  private static void printVertices(Vertex<?> current, Set<Vertex<?>> visited) {
      if (current == null) return;
      // stop if already visited
      if (visited.contains(current)) return;
      visited.add(current);
      // output
      System.out.println(current.data);
      // recurse
      for (Vertex<?> neighbor : current.neighbors) {
        printVertices(neighbor, visited);
      }
  }

  public static int sum(Vertex<Integer> current) {
    // create Set of visited nodes
    Set<Vertex<Integer>> visited = new HashSet<>();
    // helper + output
    return sum(current, visited);
  }

  private static int sum(Vertex<Integer> current, Set<Vertex<Integer>> visited) {
    // if visited or null
    if (current == null || visited.contains(current)) return 0;
    // add vertex to visited
    visited.add(current);
    // create total to keep track
    int total = current.data;
    // recurse through neighbors
    for (var neighbor : current.neighbors) {
      total += sum(neighbor, visited);
    }
    // output
    return total;
  }

}

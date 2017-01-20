# stanford-algs

## Course 4 - Shortest Paths Revisited, NP-Complete Problems and What To Do About Them

### Assignment 1 - All Pairs Shortest Path - Input File Generator - Java

This is a Java implementation of a test case generator for this assignment.

`StanfordAlgsCourse4Assignment1InputFileGenerator.java` relies on the following API:

```java
public class FloydWarshall {
  /**
   * Creates a FloydWarshall solution for the given graph.
   *
   * @param graph the graph
   * @throws NegativeCycleException throw an exception if the graph contains a negative cycle.
   */
  public FloydWarshall(EdgeWeightedGraph graph) throws NegativeCycleException {
  }

  /**
   * Returns the shortest shortest path in the entire solution.
   *
   * @return the shortest shortest path in the entire solution
   */
  public double shortestShortestPath() {
  }
}


  /**
   * A weighted Edge for use in an undirected graph.
   *
   * @author beaunus
   *
   */
  public static class Edge implements Comparable<Edge> {
    /**
     * Creates a new Edge with the specified vertices and weight.
     *
     * @param v1 vertex 1
     * @param v2 vertex 2
     * @param weight the weight
     */
    public Edge(int v1, int v2, double weight) {
    }

    /**
     * Returns one vertex of this Edge.
     *
     * @return the vertex
     */
    public int vertex1() {
    }

    /**
     * Returns the other vertex of this Edge.
     *
     * @return the other vertex
     */
    public int vertex2() {
    }

    /**
     * Returns the weight of this edge.
     *
     * @return the weight
     */
    public double weight() {
    }

  }

  /**
   * An undirected graph with weighted Edges.
   *
   * @author beaunus
   *
   */
  public static class EdgeWeightedGraph {
    /**
     * Creates a new EdgeWeightedGraph with the specified number of vertices.
     *
     * @param numVertices number of vertices
     */
    public EdgeWeightedGraph(int numVertices) {
    }

    /**
     * Returns the number of vertices in this graph.
     *
     * @return the number of vertices.
     */
    public int numVertices() {
    }

    /**
     * Adds the specified edge to the graph.
     *
     * @param edge edge to be added
     */
    public void addEdge(Edge edge) {
    }

    /**
     * Returns all Edges that belong to this Graph.
     *
     * @return all the Edges
     */
    public Collection<Edge> edges() {
    }
  }
```

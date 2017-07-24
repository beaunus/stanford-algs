# Course 4 - Shortest Paths Revisited, NP-Complete Problems and What To Do About Them

## Assignment 1 - All Pairs Shortest Path

In this assignment you will implement one or more algorithms for the all-pairs shortest-path problem. 

The first line indicates the number of vertices and edges, respectively. Each subsequent line describes an edge (the first two numbers are its tail and head, respectively) and its length (the third number). NOTE: some of the edge lengths are negative. NOTE: These graphs may or may not have negative-cost cycles.

Your task is to compute the "shortest shortest path". Precisely, you must first identify which, if any, of the three graphs have no negative cycles. For each such graph, you should compute all-pairs shortest paths and remember the smallest one (i.e., compute min<sub>u,v∈V</sub>_d(u,v)_, where _d(u,v)_ denotes the shortest-path distance from u to v).

If each of the three graphs has a negative-cost cycle, then enter "NULL" in the box below. If exactly one graph has no negative-cost cycles, then enter the length of its shortest shortest path in the box below. If two or more of the graphs have no negative-cost cycles, then enter the smallest of the lengths of their shortest shortest paths in the box below.

OPTIONAL: You can use whatever algorithm you like to solve this question. If you have extra time, try comparing the performance of different all-pairs shortest-path algorithms!

---

\[number\_of\_vertices\] \[number\_of\_edges\]

\[edge\_vertex\_tail\_1\] \[edge\_vertex\_head\_1\] \[weight\_1\]

\[edge\_vertex\_tail\_2\] \[edge\_vertex\_head\_2\] \[weight\_2\]

...

---

### Path files

For this assignment, there are files that define the vertices on the _shortest path_.  

For example, if the path file reads:

```
[13, 26, 28, 29]
```

It means that the shortest path is:

```
vertex 13 -> vertex 26 -> vertex 28 -> vertex 29
```


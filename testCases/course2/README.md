# Course 2. Graph Search, Shortest Paths, and Data Structures

## Programming Assignment #2

### 1

In this programming problem you'll code up Dijkstra's shortest-path algorithm.

 The file contains an adjacency list representation of an undirected weighted graph with _n_ (_n ≤ 200_) vertices labeled 1 to _n_. Each row consists of the node tuples that are adjacent to that particular vertex along with the length of that edge. For example, the 6th row has 6 as the first entry indicating that this row corresponds to the vertex labeled 6. The next entry of this row "141,8200" indicates that there is an edge between vertex 6 and vertex 141 that has length 8200. The rest of the pairs of this row indicate the other vertices adjacent to vertex 6 and the lengths of the corresponding edges.

 Your task is to run Dijkstra's shortest-path algorithm on this graph, using 1 (the first vertex) as the source vertex, and to compute the shortest-path distances between 1 and every other vertex of the graph. If there is no path between a vertex v and vertex 1, we'll define the shortest-path distance between 1 and v to be 1000000.

 You should report the shortest-path distances to the following ten vertices, in order: 7,37,59,82,99,115,133,165,188,197. You should encode the distances as a comma-separated string of integers. So if you find that all ten of these vertices except 115 are at distance 1000 away from vertex 1 and 115 is 2000 distance away, then your answer should be 1000,1000,1000,1000,1000,2000,1000,1000,1000,1000. Remember the order of reporting DOES MATTER, and the string should be in the same order in which the above ten vertices are given. The string should not contain any spaces.

 IMPLEMENTATION NOTES: This graph is small enough that the straightforward _O(mn)_ time implementation of Dijkstra's algorithm should work fine. OPTIONAL: For those of you seeking an additional challenge, try implementing the heap-based version. Note this requires a heap that supports deletions, and you'll probably need to maintain some kind of mapping between vertices and their positions in the heap.

## Programming assignment #4

### 1

The goal of this problem is to implement a variant of the 2-SUM algorithm covered in this week's lectures.

 The file contains _n_ integers (_n ≤ 1 million_), both positive and negative (there might be some repetitions!).This is your array of integers, with the _i<sup>th</sup>_ row of the file specifying the _i<sup>th</sup>_ entry of the array.

 Your task is to compute the number of target values _t_ in the interval \[-10000,10000\] (inclusive) such that there are distinct numbers _x_,_y_ in the input file that satisfy _x + y = t_. (NOTE: ensuring distinctness requires a one-line addition to the algorithm from lecture.)

 Write your numeric answer (an integer between 0 and 20001) in the space provided.

 OPTIONAL CHALLENGE: If this problem is too easy for you, try implementing your own hash table for it. For example, you could compare performance under the chaining and open addressing approaches to resolving collisions.
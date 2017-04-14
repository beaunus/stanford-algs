# Course 2. Graph Search, Shortest Paths, and Data Structures

## Programming Assignment #1

### 1

The file contains the edges of a directed graph. Vertices are labeled as positive integers from 1 to _n_ (_n ≤ 875714_). Every row indicates an edge, the vertex label in first column is the tail and the vertex label in second column is the head (recall the graph is directed, and the edges are directed from the first column vertex to the second column vertex). So for example, the 11th row looks like : "2 47646". This just means that the vertex with label 2 has an outgoing edge to the vertex with label 47646

 Your task is to code up the algorithm from the video lectures for computing strongly connected components (SCCs), and to run this algorithm on the given graph.

 Output Format: You should output the sizes of the 5 largest SCCs in the given graph, in decreasing order of sizes, separated by commas (avoid any spaces). So if your algorithm computes the sizes of the five largest SCCs to be 500, 400, 300, 200 and 100, then your answer should be "500,400,300,200,100" (without the quotes). If your algorithm finds less than 5 SCCs, then write 0 for the remaining terms. Thus, if your algorithm computes only 3 SCCs whose sizes are 400, 300, and 100, then your answer should be "400,300,100,0,0" (without the quotes). (Note also that your answer should not have any spaces in it.)

 **WARNING**: This is the most challenging programming assignment of the course. Because of the size of the graph you may have to manage memory carefully. The best way to do this depends on your programming language and environment, and we strongly suggest that you exchange tips for doing this on the discussion forums.

## Programming Assignment #2

### 1

In this programming problem you'll code up Dijkstra's shortest-path algorithm.

 The file contains an adjacency list representation of an undirected weighted graph with _n_ (_n ≤ 200_) vertices labeled 1 to _n_. Each row consists of the node tuples that are adjacent to that particular vertex along with the length of that edge. For example, the 6th row has 6 as the first entry indicating that this row corresponds to the vertex labeled 6. The next entry of this row "141,8200" indicates that there is an edge between vertex 6 and vertex 141 that has length 8200. The rest of the pairs of this row indicate the other vertices adjacent to vertex 6 and the lengths of the corresponding edges.

 Your task is to run Dijkstra's shortest-path algorithm on this graph, using 1 (the first vertex) as the source vertex, and to compute the shortest-path distances between 1 and every other vertex of the graph. If there is no path between a vertex v and vertex 1, we'll define the shortest-path distance between 1 and v to be 1000000.

 You should report the shortest-path distances to the following ten vertices, in order: 7,37,59,82,99,115,133,165,188,197. You should encode the distances as a comma-separated string of integers. So if you find that all ten of these vertices except 115 are at distance 1000 away from vertex 1 and 115 is 2000 distance away, then your answer should be 1000,1000,1000,1000,1000,2000,1000,1000,1000,1000. Remember the order of reporting DOES MATTER, and the string should be in the same order in which the above ten vertices are given. The string should not contain any spaces.

 IMPLEMENTATION NOTES: This graph is small enough that the straightforward _O(mn)_ time implementation of Dijkstra's algorithm should work fine. OPTIONAL: For those of you seeking an additional challenge, try implementing the heap-based version. Note this requires a heap that supports deletions, and you'll probably need to maintain some kind of mapping between vertices and their positions in the heap.

## Programming Assignment #3

### 1

The goal of this problem is to implement the "Median Maintenance" algorithm (covered in the Week 3 lecture on heap applications). The text file contains a list of the integers from 1 to _n_ (_n ≤ 10000_) in unsorted order; you should treat this as a stream of numbers, arriving one by one. Letting _x<sub>i</sub>_ denote the _i<sup>th</sup>_ number of the file, the _k<sup>th</sup>_ median _m<sub>k</sub>_ is defined as the median of the numbers _x<sub>1</sub>_,…,_x<sub>k</sub>_. (So, if _k_ is odd, then _m<sub>k</sub>_ is _((k+1)/2)<sup>th</sup>_ smallest number among _x<sub>1</sub>_,…,_x<sub>k</sub>_; if _k_ is even, then _m<sub>k</sub>_ is the _(k/2)<sup>th</sup>_ smallest number among _x<sub>1</sub>_,…,_x<sub>k</sub>_.)

 The answer to this Programming Assignment is the sum of these _n_ medians, modulo 10000 (i.e., only the last 4 digits). That is, you should compute (_m<sub>1</sub>_+_m<sub>2</sub>_+_m<sub>3</sub>_+...+_m<sub>n</sub>_) _mod_ 10000.

 OPTIONAL EXERCISE: Compare the performance achieved by heap-based and search-tree-based implementations of the algorithm.


## Programming assignment #4

### 1

The goal of this problem is to implement a variant of the 2-SUM algorithm covered in this week's lectures.

 The file contains _n_ integers (_n ≤ 1 million_), both positive and negative (there might be some repetitions!).This is your array of integers, with the _i<sup>th</sup>_ row of the file specifying the _i<sup>th</sup>_ entry of the array.

 Your task is to compute the number of target values _t_ in the interval \[-10000,10000\] (inclusive) such that there are distinct numbers _x_,_y_ in the input file that satisfy _x + y = t_. (NOTE: ensuring distinctness requires a one-line addition to the algorithm from lecture.)

 Write your numeric answer (an integer between 0 and 20001) in the space provided.

 OPTIONAL CHALLENGE: If this problem is too easy for you, try implementing your own hash table for it. For example, you could compare performance under the chaining and open addressing approaches to resolving collisions.
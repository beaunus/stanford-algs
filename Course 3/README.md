# Course 3. Greedy Algorithms, Minimum Spanning Trees, and Dynamic Programming

## Programming Assignment #1

### 1

In this programming problem and the next you'll code up the greedy algorithms from lecture for minimizing the weighted sum of completion times.

 The input file describes a set of jobs with positive and integral weights and lengths. It has the format

 \[number_of_jobs\]  
 \[job_1_weight\] \[job_1_length\]  
 \[job_2_weight\] \[job_2_length\]  
 ...

 For example, the third line of the file is "74 59", indicating that the second job has weight 74 and length 59.

 You should NOT assume that edge weights or lengths are distinct.

 Your task in this problem is to run the greedy algorithm that schedules jobs in decreasing order of the difference (weight - length). Recall from lecture that this algorithm is not always optimal. **IMPORTANT**: if two jobs have equal difference (weight - length), you should schedule the job with higher weight first. Beware: if you break ties in a different way, you are likely to get the wrong answer. You should report the sum of weighted completion times of the resulting schedule -- a positive integer --.

### 2

For this problem, use the same data set as in the previous problem.

 Your task now is to run the greedy algorithm that schedules jobs (optimally) in decreasing order of the ratio (weight/length). In this algorithm, it does not matter how you break ties. You should report the sum of weighted completion times of the resulting schedule -- a positive integer --.

### 3

In this programming problem you'll code up Prim's minimum spanning tree algorithm.

 The input file describes an undirected graph with integer edge costs. It has the format

 \[number_of_nodes\] \[number_of_edges\]  
 \[one_node_of_edge_1\] \[other_node_of_edge_1\] \[edge_1_cost\]  
 \[one_node_of_edge_2\] \[other_node_of_edge_2\] \[edge_2_cost\]  
 ...

 For example, the third line of the file is "2 3 -8874", indicating that there is an edge connecting vertex #2 and vertex #3 that has cost -8874.

 You should NOT assume that edge costs are positive, nor should you assume that they are distinct.

 Your task is to run Prim's minimum spanning tree algorithm on this graph. You should report the overall cost of a minimum spanning tree -- an integer, which may or may not be negative --.

 **IMPLEMENTATION NOTES**: This graph is small enough that the straightforward _O(mn)_ time implementation of Prim's algorithm should work fine. OPTIONAL: For those of you seeking an additional challenge, try implementing a heap-based version. The simpler approach, which should already give you a healthy speed-up, is to maintain relevant edges in a heap (with keys = edge costs). The superior approach stores the unprocessed vertices in the heap, as described in lecture. Note this requires a heap that supports deletions, and you'll probably need to maintain some kind of mapping between vertices and their positions in the heap.

## Programming Assignment #3

### 1

In this programming problem and the next you'll code up the greedy algorithm from the lectures on Huffman coding.

 The input file describes an instance of the problem. It has the following format:

 \[number_of_symbols\]  
 \[weight of symbol #1\]  
 \[weight of symbol #2\]  
 ...

 For example, the third line of the file is "6852892," indicating that the weight of the second symbol of the alphabet is 6852892. (We're using weights instead of frequencies, like in the "A More Complex Example" video)

 Your task in this problem is to run the Huffman coding algorithm from lecture on this data set. What is the maximum length of a codeword in the resulting Huffman code?

### 2

Continuing the previous problem, what is the minimum length of a codeword in your Huffman code?

### 3

In this programming problem you'll code up the dynamic programming algorithm for computing a maximum-weight independent set of a path graph.

 The input file describes the weights of the vertices in a path graph (with the weights listed in the order in which vertices appear in the path). It has the following format:

 \[number_of_vertices\]  
 \[weight of first vertex\]  
 \[weight of second vertex\]  
 ...

 For example, the third line of the file is "6395702," indicating that the weight of the second vertex of the graph is 6395702.

 Your task in this problem is to run the dynamic programming algorithm (and the reconstruction procedure) from lecture on this data set. The question is: of the vertices 1, 2, 3, 4, 17, 117, 517, and 997, which ones belong to the maximum-weight independent set? By "vertex 1" we mean the first vertex of the graph -- there is no vertex 0--.

 The output is a 8-bit string, where the _i<sup>th</sup>_ bit should be 1 if the _i<sup>th</sup>_ of these 8 vertices is in the maximum-weight independent set, and 0 otherwise. For example, if you think that the vertices 1, 4, 17, and 517 are in the maximum-weight independent set and the other four vertices are not, then you should enter the string 10011010 in the box below.

 **NOTE**: If the graph doesn't have a node, then it doesn't belong to the maximum-weight independent set, and therefore its bit would be 0. For example, if the size of the graph is 500, then the two last bits correspondning to nodes 517 and 997 would be 0.
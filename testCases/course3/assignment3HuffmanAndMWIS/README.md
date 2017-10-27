# Course 3. Greedy Algorithms, Minimum Spanning Trees, and Dynamic Programming

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

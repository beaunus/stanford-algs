# Course 1. Divide and Conquer, Sorting and Searching, and Randomized Algorithms

## Programming assignment #4

### 1

The file contains the adjacency list representation of a simple undirected graph. There are 200 vertices labeled 1 to 200. The first column in the file represents the vertex label, and the particular row (other entries except the first column) tells all the vertices that the vertex is adjacent to. So for example, the _6<sup>th</sup>_ row looks like : "6	155	56	52	120	......". This just means that the vertex with label 6 is adjacent to (i.e., shares an edge with) the vertices with labels 155,56,52,120,......,etc

 Your task is to code up and run the randomized contraction algorithm for the min cut problem and use it on the above graph to compute the min cut. (HINT: Note that you'll have to figure out an implementation of edge contractions. Initially, you might want to do this naively, creating a new graph from the old every time there's an edge contraction. But you should also think about more efficient implementations.) (WARNING: As per the video lectures, please make sure to run the algorithm many times with different random seeds, and remember the smallest cut that you ever find.)

#### NOTE:
Since the algorithm is not deterministic, there could be errors in the tests with bigger sizes. If your result is smaller than the expected result for an input of size 150 or bigger, then you are probably getting a correct result and the test case should be updated. Please, [let us know](https://github.com/beaunus/stanford-algs/issues/new) if you find a min cut smaller than the one expected by the output.
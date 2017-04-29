# Course 2. Graph Search, Shortest Paths, and Data Structures

## Programming Assignment #3

### 1

The goal of this problem is to implement the "Median Maintenance" algorithm (covered in the Week 3 lecture on heap applications). The text file contains a list of the integers from 1 to _n_ (_n ≤ 10000_) in unsorted order; you should treat this as a stream of numbers, arriving one by one. Letting _x<sub>i</sub>_ denote the _i<sup>th</sup>_ number of the file, the _k<sup>th</sup>_ median _m<sub>k</sub>_ is defined as the median of the numbers _x<sub>1</sub>_,…,_x<sub>k</sub>_. (So, if _k_ is odd, then _m<sub>k</sub>_ is _((k+1)/2)<sup>th</sup>_ smallest number among _x<sub>1</sub>_,…,_x<sub>k</sub>_; if _k_ is even, then _m<sub>k</sub>_ is the _(k/2)<sup>th</sup>_ smallest number among _x<sub>1</sub>_,…,_x<sub>k</sub>_.)

 The answer to this Programming Assignment is the sum of these _n_ medians, modulo 10000 (i.e., only the last 4 digits). That is, you should compute (_m<sub>1</sub>_+_m<sub>2</sub>_+_m<sub>3</sub>_+...+_m<sub>n</sub>_) _mod_ 10000.

 OPTIONAL EXERCISE: Compare the performance achieved by heap-based and search-tree-based implementations of the algorithm.
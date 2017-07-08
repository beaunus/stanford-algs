# stanford-algs

## Course 1. Divide and Conquer, Sorting and Searching, and Randomized Algorithms

### Optional Theory Problems Batch 1 - Question 4

You are given an n by n grid of distinct numbers. 
A number is a _local minimum_ if it is smaller than all of its neighbors. 
(A neighbor of a number is one immediately above, below, to the left, or the right. 
Most numbers have four neighbors; 
numbers on the side have three; 
the four corners have two.) 
Use the divide-and-conquer algorithm design paradigm to compute a local minimum with only O(n) comparisons between pairs of numbers. 
(__Note__: since there are n<super>2</super> numbers in the input, you cannot afford to look at all of them. 
__Hint__: Think about what types of recurrences would give you the desired upper bound.)
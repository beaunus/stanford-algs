# Course 4. Shortest Paths Revisited, NP-Complete Problems and What To Do About Them

## Programming Assignment #1

_**EDIT**: The original assignment asks for computing the shortest all-pairs shortest path of 3 graphs. Meaning that you have to compute the all-pairs shortest path of each graph, and then submit the shortest. For the test cases it makes sense to compute the all-pairs shortest path of just one graph._

In this assignment you will implement one or more algorithms for the all-pairs shortest-path problem.

The first line indicates the number of vertices and edges, respectively. Each subsequent line describes an edge (the first two numbers are its tail and head, respectively) and its length (the third number). NOTE: some of the edge lengths are negative. NOTE: These graphs may or may not have negative-cost cycles.

Your task is to compute the "shortest path". Precisely, you must first identify if the graph doesn't have negative cycles. For each such graph, you should compute all-pairs shortest paths and remember the smallest one (i.e., compute _min<sub>u,v ∈ V</sub>d(u,v)_, where _d(u,v)_ denotes the shortest-path distance from _u_ to _v_).

If the graph has a negative-cost cycle, then submit "NULL" as output. Else submit the length of its shortest path in the box below.

OPTIONAL: You can use whatever algorithm you like to solve this question. If you have extra time, try comparing the performance of different all-pairs shortest-path algorithms!

## Programming Assignment #2

In this assignment you will implement one or more algorithms for the traveling salesman problem, such as the dynamic programming algorithm covered in the video lectures.

The first line indicates the number of cities. Each city is a point in the plane, and each subsequent line indicates the x- and y-coordinates of a single city.

The distance between two cities is defined as the Euclidean distance --- that is, two cities at locations (_x_,_y_) and (_z_,_w_) have distance _sqrt((x−z)<sup>2</sup>+(y−w)<sup>2</sup>_) between them.

Submit the minimum cost of a traveling salesman tour for this instance, _rounded down to the nearest integer_.

OPTIONAL: If you want bigger data sets to play with, check out the TSP instances from around the world [here](http://www.math.uwaterloo.ca/tsp/world/countries.html). The smallest data set (Western Sahara) has 29 cities, and most of the data sets are much bigger than that. What's the largest of these data sets that you're able to solve --- using dynamic programming or, if you like, a completely different method?

HINT: You might experiment with ways to reduce the data set size. For example, trying plotting the points. Can you infer any structure of the optimal solution? Can you use that structure to speed up your algorithm?

## Programming Assignment #3
In this assignment we will revisit an old friend, the traveling salesman problem (TSP). This week you will implement a heuristic for the TSP, rather than an exact algorithm, and as a result will be able to handle much larger problem sizes. Here is a data file describing a TSP instance (original source: [http://www.math.uwaterloo.ca/tsp/world/bm33708.tsp](http://www.math.uwaterloo.ca/tsp/world/bm33708.tsp)).

The first line indicates the number of cities. Each city is a point in the plane, and each subsequent line indicates the _x_ and _y_-coordinates of a single city.

The distance between two cities is defined as the Euclidean distance --- that is, two cities at locations (_x_,_y_) and (_z_,_w_) have distance _sqrt((x−z)<sup>2</sup>+(y−w)<sup>2</sup>_) between them.

You should implement the _nearest neighbor_ heuristic:

 1. Start the tour at the first city.
 2. Repeatedly visit the closest city that the tour hasn't visited yet. _In case of a tie, go to the closest city with the lowest index_. For example, if both the third and fifth cities have the same distance from the first city (and are closer than any other city), then the tour should begin by going from the first city to the third city.
 3. Once every city has been visited exactly once, return to the first city to complete the tour.

Submit the cost of the traveling salesman tour computed by the nearest neighbor heuristic for this instance, _rounded down to the nearest integer_.

[Hint: when constructing the tour, you might find it simpler to work with squared Euclidean distances (i.e., the formula above but without the square root) than Euclidean distances. But don't forget to report the length of the tour in terms of standard Euclidean distance.]

## Programming assignment #4

_**EDIT**: The original assignment asks for computing the 2SAT problem in 6 different instances. Then you had to check the satisfiability of each of them. For the test cases it makes sense to compute the 2SAT problem of just one instance._

In this assignment you will implement one or more algorithms for the 2SAT problem.

The file format is as follows. The number of variables and the number of clauses is the same, and this number is specified on the first line of the file. Each subsequent line specifies a clause via its two literals, with a number denoting the variable and a "-" sign denoting logical "not". For example, the second line of the first data file is "-16808 75250", which indicates the clause ¬_x_<sub>16808</sub> <sup>V</sup> _x_<sub>75250</sub>.

Your task is to determine if the instances is satisfiable or not. submit a 1 if the instance is satisfiable, and 0 otherwise.

DISCUSSION: This assignment is deliberately open-ended, and you can implement whichever 2SAT algorithm you want. For example, 2SAT reduces to computing the strongly connected components of a suitable graph (with two vertices per variable and two directed edges per clause, you should think through the details). This might be an especially attractive option for those of you who coded up an SCC algorithm in Part 2 of this specialization. Alternatively, you can use Papadimitriou's randomized local search algorithm. (The algorithm from lecture is probably too slow as stated, so you might want to make one or more simple modifications to it --- even if this means breaking the analysis given in lecture --- to ensure that it runs in a reasonable amount of time.) A third approach is via backtracking. In lecture we mentioned this approach only in passing; see Chapter 9 of the Dasgupta-Papadimitriou-Vazirani book, for example, for more details.
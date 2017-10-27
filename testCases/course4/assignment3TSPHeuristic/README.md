# Course 4. Shortest Paths Revisited, NP-Complete Problems and What To Do About Them

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

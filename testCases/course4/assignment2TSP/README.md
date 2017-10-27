# Course 4. Shortest Paths Revisited, NP-Complete Problems and What To Do About Them

## Programming Assignment #2

In this assignment you will implement one or more algorithms for the traveling salesman problem, such as the dynamic programming algorithm covered in the video lectures.

The first line indicates the number of cities. Each city is a point in the plane, and each subsequent line indicates the x- and y-coordinates of a single city.

The distance between two cities is defined as the Euclidean distance --- that is, two cities at locations (_x_,_y_) and (_z_,_w_) have distance _sqrt((x−z)<sup>2</sup>+(y−w)<sup>2</sup>_) between them.

Submit the minimum cost of a traveling salesman tour for this instance, _rounded down to the nearest integer_.

OPTIONAL: If you want bigger data sets to play with, check out the TSP instances from around the world [here](http://www.math.uwaterloo.ca/tsp/world/countries.html). The smallest data set (Western Sahara) has 29 cities, and most of the data sets are much bigger than that. What's the largest of these data sets that you're able to solve --- using dynamic programming or, if you like, a completely different method?

HINT: You might experiment with ways to reduce the data set size. For example, trying plotting the points. Can you infer any structure of the optimal solution? Can you use that structure to speed up your algorithm?

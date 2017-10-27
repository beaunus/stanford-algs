# Course 4. Shortest Paths Revisited, NP-Complete Problems and What To Do About Them

## Programming assignment #4

_**EDIT**: The original assignment asks for computing the 2SAT problem in 6 different instances. Then you had to check the satisfiability of each of them. For the test cases it makes sense to compute the 2SAT problem of just one instance._

In this assignment you will implement one or more algorithms for the 2SAT problem.

The file format is as follows. The number of variables and the number of clauses is the same, and this number is specified on the first line of the file. Each subsequent line specifies a clause via its two literals, with a number denoting the variable and a "-" sign denoting logical "not". For example, the second line of the first data file is "-16808 75250", which indicates the clause ¬_x_<sub>16808</sub> <sup>V</sup> _x_<sub>75250</sub>.

Your task is to determine if the instances is satisfiable or not. submit a 1 if the instance is satisfiable, and 0 otherwise.

DISCUSSION: This assignment is deliberately open-ended, and you can implement whichever 2SAT algorithm you want. For example, 2SAT reduces to computing the strongly connected components of a suitable graph (with two vertices per variable and two directed edges per clause, you should think through the details). This might be an especially attractive option for those of you who coded up an SCC algorithm in Part 2 of this specialization. Alternatively, you can use Papadimitriou's randomized local search algorithm. (The algorithm from lecture is probably too slow as stated, so you might want to make one or more simple modifications to it --- even if this means breaking the analysis given in lecture --- to ensure that it runs in a reasonable amount of time.) A third approach is via backtracking. In lecture we mentioned this approach only in passing; see Chapter 9 of the Dasgupta-Papadimitriou-Vazirani book, for example, for more details.

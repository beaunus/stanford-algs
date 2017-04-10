# stanford-algs Testers

*Testers* are used to automate testing your code's output with the expected 
output of the test cases.

The *Tester* will proceed as follows:

1. For each of the files in the [array of input files to verify]
  1. Run [your code] with the current *single file* as the *first argument*.
  1. Compare [your code]'s output with the expected output.
    1. Indicate whether or not there is a match.

## General considerations

Since your *solution* code should be isolated from the test case repository, 
you have to pay special attention to paths when you run the Testers.

## Java

### Prerequisites 

The Tester has a critical requirement for your solution code:
* You *must* define a class whose *main* method accepts a single argument 
(the input file name) and outputs a single line (to **_System.out_**) for each 
of the assignment's submission boxes.

For example, if the solution to a problem is 43, your solution code should 
work something like this:

```shell
java -cp path/to/your/classes MySolutionClass path/to/input/file.txt
43
```
If, for example, there are multiple submission boxes for the same input file, 
each submission box value should be on a new line.
```shell
java -cp path/to/your/classes MySolutionClass path/to/input/file.txt
43
21
5
```

This being said, you *may* output additional text through **_System.err_** if 
you wish.  For many assignments, it's useful to use **_System.err_** for 
outputting debugging information.

### Using the Tester

It is important to keep your solution code separate from the test case 
repository. However, using the Tester allows for this.

* Compile your solution code in your *private* folder. (Do not move solution 
code to this repository)
* Compile the Tester code.

At this point, your solution classes will be in a separate folder from the 
Tester class.

Running the Tester requires only a single command.  It's long, but you'll get 
the hang of it.

```
java -cp path/to/solution/classes:path/to/Tester/class Tester [your solution class name] {list of test case input files}
```

You can also use "*" characters to let the shell do most of the work. 
For example, imagine this:
* Your solution classes are in ```~/stanford-algs/build/classes``` 
* The Tester class is in ```~/git/stanford-algs/build/classes```
* You want to test your class ```Course4Assignment1```
* The test case input files are in ```~/git/stanford-algs/Course4/Assignment1```

You can run the following command:

```
java -cp ~/stanford-algs/build/classes:~/git/stanford-algs/build/classes Tester Course4Assignment1 ~/git/stanford-algs/Course4/Assignment1/input*.txt
```

The system will run your *Course4Assignment1* class on all of the input files 
in the specified folder.  
* If they match, you will see ```Your result matches the test case.```. 
* If they don't match, you will see ```Your result DOES NOT matches the test case. Press "ENTER" to continue...```.
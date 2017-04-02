# stanford-algs Testers

*Testers* are used to automate testing your code's output with the expected 
output of the test cases.

Since the internal workings of programming languages vary, each language's 
*Testers* have different syntax.

The general syntax for usage is as follows:

```
Tester [location of your code] [array of input files to verify]
```

The *Tester* will proceed as follows:

1. For each of the files in the [array of input files to verify]
  1. Run [your code] with the current *single file* as the *first argument*.
  1. Compare [your code]'s output with the expected output.
    1. Indicate whether or not there is a match.

## General considerations

Since your *solution* code should be isolated from the test case repository, 
you have to pay special attention to paths when you run the Testers.
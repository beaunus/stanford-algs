# stanford-algs Testers

_Testers_ are used to automate testing your code's output with the expected output of the test cases.

The _Tester_ will proceed as follows:

1. For each of the files in the [array of input files to verify]

  1. Run [your code] with the current _single file_ as the _first argument_.
  2. Compare [your code]'s output with the expected output.

    1. Indicate whether or not there is a match.

## General considerations

Since your _solution_ code should be isolated from the test case repository, you have to pay special attention to paths when you run the Testers.

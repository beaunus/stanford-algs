# stanford-algs Shell Tester

Be sure to check out the [Using Test Cases Wiki](https://github.com/beaunus/stanford-algs/wiki/Using-Test-Cases) before proceeding.

The `shell` tester is used for testing programs written in _any programming language_.

## Prerequisites

As usual, your program needs to output a new line for each of the submission boxes in the assignment. Your program needs to accept a single argument that defines the file that should be used as input to the program.

For example, if the solution to a problem is 43, your solution code should work something like this:

```shell
<command to run your program> path/to/input/file.txt
43
```

If, for example, there are multiple submission boxes for the same input file, each submission box value should be on a new line.

```shell
<command to run your program> path/to/input/file.txt
43
21
5
```

## Using the Tester

Running the Tester requires only a single command. It's long, but you'll get the hang of it.

```
path/to/tester.sh -c "<command to run your program>" <list of test case input files>
```

You can also use "*" characters to let the shell do most of the work. For example, imagine this:

- You want to test your program `course4assignment1`
- The test case input files are in `~/git/stanford-algs/course4/assignment1`

You can run the following command:

```
path/to/tester.sh -c "path/to/course4assignment1" ~/git/stanford-algs/course4/assignment1/input*.txt
```

The system will run your _course4assignment1_ program on all of the input files in the specified folder.

### A Breakdown of how to get started

Since the solution program code is _isolated_ from the test cases, the paths can be long.

In this example, imagine the following situation:

- Your **compiled binary** is `~/my-stanford-algs/course1assignment1`
- The **test cases** are in `~/git/stanford-algs/testCases/course1/assignment1Multiplication`
- The **tester** is in `~/git/stanford-algs/tester/shell/tester.sh`

Here's an example of a basic workflow using a program written in C++, or another language that generates a __compiled binary__ file.

Make sure you can run the __compiled binary__. The __compiled binary__ takes one argument, the filename for the test case.

```shell
~$ ~/my-stanford-algs/course1assignment1 ~/git/stanford-algs/testCases/course1/assignment1Multiplication/input_dgrcode_14_8.txt
1268084190907280
~$
```

Make sure you can run the __tester__. Without any options or arguments, you will get an error message.

```shell
~$ ~/git/stanford-algs/tester/shell/tester.sh
You must specify a command with the '-c' option.
~$
```

Run the tester with the proper syntax. `<path to tester.sh> -c "<command to run your program>" <list of files to test>` Notice the quotation marks around the command. The quotation marks allow for commands that include spaces, for example `"node mySolution.js"`.

```shell
~$ ~/git/stanford-algs/tester/shell/tester.sh -c "~/my-stanford-algs/course1assignment1" ~/git/stanford-algs/testCases/course1/assignment1Multiplication/input*
Testing your code's output with the expected output.

/Users/beaunus/git/stanford-algs/testCases/course1/assignment1Multiplication/input_dgrcode_01_1.txt    ✔
/Users/beaunus/git/stanford-algs/testCases/course1/assignment1Multiplication/input_dgrcode_02_1.txt    ✔
/Users/beaunus/git/stanford-algs/testCases/course1/assignment1Multiplication/input_dgrcode_03_1.txt    ✔
/Users/beaunus/git/stanford-algs/testCases/course1/assignment1Multiplication/input_dgrcode_04_1.txt    ✔
/Users/beaunus/git/stanford-algs/testCases/course1/assignment1Multiplication/input_dgrcode_05_2.txt    ✔
/Users/beaunus/git/stanford-algs/testCases/course1/assignment1Multiplication/input_dgrcode_06_2.txt    ✔
/Users/beaunus/git/stanford-algs/testCases/course1/assignment1Multiplication/input_dgrcode_07_2.txt    ✔
/Users/beaunus/git/stanford-algs/testCases/course1/assignment1Multiplication/input_dgrcode_08_2.txt    ✔
/Users/beaunus/git/stanford-algs/testCases/course1/assignment1Multiplication/input_dgrcode_09_4.txt    ✔
/Users/beaunus/git/stanford-algs/testCases/course1/assignment1Multiplication/input_dgrcode_10_4.txt    ✔
/Users/beaunus/git/stanford-algs/testCases/course1/assignment1Multiplication/input_dgrcode_11_4.txt    ✔
/Users/beaunus/git/stanford-algs/testCases/course1/assignment1Multiplication/input_dgrcode_12_4.txt    ✔
/Users/beaunus/git/stanford-algs/testCases/course1/assignment1Multiplication/input_dgrcode_13_8.txt    ✔
/Users/beaunus/git/stanford-algs/testCases/course1/assignment1Multiplication/input_dgrcode_14_8.txt    ✔
/Users/beaunus/git/stanford-algs/testCases/course1/assignment1Multiplication/input_dgrcode_15_8.txt    ✔
/Users/beaunus/git/stanford-algs/testCases/course1/assignment1Multiplication/input_dgrcode_16_8.txt    ✔
/Users/beaunus/git/stanford-algs/testCases/course1/assignment1Multiplication/input_dgrcode_17_16.txt    ✔
/Users/beaunus/git/stanford-algs/testCases/course1/assignment1Multiplication/input_dgrcode_18_16.txt    ✔
/Users/beaunus/git/stanford-algs/testCases/course1/assignment1Multiplication/input_dgrcode_19_16.txt    ✔
/Users/beaunus/git/stanford-algs/testCases/course1/assignment1Multiplication/input_dgrcode_20_16.txt    ✔
/Users/beaunus/git/stanford-algs/testCases/course1/assignment1Multiplication/input_dgrcode_21_32.txt    ✔
/Users/beaunus/git/stanford-algs/testCases/course1/assignment1Multiplication/input_dgrcode_22_32.txt    ✔
/Users/beaunus/git/stanford-algs/testCases/course1/assignment1Multiplication/input_dgrcode_23_32.txt    ✔
/Users/beaunus/git/stanford-algs/testCases/course1/assignment1Multiplication/input_dgrcode_24_32.txt    ✔
/Users/beaunus/git/stanford-algs/testCases/course1/assignment1Multiplication/input_dgrcode_25_64.txt    ✔
/Users/beaunus/git/stanford-algs/testCases/course1/assignment1Multiplication/input_dgrcode_26_64.txt    ✔
/Users/beaunus/git/stanford-algs/testCases/course1/assignment1Multiplication/input_dgrcode_27_64.txt    ✔
/Users/beaunus/git/stanford-algs/testCases/course1/assignment1Multiplication/input_dgrcode_28_64.txt    ✔
/Users/beaunus/git/stanford-algs/testCases/course1/assignment1Multiplication/input_dgrcode_29_128.txt    ✔
/Users/beaunus/git/stanford-algs/testCases/course1/assignment1Multiplication/input_dgrcode_30_128.txt    ✔
/Users/beaunus/git/stanford-algs/testCases/course1/assignment1Multiplication/input_dgrcode_31_128.txt    ✔
/Users/beaunus/git/stanford-algs/testCases/course1/assignment1Multiplication/input_dgrcode_32_128.txt    ✔

✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔
All tests passed
✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔
~$
```

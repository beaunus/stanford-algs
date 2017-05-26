# stanford-algs Python Tester

Be sure to check out the [Using Test Cases Wiki](https://github.com/beaunus/stanford-algs/wiki/Using-Test-Cases) before proceeding.

**NOTE**: The tester has been only tested with Linux. If you're having problems
with other operating systems, please
[open an issue](https://github.com/beaunus/stanford-algs/issues/new).

## Prerequisites

The Tester has a critical requirement for your solution code:
* You *must* define a function which accepts a single argument (the input file
path) and returns a list with the values for each solution box. If the
assignment has only one solution box, you can either return the single value,
or an array with just one item.

For example, if the assignment has 2 solution boxes and the correct result is
'first' for the first box and 'second' for the second box, the function you
define should return the list `['first', 'second']`. For an assignment with
'solution' as its correct result, you could either return `solution`, or
`[solution]`.

If you want to have a different file for each submission box, you can easily
create a new file that imports each subproblem and acts as a wrapper. And if
you want to check just 1 subproblem, you can return an arbitrary value for the
rest of subproblem, and just check the one you're interested about.


## Using the Tester

Running the Tester requires only a single command.  It's long, but you'll get
the hang of it.

```shell
python3 tester.py path/to/your/algorithm.py path/to/testcases [options]
```

We'll break down the command:

* `python3`: this is to call python v3, in case you had also v2 installed.
Use the command that would call Python v3
* `tester.py`: execute the tester
* `path/to/your/algorithm.py`: this is the path to the file where you have
your solution code. By default the tester will try to import the method named
`alg` from that file. You can specify a custom name with the options.
* `path/to/testcases`: this is the path to the folder where you have the test
cases
* `options`: so far there are three possible options (feel free to contribute!)
	* `name=METHOD_NAME`: use this option if the method is named different than
	`alg`
	* `max_size=SIZE`: use this option if you don't want to run files with input
	bigger than `SIZE`. This is useful if you're looking for a bug and the
	algorithm takes too long for large inputs.
	* `only=FILES`: use this option to run only specific tests. The value to
	send is a comma-separated-value list of the _references_ of the files you
	want to test. The reference of a file is the name of the file, without
	'input_' and '.txt'. When there are failed tests, you'll get the
	comma-separated-value list of the references that failed, so it will be easy
	to re-run only those failed tests. See the tester output if you're not sure
	how to send the comma-separated-value list of references.

Here's an example using all the options. Imagine you have the file
'Dijkstra.py' in the folder './course/assignment/', there you have the method
`shortest_paths` that runs Dijksra's algorithm and returns the solution you
are looking for. You have all your tests cases for that algorithm in the
folder './course/tests/dijkstra', but you don't want to run the tests with
inputs larger than 100000. Additionally, you'll only want to run the files
with references `random_22_100` and `random_23_100`. In that case, you would
write

```shell
python3 tester.py ./course/assignment/Dijkstra.py ./course/tests/dijkstra name=shortest_paths max_size=100000 only=random_22_100,random_23_100
```

## Tester output

The tester will give you some feedback before running the tests, to let you
know the options that it has received. It will print:

```shell
Testing algorithm at "PATH/TO/ALGORITHM"
Options:
	name = NAME
	max_size = SIZE
	only =
		fileref_1
		fileref_2
		...
```

Then, it will print a line for each skipped test with the reason of skipping,
so you can check that the skipping went as expected.

```shell
skipped fileref_i 	-> exceeded max_size
skipped fileref_j 	-> not in only
```

After that, it will print a quick summary of the test as they are run. The
summary has the reference to the file, and shows the expected and the computed
results in case they don't match. You'll see "✔" in case a submission was
correct or "✘" otherwise. For example, you'll see something like this:

```shell
dgrcode_02_1	✔✘
    Answer 2:
	Expected: good
	Result: bad
dgrcode_01_1	✔✔
```

Finally, you'll get the result after running all the tests. In case all passed
you'll see:
```shell
✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔
All tests passed
✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔
```
And in case something failed, you'll see:
```text
✘✘✘✘✘✘✘✘✘✘✘✘✘✘✘✘✘✘✘✘✘✘
Some tests didn't pass
✘✘✘✘✘✘✘✘✘✘✘✘✘✘✘✘✘✘✘✘✘✘
Failed tests: random_22_100,random_23_100
```

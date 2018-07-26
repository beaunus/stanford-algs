# stanford-algs PHP Tester

Be sure to check out the [Using Test Cases Wiki](https://github.com/beaunus/stanford-algs/wiki/Using-Test-Cases) before proceeding.

**NOTE**: The tester has been only tested with Linux. If you're having problems
with other operating systems, please
[open an issue](https://github.com/beaunus/stanford-algs/issues/new).

## Prerequisites

The Tester has a critical requirement for your solution code:
* You *must* create a php file accepts one argument (full path to the test case) and outputs either one or multiple lines
 depending on how many tasks are required to resolve.

For example, if the solution to a problem is 43, your solution code should
work something like this:

```shell
php /path/to/file/with/solution.php path/to/input/file.txt
43
```
If, for example, there are multiple submission boxes for the same input file,
each submission box value should be on a new line.
```shell
php /path/to/file/with/solution.php path/to/input/file.txt
43
21
5
```

## Using the Tester

Running the Tester requires only a single command. It accepts only two options and both are required:
* _s_ - full path to the file with solution;
* _t_ - has to be a string - path to the test case or pattern. Keep in mind that this pattern is used by [glob](http://php.net/manual/en/function.glob.php)
function hence template you input must conform to _pattern_ argument of _glob_.

```shell
php tester.php -s /var/www/stanford_algs/solutions/course3/week1/schedule_job_wl.php -t '/var/www/stanford-algs/testCases/course3/assignment1SchedulingAndMST/questions1And2/*'
```

## Tester output

The tester gives you some feedback on running all of the test cases:
```shell
 ✔.../input_random_5_20.txt ✔  //test passed
 ✖.../input_random_6_20.txt: Test failed ✖ //test didn't pass
 ✔.../input_random_7_20.txt ✔ 
 ✖.../input_random_8_265.txt: No respective output file for the testcase /var/www/stanford-algs/testCases/course3/assignment1SchedulingAndMST/questions1And2/input_random_8_265.txt ✖ // something wrong with output file, it might not exist
 ✔.../input_random_9_40.txt ✔ 
 ✔.../input_random_10_40.txt ✔ 
```
Output isn't enough verbose so that you will have to look at failed test cases directly to find out what was wrong.
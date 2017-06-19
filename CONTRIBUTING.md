# Contributing to stanford-algs

Thank you for your interest in helping with our mission.

If this is your first time contributing to a GitHub project, fear not.
When this repository was started, it was one of the first collaborations done
by the contributors.  Using GitHub is really quite easy.
You don't need to worry about making mistakes or breaking anything.

The collaborators of this repository are very patient and willing to help you.
After all, we want *you* to help us.

## Familiarize yourself with GitHub

GitHub has quite an extensive library of training materials.
They are all very helpful and easy to understand.

https://guides.github.com/

## Getting started

If you want to help make some test cases for the students of stanford-algs,
here's what you should do:

1. [Create a GitHub account](https://github.com/join).
1. [Fork this repository](https://guides.github.com/activities/forking/).
1. [Clone your fork](https://guides.github.com/activities/forking/#clone)
to your local machine.
1. [Look at the issues](https://github.com/beaunus/stanford-algs/issues) for
this repository. Choose an assignment that needs test cases.
1. In your local repository, make a branch for your new unconfirmed test files.
For example, make a branch called "unconfirmedTestCases3.2"
if you are working on Course 3 Assignment 2.
1. Make some test case files. **Note** Please use the standard
[naming convention](https://github.com/beaunus/stanford-algs#file-names).
1. Commit your new files to your *local repository*.
1. Push your *local branch* to your *GitHub repository*.
1. [Make a Pull Request](https://guides.github.com/activities/forking/#making-a-pull-request)
from your GitHub repository dashboard.

At that point, here's what will happen:
1. Our team will review your files to make sure that they
*don't violate the Coursera Honor Code*.
If they do, we'll just let you know. You can make adjustments and re-submit.
1. We will *pull your files* to our repository.
1. Once the files have been confirmed by *multiple users*, they will be merged
with the master branch.

## Contributing test cases only

If you want to contribute test cases:

1. On your local repository, create a branch called "unconfirmedTestCasesX.x"
1. Use the file naming conventions on the
[Project page](https://github.com/beaunus/stanford-algs).
1. Put your test cases in the appropriate folder.  For example
```testCases/courseX/assignmentX```.
1. Commit your files and push your test cases to your GitHub account.
1. Submit a pull request for your branch.

## Confirming test cases

If you want to confirm the correctness of test cases:

1. Look at the *issues* on the GitHub page for test cases that need to be confirmed.
1. On your local repository, checkout the branch that you want to confirm.  Unconfirmed branches are called "unconfirmedTestCasesX.x"
1. Run a ```tester``` for the test cases in the unconfirmed branch.
1. If you get the same results, make a comment on the relevant issue.  A contributor will move the newly confirmed test cases to the master branch.
1. If you get different results, make a comment on the relevant issue.  Be sure to specify the files that have a discrepancy.

## Contributing testers

Since different languages have different features, testers are likely to be
very different from each other.

Have a look at the ```tester``` folder and try your best to do something
similar to the existing testers.

If you add a tester for a new language, please create a suitable README.md file
for that tester, so that others can easily understand your thinking.

## Getting help

If you would like to know more about our project, feel free to contact one of
the
[contributors](https://github.com/beaunus/stanford-algs/graphs/contributors).
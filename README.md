# stanford-algs

## Overview

This repository contains some example test case files for Stanford's Coursera
specialization 
[_Learn To Think Like A Computer Scientist_](https://www.coursera.org/specializations/algorithms)

## Intention

These files are intended to be used as a supplement to the above course.

In order to comply with the 
[Coursera Honor Code](https://www.google.com/search?q=Coursera+Honor+Code),
please do not share any solutions to the _actual_ assignments from the course.

## Test Case Files

Each test case contains two files:
<dl>
  <dt>input file</dt>
  <dd>
    The file that your program should *read* in order to compute the result.
  </dd>
  <dt>output file</dt>
  <dd>
    The *exact* output that your program should *produce*.  This is the value 
    that would be entered into the result box on the assignment submission 
    page. 
  </dd>
  <dd>
    If the assignment has multiple submission boxes for the same input, each 
    box's value will be on a new line.  
  </dd>
</dl>

### File Names

File names contain the following tokens, each separated by a single "_" 
character.
* [input/output]
* [contributor]
* [index] (arbitrary)
* [problem size]

For example, the file ```input_beaunus_1_2.txt``` is *beaunus*'s *input* file 
for a *problem size* of 2.  The file ```output_beaunus_1_2.txt``` is the 
corresponding *output* file for that input.  

Similarly, the files ```input_badmf_35_8192.txt``` and 
```output_badmf_35_8192.txt``` are corresponding files for *badmf*'s test 
cases of *problem size* 8192.

Why is the *contributor*'s name included?
* Individual contributors have control over the indices in their files.
* Developers can easily batch delete and create their own files with shell 
commands like ```rm *myname*.txt```.  Other contributor's files are untouched.
* Contributors can easily modify the README.md for a particular assignment if 
their files require explanation.  For example, a contributor may want to state 
which *edge cases* their files cover, if any.

## Contributing

If you are interested in contributing, please read our 
[CONTRIBUTING](CONTRIBUTING.md) guide.

## Disclaimer

The programming assignments themselves are intellectual property of the
Coursera course.  The Coursera community has given permission to re-publish 
the assignments.  

Any infringement on intellectual property rights is accidental.  If you feel 
that this repository is out of line, please let us know and we will do our 
best to comply with your request.
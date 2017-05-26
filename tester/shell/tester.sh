#!/bin/sh
#
# Run the specified test cases for the specified command.

function display_usage {
  echo "usage: `basename $0` -c <command to run your program> <list of files to test>"
}

# Check to see if help is requested
if [[ "$1" == "-help" || "$1" == "-h" ]]
then
  display_usage
  exit 0
fi

# Parse command-line options
while getopts ":c:h" opt
do
  case $opt in
    c)
      command=$OPTARG
      shift 2
      ;;
    h)
      display_usage
      exit 0
      ;;
    \?)
      echo "Invalid option: -$OPTARG" >&2
      display_usage
      ;;
  esac
done

# Insist on setting -c option
if [[ $command == "" ]]
then
  echo "You must specify a command with the '-c' option." >&2
  display_usage
  exit 1
fi

# Sort the filenames by index number.
# Use "_" as a field delimiter.
sort_command="sort -b -t_ "

# Since paths may include "_" characters, this is a hack to ensure that the
# filenames are sorted by all fields.
for i in $(seq 99)
do
  sort_command+="-"
  sort_command+=k$i
  sort_command+="n "
done
# Sort
filenames=$(echo "$@" | tr " " "\n" | $sort_command)

# Perform the tests.
printf "Testing your code's output with the expected output.\n\n"

# Maintain an array_of_failed_tests that will be summarized later.
array_of_failed_tests=()

# Compare result with expected_result for all given test cases.
for filename in $filenames
do
  # A hack to replace the last occurence of "input" with "output"
  reversed_filename=$(echo "$filename" | rev)
  output_filename=$(echo ${reversed_filename/tupni/tuptuo} | rev)

  # Determine the result and expected_result, with leading and trailing
  # whitespace trimmed.
  expected_result="$(cat $output_filename | tr "\r" "\n" | xargs)"
  result=$(eval $command $filename | xargs)

  # Compare expected_result to result and print details.
  if [[ $expected_result == $result ]]
  then
    printf "\033[32m"
    printf "$filename\t"
    printf "✔\n"
    printf "\033[0m"
  else
    printf "\033[31m"
    printf "$filename\t"
    printf "✘\n"
    printf "\tExpected: $expected_result\n"
    printf "\t  Result: $result\n"
    printf "\033[0m"
    array_of_failed_tests=("$array_of_failed_tests $filename")
  fi
done
printf "\n"

# Print the summary
if [[ ${#array_of_failed_tests[@]} == 0 ]]
then
  printf "\033[32m"
  printf "✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔\n"
  printf "All tests passed\n"
  printf "✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔✔\n"
  printf "\033[0m"
else
  printf "\033[31m"
  printf "✘✘✘✘✘✘✘✘✘✘✘✘✘✘✘✘✘✘✘✘✘✘✘✘✘✘✘✘✘✘✘\n"
  printf "Some tests didn't pass\n"
  printf "✘✘✘✘✘✘✘✘✘✘✘✘✘✘✘✘✘✘✘✘✘✘✘✘✘✘✘✘✘✘✘\n"
  printf "\nFailed tests:\n"

  for failed_test in $array_of_failed_tests
  do
    printf "\t$failed_test\n"
  done
  printf "\033[0m"
fi

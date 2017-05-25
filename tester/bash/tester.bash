#!/bin/bash
#
# Run the specified test cases for the specified command.

function display_usage {
  echo "usage: `basename $0` [somestuff]"
}

# check to see if help is requested
if [[ "$1" == "-help" || "$1" == "-h" ]]
then
  display_usage
  exit 0
fi

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
      ;;
  esac
done

if [[ $command == "" ]]
then
  echo "You must specify a command with the '-c' option." >&2
  exit 1
fi

# A hack to ensure that the files will be sorted numerically,
# instead of the default "10" before "1" behavior
sort_command="sort -b -t_ "

for i in $(seq 99)
do
  sort_command+="-"
  sort_command+=k$i
  sort_command+="n "
done

filenames=$(echo "$@" | tr " " "\n" | $sort_command)

for filename in $filenames
do
  reversed_filename=$(echo "$filename" | rev)
  output_filename=$(echo ${reversed_filename/tupni/tuptuo} | rev)
  echo "input file => $filename"
  echo "output file => $output_filename"
  diff $output_filename <(eval $command $filename)
done

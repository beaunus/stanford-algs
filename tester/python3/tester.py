import sys
import os
import importlib

def test(student_algorithm, test_cases_folder, name='alg', max_size=-1, only=[]):
	# import the algorithm
	*alg_path, alg_file = student_algorithm.split('/')
	sys.path.append('/'.join(alg_path))
	alg = importlib.import_module(alg_file[:-3])
	alg = getattr(alg, name)

	# print the options to the user
	print('Testing algorithm at "'+ student_algorithm + '".')
	if name != 'alg' or max_size != -1 or only != []:
		print('Options: ')
		if name != 'alg': print('\tname =', name)
		if max_size != -1: print('\tmax_size =', max_size)
		if len(only) > 0:
			print('\tonly =')
			for only_file in only:
				print('\t\t' + only_file)

	# read the test cases titles
	test_cases = []
	for filename in os.listdir(test_cases_folder):
		if filename[:5] != 'input':
			continue

		# get all input files
		file_info = filename[:-4].split('_')
		# First three parts are type, user, and number
		file_type, file_user, file_num = file_info[:3]
		# Rest parts are input sizes
		file_size = 1
		for size in file_info[3:]:
			file_size *= int(size)

		fileref = file_info[1:]
		fileref_str = '_'.join([str(x) for x in fileref])

		# filter 'only' files
		if len(only)>0 and fileref_str not in only:
			print('skipped', fileref_str, '\t-> not in only')
			continue

		# if the size is bigger than the max_size option, don't run that test
		if max_size != -1 and file_size > max_size:
			print('skipped', fileref_str, '\t-> exceeded max_size')
			continue
		
		# construct the output
		output_file = (test_cases_folder + '/output_' + fileref_str + '.txt')
		# read the expected output
		with open(output_file, 'r') as ofile:
			output = []
			for line in ofile:
				output.append(line.strip())

		# construct an array with the relation between file and test case solution
		test_cases.append([file_size, fileref, output])

	# sort by size to run first the samller tests
	test_cases.sort(key=lambda x: x[0])

	# run the tests
	print()
	tests_passed = 0
	tests_total = 0
	failed_tests = []
	for file_size, fileref, expected_output in test_cases:
		fileref_str = '_'.join(str(x) for x in fileref)
		filename = test_cases_folder + '/input_' + fileref_str + '.txt'

		alg_output_unprocessed = alg(filename)
		if not isinstance(alg_output_unprocessed, list):
			alg_output_unprocessed = [alg_output_unprocessed]

		alg_output = [str(x) for x in alg_output_unprocessed]

		if len(alg_output) != len(expected_output):
			print('Error. Your algorithm should be returning', len(expected_output),
				'values, but it is returning', len(alg_output), 'instead. Fix this ' +
				'and run the tester again.')
			return

		answers_ok = []
		print(fileref_str, end='\t')
		errors_buffer = ''
		for i in range(len(alg_output)):
			if expected_output[i] == alg_output[i]:
				answers_ok.append(True)
				print(u'\N{heavy check mark}', end='')
			else:
				answers_ok.append(False)
				print(u'\N{heavy ballot x}', end='')
				errors_buffer += ('\n    Answer ' + str(i+1) + ':\n\tExpected: ' + expected_output[i] +
					'\n\tResult: ' + alg_output[i])
				failed_tests.append(fileref_str)

		if all(answers_ok):
			tests_passed += 1
			print()
		else:
			print(errors_buffer)
			errors_buffer = ''

		tests_total += 1

	print()
	if tests_total == tests_passed:
		message = 'All tests passed'
		print(u'\N{heavy check mark}'*len(message))
		print(message)
		print(u'\N{heavy check mark}'*len(message))
	else:
		message = 'Some tests didn\'t pass'
		print(u'\N{heavy ballot x}'*len(message))
		print(message)
		print(u'\N{heavy ballot x}'*len(message))
		print('Failed tests:', ','.join(failed_tests))


if __name__ == '__main__':
	if (len(sys.argv) < 3):
		print('usage: ' + __file__ + 
				' path/to/your/algorithm.py path/to/testcases [options]\n')
		print('valid options: ')
		print('\tmax_size=[maximum input size]')
		print('\tname=[function that returns solution]')
		print('\tonly=[specific tests to run, excluding others]')
		sys.exit()
	tester_name, student_algorithm, test_cases_folder, *options = sys.argv
	
	valid_options = ['max_size', 'name', 'only']
	name = 'alg'
	max_size = -1
	only = []
	for opt in options:
		opt_name, opt_value = opt.split('=')
		if opt_name == 'max_size':
			max_size = int(opt_value)

		if opt_name == 'name':
			name = opt_value

		if opt_name == 'only':
			only = opt_value.split(',')

		if opt_name not in valid_options:
			print('Unknown option "' + opt_name + 
					'". Valid options are\n\t"name"\n\t"max_size"')
			print('Option "'+ opt_name +'" not set')

	option_values = [name, max_size, only]
	test(student_algorithm, test_cases_folder,  *option_values)

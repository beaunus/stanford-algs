<?php

(new TesterApp)->run();

/**
 * Class TesterApp
 *
 * @property string $s
 * @property array|string[] $t
 */
class TesterApp
{
    /**
     * @var array
     */
    private $optionsSpecification = [
        's' => 'required',
        't' => 'required'
    ];

    protected $options = [];

    /**
     * TesterApp constructor
     */
    public function __construct()
    {
        $this->init();
    }

    /**
     * Runs all of test cases
     *
     * @return void
     */
    public function run()
    {
        (new TestCasesProcessor($this))->run();
    }

    private function init()
    {
        $options = getopt("s:t:");

        try {
            $this->hasRequiredOptions($options);
            $this->initParameters($options);
        } catch (Exception $e) {
            $this->error($e->getMessage());
            die;
        }
    }

    /**
     * @param $options
     *
     * @throws InvalidArgumentException
     */
    private function hasRequiredOptions($options)
    {
        $actualOptions = array_keys($options);
        $requiredOptions = array_keys($this->optionsSpecification);


        if (array_diff($requiredOptions, $actualOptions)) {
            throw new InvalidArgumentException("Options " . implode(',', $requiredOptions) . " are required");
        }
    }

    /**
     * @param $options
     */
    private function initParameters($options)
    {
        foreach ($options as $optionName => $option) {
            $this->{$optionName} = $option;
        }
    }

    /**
     * @param $name
     *
     * @param $value
     */
    public function __set($name, $value)
    {
        if (!isset($this->options[$name])) {
            $this->options[$name] = method_exists($this, "process{$name}")
                ? $this->{"process{$name}"}($value)
                : $value;
        }
    }

    protected function processs($path)
    {
        if (is_file($path) && file_exists($path)) {
            return $path;
        }

        throw new InvalidArgumentException("No such script with path {$path}");
    }

    /**
     * @param $path
     *
     * @return array
     */
    protected function processt($path)
    {
        $validPaths = array_filter(preg_grep('/^.*\/input_\w+_\d+_\d+\.txt$/', glob($path)), function ($testCasePath) {
            return is_file($testCasePath) && file_exists($testCasePath);
        });

        natsort($validPaths);

        return $validPaths;
    }

    /**
     * @return array
     */
    public function getOptions()
    {
        return $this->options;
    }

    public function correct($msg)
    {
        echo "\033[32m ✔{$msg} ✔ \033[0m\n";
    }

    public function error($msg)
    {
        echo "\033[31m ✖{$msg} ✖ \033[0m\n";
    }
}

/**
 * Class TestCasesProcessor
 *
 * @property string $s
 * @property array|string[] $t
 */
class TestCasesProcessor
{
    /**
     * @var TesterApp
     */
    private $tester;

    /**
     * @param TesterApp $tester
     */
    public function __construct(TesterApp $tester)
    {
        $this->tester = $tester;
    }

    public function run()
    {
        foreach ($this->t as $testCasePath) {
            $this->execTestcase($testCasePath);
        }
    }

    /**
     * @param $name
     *
     * @return mixed
     */
    public function __get($name)
    {
        return $this->tester->getOptions()[$name];
    }

    /**
     * @param $testCasePath
     */
    private function execTestcase($testCasePath)
    {
        $shotTestCasePath = $this->shortTestCasePath($testCasePath);

        try {
            if ($this->compareAnswers($testCasePath)) {
                $this->tester->correct($shotTestCasePath);
            }
        } catch (Exception $e) {
            $this->tester->error("{$shotTestCasePath}: {$e->getMessage()}");
        }
    }

    private function compareAnswers($testCasePath)
    {
        if ($this->correctValues($testCasePath) == $this->actualValues($testCasePath)) {
            return true;
        }

        throw new RuntimeException("Test failed");
    }

    /**
     * @param $testCasePath
     *
     * @return array
     */
    private function actualValues($testCasePath)
    {
        return $this->processAnswers(shell_exec("php {$this->s} {$testCasePath}"));
    }

    /**
     * @param $testCasePath
     *
     * @return array
     */
    private function correctValues($testCasePath)
    {
        $outputPath = $this->outputPath($testCasePath);

        if (file_exists($testCasePath) && is_file($outputPath)) {
            return $this->processAnswers(file_get_contents($outputPath));
        }

        throw new RuntimeException("No respective output file for the testcase {$testCasePath}");
    }

    /**
     * @param $testCasePath
     *
     * @return mixed
     */
    private function outputPath($testCasePath)
    {
        return str_replace('input_', 'output_', $testCasePath);
    }

    /**
     * @param $outputString
     *
     * @return array
     */
    private function processAnswers($outputString)
    {
        return array_map(function ($value) {
            return trim($value);
        }, array_filter(explode("\n", $outputString)));
    }

    /**
     * @param $testCasePath
     *
     * @return string
     */
    private function shortTestCasePath($testCasePath)
    {
        return "..." . strstr($testCasePath, '/input');
    }
}

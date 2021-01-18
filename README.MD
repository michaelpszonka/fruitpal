This CLI tool leverages the Picocli tool to consume client input.
https://picocli.info/

Instructions:

To run the app
 
1.  Run maven install to run the necessary dependencies.
2.  Run maven package to recompile the application and create an application JAR file. The JAR will be in the target directory and called FruitPal-1.0-jar-with-dependencies.jar
3.  The simplest way to run the app from the CLI is to alias the JAR

Terminal on OSX: 
alias fruitpal='java -jar path/to/FruitPal-1.0-jar-with-dependencies.jar'

CMD on Windows: 
doskey fruitpal=java -jar path/to/FruitPal-1.0-jar-with-dependencies.jar $*

Usage
fruitpal mango 53 405

Result
BR  $22,060.10  |  (54.42 * 405.0) + 20.0
MX  $21,999.20  |  (54.24 * 405.0) + 32.0



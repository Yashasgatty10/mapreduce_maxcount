# Maximum Occurring Word using Hadoop MapReduce

This project implements a Java-based Hadoop MapReduce application to identify the word that occurs most frequently in a given text dataset. The main aim of the project is to design and implement a MapReduce program that processes a text file and determines the maximum occurring word along with its frequency. The project uses Java 11, Apache Hadoop 3.3.6, Hadoop MapReduce, HDFS, Windows, and GitHub. The `MaxWord.java` file contains the complete Java source code with the Mapper and Reducer implementations, `input.txt` contains the text dataset, and `MaxWord.jar` contains the compiled MapReduce application. The MapReduce workflow starts with the input text file, where the Mapper reads each line, splits it into individual words, and generates intermediate key-value pairs in the form `<word, 1>`. Hadoop then performs Shuffle and Sort to group identical words together. The Reducer calculates the total frequency of each word and identifies the word with the highest occurrence. For example, if the word Hadoop occurs 25 times, the final output will be `Hadoop 25`.

The project requires Java JDK 11, Apache Hadoop 3.3.6, and Hadoop Windows native support such as `winutils.exe` and the required native DLL. Java installation can be verified using the command `java -version` and Hadoop installation can be verified using `hadoop version`.

To run the project, first open Command Prompt and navigate to the project directory:

    cd C:\mapreduce_maxcount

Compile the Java program:

    for /f "delims=" %i in ('hadoop classpath') do javac -cp "%i" MaxWord.java

After successful compilation, create the JAR file:

    jar -cvf MaxWord.jar *.class

If an output directory from a previous execution already exists, remove it:

    rmdir /s /q output

Execute the MapReduce program:

    hadoop jar MaxWord.jar MaxWord input.txt output

After successful execution, view the output:

    type output\part-r-00000

The project also demonstrates basic HDFS commands. The `hdfs dfs -ls` command is used to list files and directories in HDFS. The `hdfs dfs -mkdir testdir` command creates a new HDFS directory. The `hdfs dfs -copyFromLocal input.txt testdir` command copies a file from the local file system to HDFS. The `hdfs dfs -copyToLocal testdir/input.txt copied_test.txt` command copies a file from HDFS back to the local file system. The `hdfs dfs -rm test.txt` command removes a file from HDFS, while `hdfs dfs -rm -r testdir` removes a directory and its contents.

The key concepts demonstrated by this project include Hadoop MapReduce, Mapper, Reducer, HDFS, key-value pairs, Shuffle and Sort, word frequency analysis, and distributed data processing. The project is intended to demonstrate practical implementation of Hadoop-based data processing using Java and provides hands-on experience with compiling, packaging, executing, and managing MapReduce applications on a Windows environment.

## Author

**Yashas Gatty**  
Computer Science and Engineering

GitHub: https://github.com/Yashasgatty10/mapreduce_maxcount

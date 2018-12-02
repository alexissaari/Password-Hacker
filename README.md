# Password-Hacker
This project compares the efficiency of MapReduce for cracking passwords compared to a brute force iterative control, both as a single thread implementation and a multi thread implementation.


Group Project
Samantha Ingersoll, Alexis Saari, Nathan Stowman, Skyler Svendsen

This project used:
Putty and WinSCP

To run this project, you will need:
The provided input directory, which contains input1 - input 63. These input files are used to read in each possible 4 digit password option using digits, lowercase alphabet, and uppercase alphabet.
A Java file that implements single-thread brute-force password cracking, which was used as a control.
A Java file that implements multi-thread brute-force password cracking, which was used as a comparison to MapReduce.
A Java file that implements the mapper, reducer, and main functions of MapReduce. We have two MapReduce implementations, one for finding a single password and one for finding each password in a supplied array.

Steps to run the Single-thread and Multi-thread portions of the project:
1) Open Eclipse (or any suitable Java IDE). 
2) Create a project
3) Create a file with the same name as the Single-thread implementation
4) Create a file with the same name as the Multi-thread implementation
5) Copy the supplied Single-thread implementation file into the appropriate created file
6) Copy the supplied Multi-thread implementation file into the appropriate created file
7) Adjust the Multi-thread file input path to the local directory where inputs are located
8) Run each file

Steps to run the MapReduce portion of the project:
1) Make directories for the Assignment3Main.java and it's input files
	yourName@zoidberg:~$ hadoop fs -mkdir /user/yourName/Project415
	yourName@zoidberg:~$ hadoop fs -mkdir /user/yourName/Project415/input	
2) Move the input files into their directory.
	yourName@zoidberg:~$ hadoop fs -copyFromLocal /home/yourName/input1 /user/yourName/Project415/input/input1
	yourName@zoidberg:~$ hadoop fs -copyFromLocal /home/yourName/input2 /user/yourName/Project415/input/input2
	...
	yourName@zoidberg:~$ hadoop fs -copyFromLocal /home/yourName/input63 /user/yourName/Project415/input/input63

3) Compile the files
	yourName@zoidberg:~$ hadoop com.sun.tools.javac.Main MapReduceAlg1.java
	yourName@zoidberg:~$ hadoop com.sun.tools.javac.Main MapReduceAlg2.java
4) Move the jar file
	yourName@zoidberg:~$ jar cf wc.jar MapReuceAlg1*.class
	yourName@zoidberg:~$ jar cf wc.jar MapReuceAlg2*.class
5) Run the file**
	yourName@zoidberg:~$ hadoop jar wc.jar Assignment3Main /user/nstowman/Project415/input /user/nstowman/Project415/output_single
	yourName@zoidberg:~$ hadoop jar wc.jar Assignment3Main /user/nstowman/Project415/input /user/nstowman/Project415/output_multi
6) Look at your output
	yourName@zoidberg:~$ hadoop fs -cat /user/nstowman/Project415/output_single/part-00000
	yourName@zoidberg:~$ hadoop fs -cat /user/nstowman/Project415/output_multi/part-00000

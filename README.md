# Password-Hacker
This project compares the efficiency of MapReduce for cracking passwords compared to a brute force iterative control.


Assignment 3
Samantha Ingersoll, Alexis Saari, Nathan Stowman, Skyler Svendsen

This project used:
Putty and WinSCP

To run this project, you will need:
The three provided input files: file01, file02, and file03.
A java file that implements the mapper, reducer, and main functions, we named ours Assignment3Main.java.

Steps to run this project:
1) Make directories for the Assignment3Main.java and it's input files
	yourName@zoidberg:~$ hadoop fs -mkdir /user/yourName/Assignment3
	yourName@zoidberg:~$ hadoop fs -mkdir /user/yourName/Assignment3/input	
2) Move the input files into their directory.
	yourName@zoidberg:~$ hadoop fs -copyFromLocal /home/yourName/file01 /user/yourName/Assignment3/input/file01
	yourName@zoidberg:~$ hadoop fs -copyFromLocal /home/yourName/file02 /user/yourName/Assignment3/input/file02
	yourName@zoidberg:~$ hadoop fs -copyFromLocal /home/yourName/file03 /user/yourName/Assignment3/input/file03
3) Compile the files
	yourName@zoidberg:~$ hadoop com.sun.tools.javac.Main Assignment3Main.java
4) Move the jar file
	yourName@zoidberg:~$ jar cf wc.jar Assignment3Main*.class
5) Run the file
	yourName@zoidberg:~$ hadoop jar wc.jar Assignment3Main /user/nstowman/assignment3/input /user/nstowman/assignment3/output
6) Look at your output
	yourName@zoidberg:~$ hadoop fs -cat /user/nstowman/assignment3/output/part-00000
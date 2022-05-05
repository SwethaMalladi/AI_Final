Folders: 
Src_cosine: File contains source code of cosine similarity algorithm
InputOutput_cosine: File contains input and output files of cosine similarity algorithm
Src_SS : File contains sourcecode of String searching algorithms
InputOutput_SS : File contains input and output files of string searching algorithms

String Searching Algorithms:

Operating System: Windows
Tool:             Eclipse
Language: 		Java


Aim of the Project:

This project was aimed at designing a system that will be able to detect the possible act of plaigiarism. Since the source file that needs to evaluated, and the pool of
documents containing files comprise of strings it is natural to use string comparison algorithms. We used three existing classical string comparison algorithms for the task,
these algorithms are KMP, Rabin Karp and Boyer Moore comparison algorithm.

Installation for Eclipse

1.	Create a JAVA project in eclipse
2.	Copy entire pool of documents in a folder named "DataSet_SourceFiles", and include the path of this directory in MClass.java
    on line no 34, example:---> final File folder = new File("C:\AI_Project"); 
3.  Include the test file on the line no 59 of the MClass.java, example:---> File inputFile = new File( "input.txt"); 

Flow of the Project:
	
1. The source code has many java classes which are:--> MClass.java, KMP.java, BMSubStr.java, RabinKarp.java:
   a) MClass.java : Main java file.
   b) KMP.java : Has the KMP algorithm implementation.
   c) BMSubStr.java : Has the Boyer Moore algorithm implementation.
   d) RabinKarp.java : Has the Rabin Karp algorithm implementation.
  
2. Please run the MClass.java file, it will take the input file and test for plaigarism with respect to
   the files in the document pool.
3. The result of the plaigarism is shown on the console, at the same time the results are also written back
   on the result files:--> kmp.txt, boyreMoore.txt and rk.txt corresponding to the respective
   algorithms being used for the comparison.
4. The results written on the files are dependent on the threshold that we choose for the similarity for plagiarism,
   we have used the similarity threshold to be 60.00 on line no  97 of the MClass.java, please change that
   according to your choice.
5. After running the MClass.java, please run stringPlagarismOutputUI.java in the user interface to select the respective 
   comparison results.

Cosine similarity

Plagiarism-checker-Python

This repo consists of a source code of a python script to detect plagiarism in textual document using **cosine similarity**

How is it done?

You might be wondering on how plagiarism detection on textual data is done, well it aint that complicated as you may think.

We all all know that computer are good at numbers, so in order to compute the simlilarity between on two text documents, the textual  raw data is transformed into vectors => arrays of numbers and then from that we are going to use a basic knowledge vector to compute the the similarity between them.

This repo consist of a basic example on how to do that.

Getting started

To get started with the code on this repo, you need to either *clone* or *download* this repo into your machine just as shown below;

Dependencies 

Before you begin playing with the source code you might need to install deps just as shown below;

```bash
pip3 install -r requirements.txt
```
Running the App

To run this code you need to have your textual document in your project directory with extension **.txt** and then when you run the script, it will automatically loads all the document with that extension and then compute the similarity between them just as shown below;

('Sudhir.txt', 'requirements.txt', 0.0)
('requirements.txt', 'swetha.txt', 0.0)
('Sudhir.txt', 'john.txt', 0.5713243251172899)
('Sudhir.txt', 'swetha.txt', 0.20179089793739657)
('john.txt', 'swetha.txt', 0.16228391831223246)
('john.txt', 'requirements.txt', 0.0)





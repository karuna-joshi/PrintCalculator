# PrintCalculator

A simple print job cost calculator

Problem Description
To reduce printing waste, a school is implementing PaperCut and will charge for printing as follows:

Paper size A4, job type single sided:
 * 15 cents per black and white page
 * 25 cents per colour page

Paper size A4, job type double-sided:
 * 10 cents per black and white page
 * 20 cents per colour page

Write a program in Java that helps the system administrator to calculate the print costs. It takes a list of A4 print jobs and calculates the cost of each job, given the total number of pages, number of colour pages and whether printing is double-sided.

Support for other paper sizes will be added in the future.    
The application should:
 * Read print jobs from a file (see attached file for an example.)
 * Output the job details and job cost for each job to the console
 * Output the total cost of all jobs to the console

Solution
My objective was focused to deliver simple demonstration of the problem. I provided a set of tests and an executable class 
that receives the input csv file which further parses the data to list of jobs.
These jobs are individually ahndled and processed to calculate the cost.

The solution is built and the tests are run using maven.


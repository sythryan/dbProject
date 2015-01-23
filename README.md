# dbProject
Database Systems Final Project
DUE AT THE BEGINNING OF THE FINAL EXAM PERIOD

To recap the discussion in class, here is the final project specifications.

Through https://storm.cs.uni.edu/~username, you have a working Wordpress content management system with the Vantage theme that renders business listings in a "Yellow Pages" format.  The back-end database supplying content to this Wordpress deployment resides on storm.cs.uni.edu under your specific account's MySQL database.

The final project is to infuse your MySQL database backend with the specific slice of Iowa-based businesses located in the CSV file also residing in your home directory on storm.cs.uni.edu, with the stipulation that your approach be suitable for populating the backend with the full, 170,000-entry Iowa-based business listings. Your task is to populate your site with the subset of the full dataset, but your approach should scale appropriately to the larger scenario (i.e., a 'manual' approach isn't going to cut it).

Regardless of your approach, documentation of your process is a required component of the final submission.  You are expected to wrote code or comprise scripts to manipulate the dataset.  The source code for these components must be submitted as part of the final submission. 

During the next few class periods, we will be analyzing the database modifications caused by adding content throught the Wordpress front end.  While you are free to take any approach you deem sensible in the task at hand, a suggested approach would be:

ANALYSIS

If your web site is working, start by doing a mysql dump of your database as a reference database.
Make a single modification to the Wordpress site -- one of the following:
Add a business or
Add a category or
Add a user.
Dump your mysql database after making the single modification as the modified database.
Use Unix tools to help you analyze the differences between these two files:
Use 'sed' and output redirection '>' to change the two-character pattern '),' into the three-character pattern'),\n' in both the reference and modified database, storing the output under different names.
Use 'diff' and 'less' to browse the differences between the adjusted reference and modified databases, looking past any non-essential date-stamp changes (there might be essential ones though!) to elicit the exact changes to the database required to affect the appropriate changes.
Test your results:
Restore the reference database using the unix command line.
Fire up the MySQL client and manually enter the commands you believe will modify the site to reflect the same changes performed in stem #2 above.
Check the site for full functionality.  Does it work correctly?  Did you break it?  Does it result in the same effective change?
If not, repeat step #4
Return to step #1, but choose a different modificiation this time in step #2.
PROGRAMMING

Once you have fully ananlyzed the changes needed to modify your Wordpress site manually through changes to the MySQL database, it's time to craft a full database population through scripting, coding, and direct interaction with the MySQL database.  You are free to code this up in any way that you choose provided your submission to the task includes either the source code for scripts/programs that you've written, or an explanation of how you used existing tools to accomplish the task.  Again, here is a suggested approach:

Analyze the CSV file you were given along with the header file that was posted separately.
Based upon your analysis above, idenitfy the specific columns of the data set that you will need.
Identify the specific columns of the data set that you will need to compose (i.e., they are not found in the existing CSV set).
Adding all of the components in the dataset will slow down to a crawl eventually due to FOREIGN KEY constraints.  Observe that if you can assure MySL that entries are already unique, then you can turn off the FOREIGN KEY checks.  Spend some time researching how to turn off FOREIGN KEY checks and realize that the burden is then on you to enforce uniqueness of these fields.
Interacting with MySQL for this large of a dataset could be a bit overwhelming.  Research on your own how to leverage MySQL's FROM INFILE abilties and reconcile what that would mean for #1 above. 

From the MySQL documentation, the syntax is
   LOAD  DATA LOCAL INFILE  'DatabaseF14.csv'   INTO   TABLE  abc FIELDS TERMINATED  BY   ','   ENCLOSED  BY   '"'   LINES TERMINATED  BY   '\n' IGNORE  1  LINES -- If your input file has a header -PG  ( col1 ,  col2 ,  col3 ,  col4 ,  col5 ...)   

Check for errors, check for appropriate website behavior.  If you have fatal errors, go back and restore the database.

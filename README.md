# Delete-Chunk-Size-Tester
A simple java code to test the chunk size, the optimal delete LIMIT of a JDBC database session data table.

#What happens in this tool:
         * This will duplicate the table IDN_AUTH_SESSION_STORE in the WSO2CARBON_DB
         * Increase the size of the table by duplicating rows. So that it will create a exact similar table to the IDN_AUTH_SESSION_STORE with big no. of data.
         * Delete the entries with LIMITs of different chunck sizes and calculate time taken for the delete.
         * By runig this class in your environment where  WSO2 IS is running, From the results you can select the best chunck size for your session clean up task in your environment.


** To run this tool folowing arguments  should be sent by changing the run.sh file inside DeleteChunkSizeTest.

agr0 = Driver class name
         eg: mysql = "com.mysql.jdbc.Driver"
 
arg1 = Connection url of your WSO2CARBON_DB where all your session tables are available
   
arg2 = DB username
arg3 = DB password

arg4 = No of times the original table should be coppied to duplicate table.
This will help to create a table with a big size to calculate the exact performance.




# Delete-Chunk-Size-Tester
A simple java code to test the chunk size, the optimal delete LIMIT of a JDBC database session data table.

# What happens in this tool:
         # This will duplicate the table IDN_AUTH_SESSION_STORE in the WSO2CARBON_DB
         # Increase the size of the table by duplicating rows. So that it will create a exact similar table to the IDN_AUTH_SESSION_STORE with big no. of data.
         # Delete the entries with LIMITs of different chunck sizes and calculate time taken for the delete.
         # By runig this class in your environment where  WSO2 IS is running, From the results you can select the best chunck size for your session clean up task in your environment.


# To run this tool folowing arguments  should be sent by changing the run.sh file inside DeleteChunkSizeTest.

agr0 = Driver class name
         eg: mysql = "com.mysql.jdbc.Driver"
 
arg1 = Connection url of your databse where all your session tables are available
   
arg2 = DB username
arg3 = DB password

arg4 = No of times the original table should be coppied to duplicate table.
This will help to create a table with a big size to calculate the exact performance.

# content of run.sh file :

cd src

javac ChunkSizeDeleteTest.java

java -cp .:{path to the  JDBC SQL connector} ChunkSizeDeleteTest "{SQL driver name}" "{Databse URL which contains session data tables}" "{DB username}" "{DB password}" {No of times to duplicate}

eg: java -cp .:/home/wso2dinali/SUPPORT/mysql-connector-java-5.1.42/mysql-connector-java-5.1.42-bin.jar ChunkSizeDeleteTest "com.mysql.jdbc.Driver" "jdbc:mysql://localhost:3306/JDBC?useSSL=false" "root" "root" 100000



# Run this tool by using command
   
   sh run.sh 
   
# The output results will be as follows :

the round :0

The chunck Size 256 takes time of 1ms


the round :1

The chunck Size 512 takes time of 0ms


the round :2

The chunck Size 1000 takes time of 1ms


the round :3

The chunck Size 5000 takes time of 2ms


the round :4

The chunck Size 50000 takes time of 2ms


the round :5

The chunck Size 100000 takes time of 0ms


the round :6

The chunck Size 512000 takes time of 1ms




# Conclusion
  From the results it concluded that the best chunck size would be 512 or 100,000  as the optimal chunck size. Further more you can take the average for more accurate results.





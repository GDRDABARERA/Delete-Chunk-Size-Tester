#!/usr/bin/env bash
cd src
javac ChunkSizeDeleteTest.java
java -cp .:/home/wso2dinali/SUPPORT/mysql-connector-java-5.1.42/mysql-connector-java-5.1.42-bin.jar ChunkSizeDeleteTest "com.mysql.jdbc.Driver" "jdbc:mysql://localhost:3306/JDBC?useSSL=false" "DB_username" "DB_pwd" 1000



#! Here  the arguments are args[0] = JDBC driver name, args[1]= JDBC Url to the database , args[2] = Database username , args[3] = Database password , arg[4] = No of times the original table is copied to the duplicate table
#!    to increase the no. of rows in the duplicate table. This may create 1 Million size of data in the duplicate table

# StudentRecordApp Final Project for ENSF480
A student record directory Java GUI desktop app built in Model View Controller framework with SOLID Principles

Group Member emails:

Rohit Yeast
rohit.yeast1@ucalgary.ca

Desiree Leal
desiree.leal1@ucalgary.ca

Hoang Truong
hoang.truong1@ucalgary.ca

Our project is called ENSF409FinalProject.

To run the program, create an SQL database with the supplied admin, student and course csv files.  Make sure to name your schema in your SQL workbench with the csv files as "mydb". 

Before going to run the program, go into package serverModel and find class IDBCredentials. There you can set your own JDBC Driver, URL, Username and Password. Once you have done that, you can run the program.

To run the program, go into package serverController and find class Server. Run it as a Java Application. Once you have done that, go into package clientController and find class GUIControl. Run that as a java Application. 

You will be prompted to login as either an admin or a student. To do so, you can use name "Rohit" and ID "1000". This will work for both admin and student.

Our bonus features include logging in as either an admin or student. If admin is chosen, there is a separate GUI Frame that has features specific to that of admin, where they can add and remove classes from the list of courses in the database.

#### UML Diagram
![alt text](https://github.com/xtinatruong/StudentRecordApp/blob/main/FinalProject%20UML.drawio.png)

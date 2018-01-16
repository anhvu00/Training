Hotel booking application
Author: Anh Vu
Create date: 2009

RMI is a complete working example of a client/server java application.
It demonstrates the following:
- Model View Control object oriented design pattern
- Remote Method Invocation (RMI)
- Client/Server architecture
- Configurable Database access
- Row and Table locking (for DB update)
- Exception handling
- Javadoc

Note:
The "database" for this project is just a binary file (i.e. Data.db) where each row is a fix length record.
You can modify the DB* classes to use a real RDBMS database. This is left out as your exercise assignment.

SETUP INSTRUCTION:

- Clone this project from GitLab
- In Eclipse, File | Import | Existing project into workspace, select the local clone RMI folder

RUNNING MODES:

The main function is RMI.java which supports 3 running modes: STANDALONE, SERVER, and CLIENT.
To run as a standalone application, use the command: java -jar RMI.jar STANDALONE
In the Client/Server mode, you must start the server first: java -jar RMI.jar SERVER
Then, start the client: java -jar RMI.jar CLIENT.

Standalone mode will ask you for the location of a Local database (i.e. Data.db).
When you run the program and make changes to the database, no one else will see the changes.

Server mode will ask you for the location of the Server database. 
Though this database is the same (i.e. Data.db), it is exclusively used by the server.
When the Client program makes changes to This database, all other clients will see the changes.

Note: There is still some hard code default database path in the source code which should not affect testing
because the user can select the database location. This location will be kept in RMI.properties for future runs.

USAGE:
 
 The application shows all available hotel rooms for booking.
 Select a row and click "Reserve" to book that room.
 It also allow you to search either Name, Location or both.
 This search narrows down the list of available rooms.
 
 RUN IN ECLIPSE:
 
 - In eclipse, right click the RMI.java, Run As | Run Configuration.
 - On the configuration window, type Name = RMI 
 Project = RMI
 Main class = com.kyron.rmi.RMI
 Click on the 'Arguments' tab, Program Argument = STANDALONE
 - Click the run button.
  
 BUILD JAR:
 
 Build an executable RMI.jar so that you can run in Client/Server mode.
 - In eclipse, select the RMI project folder
 - File | Export  | Runable JAR file, click Next
 - Launch Configuration = select the run configuration from previous step (see RUN IN ECLIPSE).
 Export destination, select the output folder (recommend the \bin folder), the output file name is RMI.jar
 - Click Finish. Ignore all warnings. You can fix the warnings as exercise.
 
 NOTE:
 When you run in command prompt, be sure to have the RMI.properties on the same location as the RMI.jar.
 
 
 
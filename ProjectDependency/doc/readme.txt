Date: 3/1/2017
Author: Anh

ProjectDependency is a maven project demonstrating how you can use Maven pom.xml to define project dependencies.
In this example, the MainApp.java calls some methods from another project (i.e. CommonLibs project).

Usage:
In the MainApp.java, import com.vexterra.MyJDBC, then call MyJDBC.connect("some url")

Lesson:
In your ProjectDependency (caller project) pom.xml, define your dependency as followed:
  		<dependency>
			<groupId>com.vexterra</groupId>
			<artifactId>CommonLibs</artifactId>
			<version>1.0</version>
			<scope>compile</scope>
		</dependency>
		
When you build this project (right click pom.xml, Run As | Maven install), it will builds the dependency project first, then build this project.
For example, if you open the file MyJDBC.java in the CommonLibs project, change the return value to 2, save it.  
Then, build this project and run MainApp.java, you will see the new return value 2.


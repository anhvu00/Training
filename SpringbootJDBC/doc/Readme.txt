Sample JDBC application with Springboot and in-memory database HSQLDB.
Please note the following:
- Project folder structure: 
/domain contains entity objects mapped to the correspondent tables
/service contains the JDBC methods to perform CRUD operation on the tables
/resources contains application properties, sql to create tables, pre-populate tables, and other configuration for the application
- MainApp.java implements Spring framework CommandLineRunner 
- The file names in /resources folder are significant

1. Compile
In eclipse, right click pom.xml, select Run as | Maven install

2. Run
In eclipse, right click MainApp.java, select Run As | Java application

3. Output
2016-12-18 01:25:03.099  INFO 13392 --- [           main] s.c.a.AnnotationConfigApplicationContext : Refreshing org.springframework.context.annotation.AnnotationConfigApplicationContext@548ad73b: startup date [Sun Dec 18 01:25:03 EST 2016]; root of context hierarchy
2016-12-18 01:25:05.878  INFO 13392 --- [           main] o.s.jdbc.datasource.init.ScriptUtils     : Executing SQL script from URL [file:/C:/anh/gitLabRepo/Anh-Sample/SpringbootJDBC/target/classes/schema-hsqldb.sql]
2016-12-18 01:25:05.884  INFO 13392 --- [           main] o.s.jdbc.datasource.init.ScriptUtils     : Executed SQL script from URL [file:/C:/anh/gitLabRepo/Anh-Sample/SpringbootJDBC/target/classes/schema-hsqldb.sql] in 6 ms.
2016-12-18 01:25:06.437  INFO 13392 --- [           main] o.s.j.e.a.AnnotationMBeanExporter        : Registering beans for JMX exposure on startup
2016-12-18 01:25:06.493  INFO 13392 --- [           main] com.vexterra.MainApp                     : Person saved successfully
2016-12-18 01:25:06.502  INFO 13392 --- [           main] com.vexterra.MainApp                     : Adam,200


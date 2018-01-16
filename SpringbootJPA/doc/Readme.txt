Sample JPA application with Springboot and in-memory database HSQLDB.

You should have studied the SpringbootJDBC before this project because
many of the learning points are similar.
 
Please note the following:
- Project folder structure: 
/domain contains entity objects mapped to the correspondent tables
/repository contains the JPA interface for CRUD operations
/service contains the JDBC methods to perform CRUD operation on the tables
/resources contains application properties, sql to create tables, pre-populate tables, and other configuration for the application
- MainApp.java implements Spring framework CommandLineRunner 
- The file names in /resources folder are significant

1. Compile
In eclipse, right click pom.xml, select Run as | Maven install

2. Run
In eclipse, right click MainApp.java, select Run As | Java application

3. Output
...
2016-12-19 20:25:40.240  INFO 8196 --- [           main] j.LocalContainerEntityManagerFactoryBean : Initialized JPA EntityManagerFactory for persistence unit 'default'
2016-12-19 20:25:41.324  INFO 8196 --- [           main] o.s.j.e.a.AnnotationMBeanExporter        : Registering beans for JMX exposure on startup
2016-12-19 20:25:41.642  INFO 8196 --- [           main] o.h.h.i.QueryTranslatorFactoryInitiator  : HHH000397: Using ASTQueryTranslatorFactory
2016-12-19 20:25:41.914  INFO 8196 --- [           main] com.vexterra.MainApp                     : Adam,2000
2016-12-19 20:25:41.914  INFO 8196 --- [           main] com.vexterra.MainApp                     : Eve,1999
2016-12-19 20:25:41.919  INFO 8196 --- [           main] com.vexterra.MainApp                     : Started MainApp in 7.187 seconds (JVM running for 7.842)
...


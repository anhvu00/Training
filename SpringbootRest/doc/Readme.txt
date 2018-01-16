Sample RESTful web service with Spring. 

1. Compile
In eclipse, right click pom.xml, select Run as | Maven install

2. Run
In eclipse, right click MainServer.java, select Run As | Java application
Open web browser and try the following URLs:

http://localhost:8080/greeting 
You should see a json string like
{"id":1,"content":"Hello, World!"}

http://localhost:8080/greeting?name=Anh
You should see a json string like
{"id":2,"content":"Hello, Anh!"}

*Note that the id is automatically increased after each run.

The URLs are HTTP requests and the json messages are responses.
The "name=Anh" is the parameter-value pair of the request. 

In Spring's approach to building RESTful web services, HTTP requests are handled by a controller. These components are easily identified by the http://docs.spring.io/spring/docs/{spring_version}/javadoc-api/org/springframework/web/bind/annotation/RestController.html
The GreetingController class handles `GET` requests .
The @RequestMapping annotation ensures that HTTP requests to `/greeting` are mapped to the `greeting()` method.
The greeting() method just return a Greeting object where the name is default to "World".

A key difference between a traditional MVC controller and the RESTful web service controller above is the way that the HTTP response body is created. Rather than relying on a link:/understanding/view-templates[view technology] to perform server-side rendering of the greeting data to HTML, this RESTful web service controller simply populates and returns a `Greeting` object. The object data will be written directly to the HTTP response as JSON.

This code uses Spring 4's new http://docs.spring.io/spring/docs/{spring_version}/javadoc-api/org/springframework/web/bind/annotation/RestController.html
The @RestController annotation marks the class as a controller where every method returns a domain object instead of a view. It's shorthand for `@Controller` and `@ResponseBody` rolled together.

The `Greeting` object must be converted to JSON. Thanks to Spring's HTTP message converter support, you don't need to do this conversion manually. 
Because http://wiki.fasterxml.com/JacksonHome[Jackson 2] is on the classpath, Spring's http://docs.spring.io/spring/docs/{spring_version}/javadoc-api/org/springframework/http/converter/json/MappingJackson2HttpMessageConverter.html[`MappingJackson2HttpMessageConverter`] is automatically chosen to convert the `Greeting` instance to JSON.


Although it is possible to package this service as a traditional link:/understanding/WAR[WAR] file for deployment to an external application server, the simpler approach demonstrated below creates a standalone application. You package everything in a single, executable JAR file, driven by a good old Java `main()` method. Along the way, you use Spring's support for embedding the link:/understanding/Tomcat[Tomcat] servlet container as the HTTP runtime, instead of deploying to an external instance.

Date 3/1/2017
Author Anh

This CommonLibs Maven project should be used with the ProjectDependency (also Maven) project.

The idea is that CommonLibs contains some common functions (like JDBC connect, query, etc.) which will be called/used by another project
 (see the readme.txt file in ProjectDependency for detail usage).
 
 When you create such common libraries, you need to manage the version number so that the caller would benefit from the best/latest code.
 For example, you found a bug in version 1.0 of the library, fix it, test it, update to version 1.1, and check in GitLab.
 The caller is still calling version 1.0 until he checks out v1.1 and update the caller's pom.xml to use version 1.1.
 
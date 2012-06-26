# MOPS Registrar #

## Overview ##

This application provides a registration service for a "Mothers of Preschoolers" (MOPS) branch.  It does
this by providing a web layer which allows users to sign up, enter in their registration information, as 
well as the registration information for each of their children that attend MOPS.  The application
also provides "Admin user" functionality, to allow administrators the ability to view and edit all the 
registered users along with their children.

## Technical Implementation Details ##

This is a Java Spring based web application, using Spring's Web MVC framework as the main driver of the
process flow.  JSP views are used along with Tiles layout to render the web pages.  Spring's Excel
view is also used to provide an Excel export of the data for the administrators.  

The web application provides a database layer with two implementations: one in memory and one using Mongo
DB.  Spring Data is used to wire up the Mongo DB instance with the web application.  A service layer 
exists to abstract this implementation from the web layer.

The application also utilizes Spring Security to enforce authorization checks on both user profile web pages
as well as administrative web pages.  Both the MopsUser and AdminUser objects extend from UserDetails to
build upon Spring Security's pipeline.

Cloud Foundry support is also built in through Spring profiles, which allows this application to be deployed
to a Cloud Foundry instance.

Maven is used both in the build system but also to manage dependencies.  The application as-is supports
Eclipse integration, with .settings files for Maven, WST (Web Container), and Spring.

## Getting Started ##

One can load this project into Eclipse or deploy a built war file to a running application server.  To access
the main site locally, browse to: http://localhost:8080/registrar/ .  An index.jsp page has been provided to
redirect to the main home page within the web application.

If needed, a GenerateData.java class has been provided within src/test/java to generate MopsUser, Child, and
AdminUser objects to an available Mongo DB instance.  The generate-data-spring.xml file in src/test/resources
provides Spring beans which configure the connection to the Mongo DB instance.

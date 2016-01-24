[![Build Status](https://travis-ci.org/robertjamesmiller/password-validator.svg?branch=master)](https://travis-ci.org/robertjamesmiller/password-validator)

# a Java Validation Annotation for Passwords

Write a password validation service in Java, meant to be configurable via IoC using the Spring Framework. The service is meant to check a text string for compliance to any number of password validation rules.
The rules currently known are:

* Must consist of a mixture of lowercase letters and numerical digits only, with at least one of each.
* Must be between 5 and 12 characters in length.
* Must not contain any sequence of characters immediately followed by the same sequence.

## Build and install this Java annotation into your local Maven repository: 

    mvn install
    
### Use this Password annotation in a Spring MVC project

This annotation was modeled after the [Hibernate Email Validator Annotation](https://github.com/hibernate/hibernate-validator/blob/03faf186afa26a155fdb621f860422a4574a6bea/engine/src/main/java/org/hibernate/validator/internal/constraintvalidators/hv/EmailValidator.java) 
    
* Configure your Spring application to use annotations:

    <mvc:annotation-driven/>

* Add the snapshot reference of this jar to your pom.xml

    <dependency>
        <groupId>com.dropclip.validator</groupId>
        <artifactId>password-validator</artifactId>
        <version>0.0.1-SNAPSHOT</version>
    </dependency>
    
* Add the @Password annotation to a field in a domain object. This annotation only works on fields, and not methods or otherwise.

    @Password
    private String password;
    
* Customize the validation message with a hard coded value:
    
    @Password ( message = "invalid password" )
    private String password;
    
* Try to break the validation rules described above and please let me know if you're successful!

### @TODO

* Figure out how to move validation messages to a localized properties file.

# ScribeUP Extensions
[Georgia Clinical and Translational Science Alliance (Georgia CTSA)](http://www.georgiactsa.org), [Emory University](http://www.emory.edu), Atlanta, GA

## What does it do?
It is a library that patches the [ScribeUP OAuth library](https://github.com/scribejava/scribejava) to support additional OAuth providers. It standardizes the attribute names of user profiles from those providers. The supported OAuth providers are (the classes that implement use of the providers are in parentheses):
* GitHub (`org.eurekaclinical.scribeupext.provider.GitHubProvider`)
* Globus (`org.eurekaclinical.scribeupext.provider.GlobusProvider`)
* Google (`org.eurekaclinical.scribeupext.provider.Google2Provider`)
* Twitter (`org.eurekaclinical.scribeupext.provider.TwitterProvider`)

It depends on ScribeUP version 1.3.1, which integrates with the [Eureka! Clinical CAS server](https://github.com/eurekaclinical/cas) to provide its OAuth-based authentication functionality. Making Eureka! Clinical CAS depend upon this library makes the library's additional OAuth providers available for use in CAS. You reference these providers in CAS by the class names above.

## Version history
Latest release: [![Latest release](https://maven-badges.herokuapp.com/maven-central/org.eurekaclinical/scribe-up-ext/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.eurekaclinical/scribe-up-ext)

### Version 2.0
Enhanced javadoc documentation. Removed support in the pom.xml for creating a tarball/zipfile. Cleaned up the pom.xml. Fixed an issue with Globus OAuth support that resulted from a change on the Globus side. Updated parent pom dependency.

### Version 1.0
Initial release.

## Build requirements
* [Oracle Java JDK 8](http://www.oracle.com/technetwork/java/javase/overview/index.html)
* [Maven 3.2.5 or greater](https://maven.apache.org)

## Runtime requirements
* [Oracle Java JRE 8](http://www.oracle.com/technetwork/java/javase/overview/index.html)

## Building it
The project uses the maven build tool. Typically, you build it by invoking `mvn clean install` at the command line. For simple file changes, not additions or deletions, you can usually use `mvn install`. See https://github.com/eurekaclinical/dev-wiki/wiki/Building-Eureka!-Clinical-projects for more details.

## Maven dependency
```
<dependency>
    <groupId>org.eurekaclinical</groupId>
    <artifactId>scribe-up-ext</artifactId>
    <version>version</version>
</dependency>
```

## Developer documentation
* [Javadoc for latest development release](http://javadoc.io/doc/org.eurekaclinical/scribe-up-ext) [![Javadocs](http://javadoc.io/badge/org.eurekaclinical/scribe-up-ext.svg)](http://javadoc.io/doc/org.eurekaclinical/scribe-up-ext)

## Getting help
Feel free to contact us at help@eurekaclinical.org.

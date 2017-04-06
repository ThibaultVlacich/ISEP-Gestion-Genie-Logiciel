# Team Sloths : Projet Génie Logiciel

## General

TODO

## Installation

### Prerequisites

* Gradle build tool ([Installation instructions](https://gradle.org/install))
* OR Eclipse EE IDE ([Download Eclipse IDE](https://eclipse.org))
* OR the Spring tool suite, a fork of Eclipse with all the tools for using the Spring framework ([Download Spring Tool Suite](https://spring.io/tools/sts))

I recommend using the Spring Tool Suite (or Eclipse with the Spring extension installed).

### Let's go

To run the app :

#### From Command-line interface

```
git clone https://github.com/ThibaultVlacich/Projet-GL-Sloth.git
cd Projet-GL-Sloth
```

Then on UNIX-like systems (Mac, Linux...), run : `./gradlew appRun`

Or on Windows, run : `gradlew appRun`

#### From Eclipse

First, you need to install from the Eclipse Marketplace [Buildship](https://marketplace.eclipse.org/content/buildship-gradle-integration).

Then, open the project cloned from GitHub with Eclipse, and go in the "Run" menu > "Run configurations". Click on "Gradle project" and press the "New" button.

Then configure the new run configuration like this:

![](http://i.imgur.com/9bsDSdx.png)

Then you should be able to run the app from the "Run" menu.

The app should be available under the URL http://localhost:8080/gestion-gl

Rock Paper Scissors Game by Andrew Erickson

Application Requirements
Java 1.6
Maven 3.2.1

Build Instructions
1. clone rockpaperscissors from github here - https://github.com/aotearoan/rockpaperscissors
2. standard maven commands apply (e.g. mvn test, mvn deploy, etc...)
3. To build an executable jar run: mvn clean compile assembly:single

Run the Game
1. from the command line, go to the "target" directory and execute "java -jar rockpaperscissors-1.0-jar-with-dependencies.jar"

To Do
- create RESTClient to expose GameEngine API via embedded REST server
- create AngularJS application front end as an alternative to the CLIClient

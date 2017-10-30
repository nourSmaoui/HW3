# HW3

# How to compile and run:

To compile the project:

mvn clean compile

To run the code with maven (preferred):

mvn exec:java -Dexec.mainClass="Main" -Dexec.args="--key 1 --type i --list 1 2 3 4 5"

To run the code with java:

cd target/classes/
java -classpath ~/.m2/repository/commons-cli/commons-cli/1.4/commons-cli-1.4.jar:. Main --key 12 --list 1 2 3 4 5 6 --type i

# Problems faced

running "java Main --key 1 --type i --list 1 2 3 4 5" was not successful because the classpath does not point to the location of the jar

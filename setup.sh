#!/bin/bash
shopt -s expand_aliases

# Setup Enviroment
if [ -d "maven" ]
then
	rm -r maven
fi

curl -o maven.zip https://dlcdn.apache.org/maven/maven-3/3.8.2/binaries/apache-maven-3.8.2-bin.zip
unzip maven.zip
rm maven.zip
mv apache-maven-* maven

alias mvnCmd="./maven/bin/mvn.cmd"

# Build
mvnCmd compiler:compile -f "./code/backend/pom.xml"
mvnCmd compiler:compile -f "./code/frontend/pom.xml"

# Test
mvnCmd test -f "./code/backend/pom.xml"
mvnCmd test -f "./code/frontend/pom.xml"

# Execution
mvnCmd javafx:run -f "./code/frontend/pom.xml"
mvnCmd exec:java -f -T 2 "./code/backend/pom.xml"

#!/bin/bash

if [ -d "maven" ]
then
	rm -r maven
fi

curl -o maven.zip https://dlcdn.apache.org/maven/maven-3/3.8.2/binaries/apache-maven-3.8.2-bin.zip
unzip maven.zip
rm maven.zip
mv apache-maven-* maven

alias mvn=$PWD"/maven/bin/mvn"

curl -o java.zip https://download.oracle.com/otn-pub/java/jdk/16.0.2%2B7/d4a915d82b4c4fbb9bde534da945d746/jdk-16.0.2_windows-x64_bin.zip
unzip java.zip
mv jdk-* java

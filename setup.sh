#!/bin/bash
shopt -s expand_aliases
if [ -d "maven" ]
then
	alias mvnCmd="./maven/bin/mvn"
fi

if [[ $1 == "setup" ]]
then
	#SETUP Maven
	echo "Setting up Maven"
	if [ -d "maven" ]
	then
		#rm manual - run 'rm --help'
		rm -r maven/
	fi
	
	curl -o maven.zip https://dlcdn.apache.org/maven/maven-3/3.8.3/binaries/apache-maven-3.8.3-bin.zip
	unzip maven.zip
	rm maven.zip
	mv apache-maven-* maven
	alias mvnCmd=$PWD"/maven/bin/mvn.cmd"
	
#Compile projects
elif [[ $1 == "compile" ]]
then
for project in code/*
do
	if [ -e $project"/pom.xml" ]
	then
		echo "Compiling "$project
		mvnCmd clean compile -f $project"/pom.xml"
	fi
done
#Test projects
elif [[ $1 == "test" ]]
then
	for project in code/*
	do
		if [ -e $project"/pom.xml" ] 
		then 
			echo "Testing "$project
			mvnCmd clean test -f $project"/pom.xml"
		fi
	done
#Run System
elif [[ $1 == "run" ]]
then
	# Launch server
	echo "Launching Back end"
	mvnCmd clean package -q -f "./code/backend/pom.xml"
	java -jar ./code/backend/target/backend-0.0.1-jar-with-dependencies.jar > serverlog.txt &

	# Wait 3 seconds to ensure server has launched
	sleep 3
	# Launch client application
	echo "Launching Front end"
	mvnCmd javafx:run -q -f "./code/frontend/pom.xml"
else
	echo "Expected Usage: config.sh [command]"
	echo "Possible commands:"
	echo "  setup       will setup the system including all project "
	echo "               specific setup defined in a project's setup.sh"
	echo "  compile     will compile all projects"
	echo "  test        will test all projects"
	echo "  run         will launch the client system and locally launch"
	echo "               any necessary services"
fi

#!/usr/bin/env bash

echo 'The following Maven command installs your Maven-built Java application'
echo 'into the local Maven repository, which will ultimately be stored in'
echo 'Jenkins''s local Maven repository (and the "maven-repository" Docker data'
echo 'volume).'
#set -x
#mvn jar:jar install:install help:evaluate -Dexpression=project.name
#set +x

echo 'The following complex command extracts the value of the <name/> element'
echo 'within <project/> of your Java/Maven project''s "pom.xml" file.'
set -x
NAME=`/usr/local/apache-maven-3.6.3/bin/mvn help:evaluate -Dexpression=project.name | grep "^[^\[]"`
set +x

echo 'The following complex command behaves similarly to the previous one but'
echo 'extracts the value of the <version/> element within <project/> instead.'
set -x
VERSION=`/usr/local/apache-maven-3.6.3/bin/mvn help:evaluate -Dexpression=project.version | grep "^[^\[]"`
set +x

echo 'The following command runs and outputs the execution of your Java'
echo 'application (which Jenkins built using Maven) to the Jenkins UI.'
#set -x
#java -jar target/${NAME}-${VERSION}.jar

echo 'remove old image.'
set -x
CONTAINERID=`docker ps -a | grep ${NAME} | awk '{print $1}'`
if test -n "$CONTAINERID"
then
   docker stop $CONTAINERID
   docker container rm $CONTAINERID
   docker image rm ${NAME}
fi
set +x

echo 'build docker image.'
rm -rf docker-build/
mkdir docker-build
cp target/${NAME}-${VERSION}.jar docker-build/app.jar
cp Dockerfile docker-build/
cd docker-build
set -x
docker build -t ${NAME}:${VERSION} .
set +x

echo 'run image.'
set -x
docker run -d -p 9900:9900 ${NAME}:${VERSION}
set +x

echo 'END.'


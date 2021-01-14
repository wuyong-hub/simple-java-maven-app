#!/usr/bin/env bash
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

#self-repo addr
DOCKER_REPO=182.61.138.254/library

IMAGE_VERSION="$(date +'%Y%m%d%H%M%S')_$RANDOM"

echo 'remove old image.'
set -x
CONTAINERID=`docker ps -a | grep ${NAME} | awk '{print $1}'`
if test -n "$CONTAINERID"
then
   docker stop $CONTAINERID
   docker container rm $CONTAINERID
   IMAGEID=`docker images | grep ${NAME} | awk '{print $3}'`
   docker image rm $IMAGEID
fi
set +x

echo 'build docker image and push to repository.'
rm -rf docker-build/
mkdir docker-build
cp target/${NAME}-${VERSION}.jar docker-build/app.jar
cp Dockerfile docker-build/
cd docker-build
set -x
DOCKER_NAME="$DOCKER_REPO/${NAME}:$IMAGE_VERSION"
docker build -t $DOCKER_NAME .
docker login -u admin -p Harbor12345 182.61.138.254
docker push $DOCKER_NAME
set +x

echo 'edit k8s config file'
set -x
cd ../
sed "s#IMAGE-TAG#${DOCKER_NAME}#g" kubernetes-conf.yaml.tpl > kubernetes-conf.yaml
set +x

echo 'END DEPLOY.'


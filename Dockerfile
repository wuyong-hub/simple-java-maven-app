FROM openjdk:8-jdk-alpine
MAINTAINER Wuyong
VOLUME /tmp
ADD app.jar app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
# Use latest openjdk:8 image as the base
FROM openjdk:8-jdk-alpine

MAINTAINER Steffen Kory <stekodyne@gmail.com>
EXPOSE 8080
VOLUME /tmp
ADD target/camel-spring-docker-1.0.0-SNAPSHOT.jar app.jar
ADD Docker/install.sh install.sh
RUN sh -c 'apk add --update openssl'
RUN sh -c 'sh /install.sh'
RUN sh -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]

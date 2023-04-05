FROM openjdk:11 as build

MAINTAINER Ramit Sharma "ramit21"

EXPOSE 8080

WORKDIR /usr/local/bin/

COPY maven/docker-demo-0.0.1-SNAPSHOT.jar webapp.jar

CMD	["java", "-jar", "webapp.jar"]


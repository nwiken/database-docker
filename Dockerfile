# Dockerfile
FROM openjdk:8-jdk

# Add Maintainer Info
LABEL maintainer="niklas.wiken@gmail.com"

# Add a volume pointing to /tmp
VOLUME /tmp

WORKDIR /src/app

# Make port 8080 available to the world outside this container
EXPOSE 8080

# The application's jar file
COPY target/*.jar /src/app/database.jar
COPY wait-for-it.sh /src/app/wait-for-it.sh

# Run the jar file
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=docker", "database.jar"]
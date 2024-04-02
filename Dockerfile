FROM ubuntu:latest
LABEL authors="kevin"

# Use a base image with OpenJDK
FROM openjdk:11-jre-slim

# Set the working directory in the container
WORKDIR /app

# Copy the packaged Spring Boot application JAR file into the container
COPY target/backTienda-0.0.1-SNAPSHOT.jar /app/backTienda-0.0.1-SNAPSHOT.jar

# Expose the port that your Spring Boot application uses (default is 8080)
EXPOSE 5000

# Command to run the Spring Boot application when the container starts
CMD ["java", "-jar", "backTienda-0.0.1-SNAPSHOT.jar"]

FROM ubuntu:latest
LABEL authors="kevin"

# Use a base image with OpenJDK
FROM openjdk:11-jre-slim

# Set the working directory in the container
WORKDIR /app

# Copy the packaged Spring Boot application JAR file into the container
COPY target/my-application.jar /app

# Expose the port that your Spring Boot application uses (default is 8080)
EXPOSE 5000

# Copiar el archivo JAR de la aplicación al contenedor

# Comando para ejecutar la aplicación Spring Boot
CMD ["java", "-jar", "my-application.jar"]

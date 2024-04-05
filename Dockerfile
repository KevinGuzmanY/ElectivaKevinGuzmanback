FROM ubuntu:latest
LABEL authors="kevin"

FROM openjdk:11-jre-slim

WORKDIR /app

COPY target/backendsft3.jar /app

EXPOSE 5000

CMD ["java", "-jar", "backendsft3.jar"]

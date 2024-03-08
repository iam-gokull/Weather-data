FROM maven:3.8.4-openjdk-11 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn -B -DskipTests clean package

# Start with a base image
FROM adoptopenjdk/openjdk11:alpine-jre
# Set the working
WORKDIR /app
# Copy the Spring Boot application JAR file to the container
COPY target/weather-data-0.0.1-SNAPSHOT.jar app.jar
# Expose the application's port
EXPOSE 8080
# Start the application
ENTRYPOINT ["java","-jar","app.jar"]
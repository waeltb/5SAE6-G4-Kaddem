# Use an official Java 11 runtime as a parent image
FROM openjdk:8-jdk-alpine

WORKDIR /app

# Copy the application JAR file from the target directory into the container
COPY target/kaddem-0.0.1-SNAPSHOT.jar kaddem.jar

# Expose a port (change the port number if necessary)
EXPOSE 8080

# Define the command to run the application when the container starts
CMD ["java", "-jar", "kaddem.jar"]
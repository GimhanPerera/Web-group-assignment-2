# Use a base image with just Java
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the existing JAR file into the container
COPY target/WebProject4-0.0.1.jar app.jar

# Copy the history.html file into the container
COPY src/main/resources/static/history.html history.html
COPY src/main/resources/static/styles.css styles.css

# Expose the port the app runs on
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "app.jar"]



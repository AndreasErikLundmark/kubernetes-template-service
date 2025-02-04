# First stage: Build the app using Maven
FROM maven:3.8.7-openjdk-18-slim AS build

# Set the working directory inside the container
WORKDIR /app

# Copy pom.xml from the root into /app
COPY pom.xml /app/

# Copy the source code into /app/src
COPY src/ /app/src/

# Run Maven clean package (skip tests)
RUN mvn clean package -DskipTests

# Second stage: Copy the JAR file to the new image based on openjdk
FROM openjdk:18-slim

WORKDIR /app

# Copy the built JAR file from the build stage into /app
COPY --from=build /app/target/kubernetes.setup-0.0.1-SNAPSHOT.jar /app/kubernetes.setup.jar

# Expose port 8080 for the application
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "/app/kubernetes.setup.jar"]
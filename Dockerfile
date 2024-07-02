# Step 1: Build the application using Gradle
FROM gradle:7.6-jdk17 AS build

# Set the working directory
WORKDIR /app

# Copy the Gradle wrapper and configuration files
COPY gradle /app/gradle
COPY gradlew /app/gradlew
COPY build.gradle.kts /app/
COPY settings.gradle.kts /app/

# Copy the source code
COPY src /app/src

# Build the application
RUN ./gradlew build --no-daemon

# Step 2: Create the actual Docker image to run the application
FROM eclipse-temurin:17-jre

# Set the working directory
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/build/libs/*.jar app.jar

# Expose the application port
EXPOSE 9090

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]

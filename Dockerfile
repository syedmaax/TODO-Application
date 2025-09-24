# Use an official OpenJDK 21 image with Maven as a build environment
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copy project files
COPY pom.xml .
COPY src ./src

# Build the project without running tests
RUN mvn clean package -DskipTests

# Use a smaller JRE 21 image for running the app
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copy the built JAR from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose port 8080 (Render default)
EXPOSE 8080

# Set environment variables for Java
ENV JAVA_OPTS=""

# Run the application
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
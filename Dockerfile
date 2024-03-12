# Use Maven to build the application
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the rest of the source code and build the application
COPY src ./src
RUN mvn clean package -DskipTests

# Use a slim OpenJDK image as the base for the final image
FROM openjdk:17.0.1-jdk-slim
WORKDIR /app

# Copy the JAR file built in the previous stage
COPY --from=build /app/target/restapi-0.0.1-SNAPSHOT.jar restapi.jar

# Expose port 8080 and specify the entry point for the application
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "restapi.jar"]

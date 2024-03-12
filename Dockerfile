FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline

# Debugging: Print current working directory
RUN pwd

# Debugging: List all files and directories in current directory
RUN ls -la

COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim
WORKDIR /app
COPY --from=build /app/target/restapi-0.0.1-SNAPSHOT.jar restapi.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "restapi.jar"]

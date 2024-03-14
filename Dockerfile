FROM openjdk:17-jdk

COPY target/restapi.jar .

EXPOSE 8080

ENTRYPOINT [ "java","-jar", "restapi.jar" ]
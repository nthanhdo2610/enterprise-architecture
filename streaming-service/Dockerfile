FROM openjdk:17
EXPOSE ${SERVER_PORT}
COPY target/*.jar streaming-service.jar
ENTRYPOINT ["java", "-jar", "/streaming-service.jar"]
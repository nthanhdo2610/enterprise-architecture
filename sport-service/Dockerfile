FROM openjdk:17
EXPOSE ${SERVER_PORT}
COPY target/*.jar sport-service.jar
ENTRYPOINT ["java", "-jar", "/sport-service.jar"]
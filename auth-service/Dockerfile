FROM openjdk:17
EXPOSE ${SERVER_PORT}
COPY target/*.jar auth-service.jar
ENTRYPOINT ["java", "-jar", "/auth-service.jar"]
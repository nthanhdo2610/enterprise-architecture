FROM openjdk:17
EXPOSE ${SERVER_PORT}
COPY target/*.jar member-service.jar
ENTRYPOINT ["java", "-jar", "/member-service.jar"]
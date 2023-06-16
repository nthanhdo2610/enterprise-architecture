FROM openjdk:17
VOLUME /tmp
EXPOSE 8082
COPY target/*.jar sport-service.jar
ENTRYPOINT ["java", "-jar", "/sport-service.jar"]
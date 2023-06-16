FROM openjdk:17
VOLUME /tmp
EXPOSE 8080
COPY target/*.jar auth-service.jar
ENTRYPOINT ["java", "-jar", "/auth-service.jar"]
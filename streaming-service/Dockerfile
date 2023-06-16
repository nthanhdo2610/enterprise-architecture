FROM openjdk:17
VOLUME /tmp
EXPOSE 8083
COPY target/*.jar live-service.jar
ENTRYPOINT ["java", "-jar", "/live-service.jar"]
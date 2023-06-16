FROM openjdk:17
VOLUME /tmp
EXPOSE 8081
COPY target/*.jar member-service.jar
ENTRYPOINT ["java", "-jar", "/member-service.jar"]
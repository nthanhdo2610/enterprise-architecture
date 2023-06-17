FROM openjdk:17
ARG JAR_FILE
WORKDIR /app
COPY target/${JAR_FILE} ${JAR_FILE}
EXPOSE ${SERVER_PORT}
CMD ["java", "-jar", "${JAR_FILE}"]
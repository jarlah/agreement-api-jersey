FROM openjdk:19-alpine
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-cp","/app.jar", "com.example.demo.Main"]
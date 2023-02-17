FROM openjdk:17-alpine
VOLUME /tmp
COPY todo-api-v3-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
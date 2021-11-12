FROM openjdk:8-jre-alpine3.7
COPY /build/libs/demo-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["sh", "-c", "java -jar app.jar"]

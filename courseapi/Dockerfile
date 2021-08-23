FROM openjdk:11-slim
WORKDIR /app
COPY ./target/courseapi-0.0.1-SNAPSHOT.jar /app/courseapi.jar
EXPOSE 8080
CMD "java" "-jar" "/app/courseapi.jar"
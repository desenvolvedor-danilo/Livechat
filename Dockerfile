FROM openjdk:21-slim
COPY . .
EXPOSE 5000
COPY /target/living-chatting-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
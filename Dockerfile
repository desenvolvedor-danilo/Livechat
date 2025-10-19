FROM openjdk:21
COPY . .
EXPOSE 8080
COPY /target/living_chatting-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]

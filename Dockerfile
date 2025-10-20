FROM openjdk:21
COPY . .
COPY /target/living_chatting-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]

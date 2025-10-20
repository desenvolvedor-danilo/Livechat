FROM ubuntu:latest
RUN command apt update 
RUN apt upgrade -y
RUN apt install maven -y
RUN apt install openjdk-21-jdk -y
RUN mvn clean install -DskipTests
COPY . .
EXPOSE 8080
COPY /target/living_chatting-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]

FROM ubuntu:latest
RUN command apt update 
RUN apt-get upgrade -y
RUN apt-get install maven -y
RUN apt-get install openjdk-21-jdk -y
COPY . .
RUN mvn clean install -DskipTests
EXPOSE 8080
COPY /target/living_chatting-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]

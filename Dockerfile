FROM ubuntu:latest
RUN apt update && apt install -y openjdk-21-jdk maven
COPY . .
RUN mvn clean package
EXPOSE 5000
COPY /target/living-chatting-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
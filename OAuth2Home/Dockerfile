FROM openjdk:17.0-slim
COPY target/*.jar security.jar
EXPOSE 8080
ENTRYPOINT ["java","-java","/security.jar"]

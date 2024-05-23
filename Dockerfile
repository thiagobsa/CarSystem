FROM openjdk:11-jre-slim
WORKDIR /app
COPY target/CarSystem.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
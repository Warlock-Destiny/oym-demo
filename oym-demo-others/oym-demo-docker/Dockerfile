FROM openjdk:8-jdk-alpine
ADD ./target/oym-demo-docker-1.0.0.jar app.jar
EXPOSE 9000
ENTRYPOINT ["java","-jar","/app.jar" ]
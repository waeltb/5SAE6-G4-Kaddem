FROM openjdk:8-jdk-alpine
EXPOSE 8060
ADD target/kaddem-1.0.jar   kaddem-1.0.jar
ENTRYPOINT ["java","-jar","/kaddem-1.0.jar"]

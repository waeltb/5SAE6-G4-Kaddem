FROM openjdk:8-jdk-alpine
EXPOSE 8060
ADD target/kaddem-1.0.jar   hedilsaidani-5sae6-g4-kaddem.jar
ENTRYPOINT ["java","-jar","/hedilsaidani-5sae6-g4-kaddem.jar"]

FROM openjdk:8-jdk-alpine
EXPOSE 8060
ADD target/kaddem-1.0.jar   HEDILSAIDANI-5SAE6-G4-kaddem.jar
ENTRYPOINT ["java","-jar","/HEDILSAIDANI-5SAE6-G4-kaddem.jar"]

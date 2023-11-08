FROM openjdk:8
EXPOSE 8089
ADD target/kaddem-0.0.1-SNAPSHOT.jar waeltalbi-sae6-g4-kaddem-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar","/waeltalbi-sae6-g4-kaddem-0.0.1-SNAPSHOT.jar"]

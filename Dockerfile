FROM openjdk:8
EXPOSE 8089
ADD target/kaddem-0.0.1-SNAPSHOT.jar projetdevops-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar","/projetdevops-0.0.1-SNAPSHOT.jar"]
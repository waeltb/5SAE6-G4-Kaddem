pipeline {
    agent any
    tools {
        jdk 'JAVA_HOME'
        maven 'M2_HOME'
    }
    environment {
        NEXUS_VERSION = "nexus3"
        NEXUS_PROTOCOL = "http"
        NEXUS_URL = "192.168.3.44:8081"
        NEXUS_REPOSITORY = "nexus_repo"
        NEXUS_CREDENTIAL_ID = "nexus_cred"
    }
    stages {
        stage('git clone'){
            steps {
                git branch: 'ounimohamed-5SAE6-G4', changelog: false, poll: false, url: 'https://github.com/waeltb/5SAE6-G4-Kaddem.git'
                sh 'mvn clean'
                sh 'mvn install'


            }
        }

        stage('test'){
            steps{
                sh 'mvn test'

            }
        }


        stage("sonar"){
            steps {
                sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=x -Dsonar.token=SonarQube'
            }
        }

        stage("add artifact to Nexus Repository ") {
            steps {
                script {
                    pom = readMavenPom file: "pom.xml";
                    filesByGlob = findFiles(glob: "target/*.${pom.packaging}");
                    echo "${filesByGlob[0].name} ${filesByGlob[0].path} ${filesByGlob[0].directory} ${filesByGlob[0].length} ${filesByGlob[0].lastModified}"
                    artifactPath = filesByGlob[0].path;
                    artifactExists = fileExists artifactPath;
                    if(artifactExists) {
                        echo "* File: ${artifactPath}, group: ${pom.groupId}, packaging: ${pom.packaging}, version ${pom.version}";
                        nexusArtifactUploader(
                            nexusVersion: NEXUS_VERSION,
                            protocol: NEXUS_PROTOCOL,
                            nexusUrl: NEXUS_URL,
                            groupId: "pom.tn.esprit.spring",
                            version: "pom.0.0.1-SNAPSHOT",
                            repository: NEXUS_REPOSITORY,
                            credentialsId: NEXUS_CREDENTIAL_ID,
                            artifacts: [
                                [artifactId: "pom.kaddem",
                                classifier: '',
                                file: artifactPath,
                                type: pom.packaging],
                                [artifactId: "pom.kaddem",
                                classifier: '',
                                file: "pom.xml",
                                type: "pom"]
                            ]
                        );
                    } else {
                        error "* File: ${artifactPath}, could not be found";
                    }
                 sh 'docker stop mysql'

                }

            }
        }



         stage('build image'){
            steps{
               sh 'docker build -t ounimohamed/kaddem .'
            }
        }

        stage('push to docker hub'){
            steps{
                script{
                    withCredentials([string(credentialsId: 'dockerhub', variable: 'dockerpwd')]) {
                     sh 'docker login -u ounimohamed -p ${dockerpwd}'

}
                     sh 'docker push ounimohamed/kaddem'
                }
            }
        }

         stage('Docker Compose') {
            steps {
                script {

                   sh 'docker compose up -d'
              }
            }
          }



}
}

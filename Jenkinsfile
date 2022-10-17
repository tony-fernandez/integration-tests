pipeline {
    agent any
    tools {
        maven 'MAVEN_HOME'
        jdk 'JAVA_HOME'
    }
    stages {
        stage("Initialise") {
            steps {
                sh '''
                   echo "PATH = ${PATH}"
                   echo "M2_HOME = ${M2_HOME}"
                '''
            }
        }
        stage("Build Maven") {
            steps {
                sh 'mvn -B clean package'
            }
        }
        stage("Run Gatling") {
            steps {
                sh 'mvn gatling:test'
            }
            post {
                always {
                    gatlingArchive()
                }
            }
        }
    }
}
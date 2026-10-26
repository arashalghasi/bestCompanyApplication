#!/usr/bin/env groovy
pipeline {
    agent any
    stages {
        stage('Build') { 
            steps {
                sh 'sh mvnw -B -DskipTests clean package'
            }
        }
    }
}

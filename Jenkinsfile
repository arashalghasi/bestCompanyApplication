#!/usr/bin/env groovy
pipeline {
    agent any
    stages {
        stage('Build') { 
            steps {
				sh 'cd backend'
                sh 'mvn -B -DskipTests clean package' 
            }
        }
    }
}

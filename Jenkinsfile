#!/usr/bin/env groovy
pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh 'sh mvnw -B -DskipTests clean package'
            }
        }
        stage('Test') {
            steps {
                // ponytail: repeats npm ci + ng build (~13 s), surefire:test alone cannot resolve the frontend jar
                sh 'sh mvnw -B test'
            }
            post {
                always {
                    // junit is a Jenkins step, not a Maven goal; reports sit in each module's target folder
                    junit testResults: '**/target/surefire-reports/*.xml', allowEmptyResults: true
                }
            }
        }
    }
}

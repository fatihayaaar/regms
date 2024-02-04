pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                script {
                    git branch: 'main',
                    credentialsId: '2228b488-e0c8-41ab-8795-37afc7022a8b',
                    url: 'https://github.com/fatihayaaar/regms.git'
                }
            }
        }

        stage('Build') {
            steps {
                script {
                    sh 'chmod +x gradlew'
                }
                script {
                    sh './gradlew :config-server:build'
                }
                script {
                    sh './gradlew :eureka-server:build'
                }
                script {
                    sh './gradlew :gateway-server:build'
                }
                script {
                    sh './gradlew :user-service:build'
                }
            }
        }

        stage('Deploy') {
            steps {
                script {
                    sh 'docker-compose up -d'
                }
            }
        }
    }
}
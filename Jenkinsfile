pipeline {
    agent any

    stages {

        stage('Building') {
            steps {
                bat 'mvn clean package'
            }
        }

        stage('Running Unit Tests') {
            steps {
                script {
                    bat 'mvn test'
                }
            }
        }

        stage('Cleanup Containers') {
            steps {
                script {
                    bat 'docker-compose down || exit 0'
                    bat 'docker stop postgreswarehouse || exit 0'
                    bat 'docker rm postgreswarehouse || exit 0'
                    bat 'docker stop spring-app || exit 0'
                    bat 'docker rm spring-app || exit 0'
                }
            }
        }

        stage('Build and Deploy') {
            steps {
                bat 'docker-compose up -d'
            }
        }
    }
}

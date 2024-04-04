pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                bat 'mvn clean package'
                bat 'docker-compose up -d' // Construir el proyecto Spring Boot
            }
        }
    }
}

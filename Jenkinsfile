pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                bat 'docker-compose down'
                bat 'docker stop postgreswarehouse' // Detener el contenedor existente
                bat 'docker rm postgreswarehouse'
                bat 'docker stop spring-app' // Detener el contenedor existente
                bat 'docker rm spring-app'
                bat 'mvn clean package'
                bat 'docker-compose up -d' // Construir el proyecto Spring Boot
            }
        }
    }
}

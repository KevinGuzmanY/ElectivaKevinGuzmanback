pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh 'mvn clean package' // Construir el proyecto Spring Boot
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test' // Ejecutar pruebas
            }
        }
        stage('Build Docker Image') {
            steps {
                script {
                    docker.build('my-application') // Construir imagen Docker
                }
            }
        }
        stage('Deploy') {
            steps {
                sh 'docker-compose up -d' // Desplegar la aplicación utilizando docker-compose
            }
        }
    }
}

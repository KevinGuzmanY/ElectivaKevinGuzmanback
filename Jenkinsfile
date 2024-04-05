pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                bat 'docker-compose down || exit 0'
                bat 'docker stop postgreswarehouse || exit 0' // Detener el contenedor existente
                bat 'docker rm postgreswarehouse || exit 0'
                bat 'docker stop spring-app || exit 0' // Detener el contenedor existente
                bat 'docker rm spring-app || exit 0'
                bat 'mvn clean package'
                 script {
                            docker.build('backendsft3') // Construir imagen Docker
                        }
                bat 'docker-compose up -d' // Construir el proyecto Spring Boot
            }
        }
    }
}

pipeline {
    agent any

    stages {

        stage('Build Maven') {
            steps {
                sh 'cd ESD_Backend && mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh '''
                cd ESD_Backend/docker
                docker compose build
                '''
            }
        }

        stage('Deploy') {
            steps {
                sh '''
                cd ESD_Backend/docker
                docker compose down
                docker compose up -d
                '''
            }
        }
    }
}

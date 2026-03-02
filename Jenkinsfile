pipeline {
    agent any

    stages {

        stage('Clone') {
            steps {
                git 'https://github.com/Poojan-Pandyaa/ESD_MiniProject.git'
            }
        }

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

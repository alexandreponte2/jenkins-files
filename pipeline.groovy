pipeline {
    agent any
    triggers { pollSCM('* * * * *') }

    stages {
        stage('Chekout') {
            steps {
                git url: 'https://github.com/alexandreponte2/jgsu-spring-petclinic.git', branch: 'main'
            }
        }
        stage('Build') {
            steps{        
                sh './mvnw package'
            }
            
            post {
                always {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
                
            }
        }
    }
}
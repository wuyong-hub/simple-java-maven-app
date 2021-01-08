pipeline {
    agent any
	
    stages {
        stage('Build') {
            steps {
                sh '/usr/local/apache-maven-3.6.3/mvn -B -DskipTests clean package'
            }
        }
        stage('Test') {
            steps {
                sh '/usr/local/apache-maven-3.6.3/mvn test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
         stage('Deploy') {
            steps {
                echo "Deploy stage: ..." 
            }
        }
        stage('Deliver') {
            steps {
                sh './jenkins/scripts/deliver.sh'
            }
        }
    }
}

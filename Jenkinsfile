pipeline {
    agent any
	
    stages {
        stage('Build') {
            steps {
                sh '/usr/local/apache-maven-3.6.3/bin/mvn -B -DskipTests clean package'
            }
        }
        stage('Test') {
            steps {
                sh '/usr/local/apache-maven-3.6.3/bin/mvn test'
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

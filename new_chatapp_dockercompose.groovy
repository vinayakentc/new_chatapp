



pipeline {
    environment {
        PATH = "$PATH:/home/ubuntu/.local/bin/docker-compose"
    }
    agent any
    stages {
	stage('SCM Checkout'){
	    steps{
	        git 'https://github.com/vinayakentc/new_chatapp.git'
	    }
        }
    stage('Build') { 
          steps{ 
            sh "ssh  ubuntu@10.200.0.104  sudo rm -rf /home/ubuntu/Backend/new_chatapp "
            sh "rsync -avzP --rsh=ssh   /var/lib/jenkins/workspace/* ubuntu@10.200.0.104:/home/ubuntu/Backend/"
    	  }
    	}
		
		stage('Build Image') {
			steps {
				sh "ssh ubuntu@10.200.0.104 docker stop frontend backend mysqldb "
				sh "ssh ubuntu@10.200.0.104 docker rm -f frontend backend mysqldb "
				sh "ssh ubuntu@10.200.0.104 docker rmi -f backendimg_1:latest "
			}
		}
		stage('Deploy') {
			steps {
			    echo "PATH is: $PATH"

			    sh "ssh ubuntu@10.200.0.104 /home/ubuntu/.local/bin/docker-compose up -d "

			}
		}
    
    }
}


pipeline {

    agent any
    stages {
	stage('SCM Checkout'){
	    steps{
	        git 'https://github.com/vinayakentc/new_chatapp.git'
	    }
        }
    stage('Build') { 
          steps{ 
            sh "ssh  ubuntu@10.200.0.192  sudo rm -rf /home/ubuntu/Backend/new_chatapp "
            sh "rsync -avzP --rsh=ssh   /var/lib/jenkins/workspace/new_chatapp ubuntu@10.200.0.192:/home/ubuntu/Backend/"
    	  }
    	}
		
		stage('Remove Data') {
			steps {
			    sh "ssh ubuntu@10.200.0.192 docker stop frontend backend mysqldb "
				sh "ssh ubuntu@10.200.0.192 docker rm -f frontend backend mysqldb "
				sh "ssh ubuntu@10.200.0.192 docker rmi -f backendimg_1:latest "
				sh "ssh ubuntu@10.200.0.192 docker rmi -f databaseimg_2:latest "


			}
		}
        stage('Deploy') {
			steps {

				sh "ssh ubuntu@10.200.0.192 'bash /home/ubuntu/Backend/new_chatapp/scripts/start.sh' " 

			}
		}
    
    }
}

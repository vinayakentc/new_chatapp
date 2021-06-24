pipeline {
    agent any
    stages {
	stage('git clone'){
	    steps{
	        git 'https://github.com/vinayakentc/new_chatapp.git'
	    }
        }
    stage('Build') { 
          steps{ 
            sh "ssh  ubuntu@10.200.0.104  sudo rm -rf /home/ubuntu/Backend/new-chatapp "
	    sh "ssh  ubuntu@10.200.0.104  sudo rm -rf /home/ubuntu/Backend/new_chatapp "
            sh "rsync -avzP --rsh=ssh   /var/lib/jenkins/workspace/* ubuntu@10.200.0.104:/home/ubuntu/Backend/"
    	  }
    	}
    stage('Deploy') { 
          steps{ 
            sh "ssh  ubuntu@10.200.0.104   docker restart backend "
    	  }
    	}

    }
}

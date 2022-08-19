
//calling the function

def call() {
pipeline {
    agent any
    environment{
        SONAR = credentials('sonar')
    }
    stages {
       stage('Lint check') {
          steps {
            script{
              lintcheck() 
            }
          }
       }
    }
}
}

//declaring the function
def lintcheck()
{
  sh '''
  echo -e "******started link check for ${COMPONENT}******"
  mvn checkstyle:check || true
  echo "******lint check completed for ${COMPONENT}********"
   '''
}

def sonarcheck()
{ 
    sh '''
    sonar-scanner -Dsonar.host.url=http://172.31.15.137:9000 -Dsonar.login=admin -Dsonar.password=DevOps321 -Dsonar.projectKey=shipping -Dsonar.java.binaries=target/classes/
    '''
}
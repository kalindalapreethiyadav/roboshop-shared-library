//declaring the function
def lintcheck()
{
  sh '''
  echo -e "******started link check for ${COMPONENT}******"
  pwd
  mvn checkstyle:check || true
  echo "******lint check completed for ${COMPONENT}********"
   '''
}

def sonarcheck()
{ 
    sh '''
    echo "hellp"
    #sonar-scanner -Dsonar.host.url=http://172.31.5.148:9000 -Dsonar.login=${SONAR_USR} -Dsonar.password=${SONAR_PSW} -Dsonar.projectKey=shipping -Dsonar.java.binaries=target/classes/
    '''
}
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
       stage('Sonar Code check') {
          steps {
            script{
              sonarcheck() 
            }
          }
       }
    }
}
}


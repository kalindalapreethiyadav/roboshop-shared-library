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
    echo "hello"
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


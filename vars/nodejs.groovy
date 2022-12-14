
//calling the function

def call() {
pipeline {
    agent any
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
  echo "******started link check for ${COMPONENT}******"
  npm install jslint
  ls -lrt node_modules/jslint/bin
  ~/node_modules/jslint/bin/jslint.js server.js || true
   echo "******lint check completed for ${COMPONENT}********"
   '''
}

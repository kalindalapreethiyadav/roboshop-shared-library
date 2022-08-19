
//calling the function

def call() {
pipeline {
    agent any
    stages {
       stage('Lint check') {
          steps {
            script{
              nodejs.lintcheck(COMPONENT) 
            }
          }
       }
    }
}
}

//declaring the function
def lintcheck(COMPONENT)
{
  sh "echo ******started link check******"
  sh "npm install jslint"
  sh "ls -lrt node_modules/jslint/bin"
  sh "~/node_modules/jslint/bin/jslint.js server.js || true"
  sh "echo ******lint check completed********"
}

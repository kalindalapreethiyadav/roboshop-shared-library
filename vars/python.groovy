
//calling the function

def call() {
pipeline {
    agent any
    stages {
       stage('Lint check') {
          steps {
            script{
              lintcheck(COMPONENT) 
            }
          }
       }
    }
}
}

//declaring the function
def lintcheck(COMPONENT)
{
  sh '''
  echo -e "******started link check for ${COMPONENT}******"
  pylint *.py || true
  echo -e "******lint check completed for ${COMPONENT}********"
   '''
}
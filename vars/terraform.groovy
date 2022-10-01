def call() {

// If the env.VARIABLE is null ( for DB's) it will add the past as pwd i.e, ./
   if(!env.TERRAFORM_DIR)  {
        env.TERRAFORM_DIR="./"
   }

    properties([
        parameters([
            choice(choices: 'dev\nprod', description: "Chose the Env", name: "ENV"),
            choice(choices: 'apply\ndestroy', description: "Choose apply or destroy", name: "ACTION"),
            string(choices: 'APP_VERSION', description: "Chose APP_VERSION: Option only valid for APPS", name: "APP_VERSION"),
        ]),
    ])

    node {
        ansiColor('xterm') {  // This plugin is for colors. You need to install this in the MANAGE PLUGINS
        sh "rm -rf *"
        git branch: 'main', url: "https://github.com/b49-clouddevops/${REPONAME}"
        stage('terraform init'){
            sh ''' 
                cd ./${TERRAFORM_DIR}
                terrafile -f env-${ENV}/Terrafile
                terraform init -backend-config=env-${ENV}/${ENV}-backend.tfvars
            '''
        }

        stage('terraform plan') {
            sh '''
                cd ${TERRAFORM_DIR}
                export TF_VAR_APP_VERSION=${APP_VERSION}
                terraform plan -var-file=env-${ENV}/${ENV}.tfvars
            '''
        }

        stage('terraform apply') {
            sh ''' 
                cd ${TERRAFORM_DIR}
                export TF_VAR_APP_VERSION=${APP_VERSION}
                terraform ${ACTION} -var-file=env-${ENV}/${ENV}.tfvars -auto-approve
            '''
            }
        }
    }
}
def call() {
    node {
        git branch: 'main', url: "https://github.com/b49-clouddevops/${COMPONENT}"
        env.APP_TYP = "nodejs"
        common.lintChecks()
        common.sonarCheck()
        common.testCases()
        if(env.TAG_NAME != null) {
        common.artifacts()
        }
    }
}
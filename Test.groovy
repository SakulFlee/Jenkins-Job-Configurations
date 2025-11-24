// Declerative_Kubernetes_Test.groovy
pipelineJob('declarative-test-job') {
    description('Kubernetes deployment test using Declarative Pipeline syntax.')

    definition {
        // Defines the Pipeline script type
        cps {
            // Embeds the Declarative Pipeline script as a literal string
            script("""
pipeline {
    agent any

    stages {
        stage('Hello') {
            steps {
                echo 'Hello World'
            }
        }
    }
}
            """.stripIndent()) // The stripIndent() cleans up leading whitespace
        }
    }
    
    // Add any necessary build triggers for the job (optional)
    triggers {
        // e.g., scm('H/5 * * * *') or cron('30 7 * * 1-5')
    }
}
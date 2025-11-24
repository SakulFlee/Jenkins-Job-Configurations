// Declerative_Kubernetes_Test.groovy
pipelineJob('declarative-k8s-test-job') {
    description('Kubernetes deployment test using Declarative Pipeline syntax.')

    definition {
        // Defines the Pipeline script type
        cps {
            // Embeds the Declarative Pipeline script as a literal string
            script("""
// Uses Declarative syntax to run commands inside a container.
pipeline {
    agent {
        kubernetes {
            // Rather than inline YAML, in a multibranch Pipeline you could use: yamlFile 'jenkins-pod.yaml'
            // Or, to avoid YAML:
            // containerTemplate {
            //     name 'shell'
            //     image 'ubuntu'
            //     command 'sleep'
            //     args 'infinity'
            // }
            yaml '''
apiVersion: v1
kind: Pod
spec:
  containers:
  - name: shell
    image: ubuntu
    command:
    - sleep
    args:
    - infinity
    securityContext:
      # ubuntu runs as root by default, it is recommended or even mandatory in some environments (such as pod security admission "restricted") to run as a non-root user.
      runAsUser: 1000
'''
            // Can also wrap individual steps:
            // container('shell') {
            //     sh 'hostname'
            // }
            defaultContainer 'shell'
            retries 2
        }
    }
    stages {
        stage('Main') {
            steps {
                sh 'hostname'
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
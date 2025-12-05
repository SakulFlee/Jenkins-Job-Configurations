podTemplate(label: "test",
        containers: [
                containerTemplate(name: 'alpine', image: 'alpine:3.11', ttyEnabled: true, command: 'cat'),
                containerTemplate(name: 'python', image: 'python:3.15.0a2-trixie', ttyEnabled: true, command: 'cat'),
                containerTemplate(name: 'java', image: '8u472-b08-jre-ubi9-minimal', ttyEnabled: true, command: 'cat'),
        ],
        ) {
    node("test") {
        stage('Run shell') {
            container('alpine') {
                sh 'echo "hello world"'
            }
        }
        stage('Python version') {
            container('python') {
                sh 'python --version'
            }
        }
        stage('Java version') {
            container('java') {
                sh 'java --version'
            }
        }
    }
}
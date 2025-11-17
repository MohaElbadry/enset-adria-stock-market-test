pipeline {
  agent any
  environment {
    REGISTRY = "enset"
  }
  stages {
    stage('Checkout') {
      steps { checkout scm }
    }
    stage('Build Maven') {
      steps { sh 'mvn -B -DskipTests clean package' }
    }
    stage('Build Docker Images') {
      steps {
        sh 'docker build -t $REGISTRY/discovery-service:latest discovery-service'
        sh 'docker build -t $REGISTRY/gateway-service:latest gateway-service'
        sh 'docker build -t $REGISTRY/company-service:latest company-service'
        sh 'docker build -t $REGISTRY/stock-service:latest stock-service'
        sh 'docker build -t $REGISTRY/chatbot-service:latest chatbot-service'
      }
    }
    stage('Compose Up (dev)') {
      when { expression { return params.DEPLOY_DEV == true } }
      steps { sh 'docker compose up -d' }
    }
  }
  parameters {
    booleanParam(name: 'DEPLOY_DEV', defaultValue: false, description: 'Bring up docker-compose after build')
  }
  post {
    always { sh 'docker images | head -n 20 || true' }
  }
}

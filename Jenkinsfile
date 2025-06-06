def GITHUB_TOKEN = credentials('github-auth')
def POD_LABEL = 'kaniko'
    node(POD_LABEL) {
        def DOCKER_IMAGE_NAME = "gwrgwr/technova-ms-email:${env.BUILD_ID}"
        def DOCKER_IMAGE_NAME_LATEST = "gwrgwr/technova-ms-email:latest"
        stage('Checkout') {
            checkout scm
        }

        stage('Build with Kaniko') {
            container('kaniko') {
               sh '''#!/busybox/sh
                /kaniko/executor \
                  --context `pwd` \
                  --build-arg GITHUB_TOKEN=$GITHUB_TOKEN \
                  --dockerfile=./Dockerfile \
                  --destination ''' + DOCKER_IMAGE_NAME + ''' \
                  --destination ''' + DOCKER_IMAGE_NAME_LATEST + '''\
                '''
            }
        }

                stage('Checkout Helm Chart') {
                    git url: 'https://github.com/muriloramos-dev/technova-helm.git', branch: 'master', credentialsId: 'github-auth'
                }

                stage('Deploy to Kubernetes') {
                    container('kubectl') {
                        withKubeConfig([credentialsId: 'jenkins-token', namespace: 'jenkins', serverUrl: 'https://b680f6d3-8591-4b5a-a237-696f27bc05b8.k8s.ondigitalocean.com']) {
                                    sh """
                                        helm upgrade --install technova-ms-email ./charts/email/ \
                                        --values values.yaml \
                                        --values charts/email/values.yaml \
                                        --namespace technova \
                                        --set email.image.tag=${env.BUILD_ID} \
                                        --wait
                                        """
                                }
                        }
                }
    }
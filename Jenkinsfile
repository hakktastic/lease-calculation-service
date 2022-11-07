def TAG_SELECTOR = "latest"
def ARTIFACT_ID = "latest"

pipeline {
    agent {
        kubernetes {
            yaml """
        kind: Pod
        spec:
          containers:
          - name: kaniko
            image: gcr.io/kaniko-project/executor:debug
            imagePullPolicy: IfNotPresent
            command:
            - sleep
            args:
            - 9999999
            volumeMounts:
              - name: jenkins-docker-cfg
                mountPath: /kaniko/.docker
              - name: image-cache
                mountPath: /image-cache
          - name: maven
            image: maven:3.8.5-openjdk-17-slim
            imagePullPolicy: IfNotPresent
            command:
            - cat
            tty: true
            volumeMounts:
              - name: m2-cache
                mountPath: /root/.m2
          volumes:
          - name: jenkins-docker-cfg
            projected:
              sources:
              - secret:
                  name: docker-credentials
                  items:
                    - key: .dockerconfigjson
                      path: config.json
          - name: image-cache
            persistentVolumeClaim:
              claimName: kaniko-cache
          - name: m2-cache
            persistentVolumeClaim:
              claimName: maven-m2-cache
        """
        }
    }
    
    stages {
        stage('Build and test with Maven') {

            steps {
                container(name: 'maven') {

                    sh 'mvn clean package'

                    script {
                        TAG_SELECTOR = readMavenPom().getVersion()
                        ARTIFACT_ID = readMavenPom().getArtifactId()
                    }
                }
            }
        }
        stage('Build container image with Kaniko') {


            environment {
                IMG_ID = "${ARTIFACT_ID}"
                IMG_TAG = "${TAG_SELECTOR}"
            }
            steps {

                container(name: 'kaniko', shell: '/busybox/sh') {

                    sh '''#!/busybox/sh
                        /kaniko/executor --context `pwd` --destination hakktastic/${IMG_ID}:${IMG_TAG} --customPlatform=linux/arm64 --cache=true --cache-dir=/image-cache
                    '''
                }
            }
        }
    }
}
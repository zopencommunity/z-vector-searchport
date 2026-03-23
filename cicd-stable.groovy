node('linux') {
  stage ('Poll') {
    checkout([
      $class: 'GitSCM', branches: [[name: '*/main']], extensions: [],
      userRemoteConfigs: [[url: 'https://github.com/zopencommunity/z-vector-searchport.git']]])
  }
  stage('Build') {
    build job: 'Port-Pipeline', parameters: [
      string(name: 'PORT_GITHUB_REPO', value: 'https://github.com/zopencommunity/z-vector-searchport.git'),
      string(name: 'PORT_DESCRIPTION', value: 'A high-performance semantic search tool for z/OS powered by llama.cpp.'),
      string(name: 'BUILD_LINE', value: 'STABLE')
    ]
  }
}

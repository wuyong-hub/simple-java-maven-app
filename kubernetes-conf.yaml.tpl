kind: Deployment 
apiVersion: extensions/v1beta1 
metadata: 
  labels: 
    app: simple-java-maven-app 
  name: simple-java-maven-app 
  namespace: kube-system
spec: 
  replicas: 1 
  selector: 
    matchLabels: 
      app: simple-java-maven-app 
  template: 
    metadata: 
      labels: 
        app: simple-java-maven-app 
      # Comment the following annotation if Dashboard must not be deployed on master 
      annotations: 
        scheduler.alpha.kubernetes.io/tolerations: | 
          [ 
            { 
              "key": "dedicated", 
              "operator": "Equal", 
              "value": "master", 
              "effect": "NoSchedule" 
            } 
          ] 
    spec: 
      containers: 
      - name: simple-java-maven-app 
        image: IMAGE-TAG    #默认的镜像是使用google的，这里改成私有仓库
        imagePullPolicy: Always
        ports: 
        - containerPort: 9900
          protocol: TCP 
        args: 
          # Uncomment the following line to manually specify Kubernetes API server Host 
          # If not specified, Dashboard will attempt to auto discover the API server and connect 
          # to it. Uncomment only if the default does not work. 
          - --apiserver-host=http://182.61.138.254:8080    #注意这里是api的地址 
        livenessProbe: 
          httpGet: 
            path: / 
            port: 9900 
          initialDelaySeconds: 30 
          timeoutSeconds: 30 
--- 
kind: Service 
apiVersion: v1 
metadata: 
  labels: 
    app: simple-java-maven-app 
  name: simple-java-maven-app 
  namespace: kube-system 
spec: 
  type: NodePort 
  ports: 
  - port: 80 
    targetPort: 9900
    nodePort: 30099
  selector: 
    app: simple-java-maven-app 

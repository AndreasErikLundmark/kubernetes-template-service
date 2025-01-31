# kubernetes-template-service

Simple Spring message app that serves a message on URL/api/message

Setup Minikube
https://minikube.sigs.k8s.io/docs/start/?arch=%2Fmacos%2Farm64%2Fstable%2Fhomebrew

#After installing 
minikube start

#Build
docker build -t your-dockerhub-username/kubernetes-setup:latest . 

#Push to docker hub ( log in )
docker push your-dockerhub-username/kubernetes-setup:latest                        

# See deployment.yaml under /infrastructure 
          image: dockerhub-username/kubernetes-setup
          imagePullPolicy: Always

#Run
kubectl apply -f deployment.yaml

# See service.yaml under /infrastructure
kubectl apply -f service.yaml

#Check status 
kubectl get services

#Run
minikube service message-api
Should show somthing like this: 
|-----------|-------------|-------------|---------------------------|
| NAMESPACE |    NAME     | TARGET PORT |            URL            |
|-----------|-------------|-------------|---------------------------|
| default   | message-api |        8080 | http://111.111.11.1:30000 |
|-----------|-------------|-------------|---------------------------|

#Forward port example
kubectl port-forward service/message-api 8080:8080

#install K9 
kubectl config use-context minikube
#Run
k9s






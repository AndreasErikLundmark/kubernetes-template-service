# kubernetes-template-service

Simple Spring message app that serves a message on URL `/api/message`.

#Follow the Minikube installation guide:
[Minikube installation guide](https://minikube.sigs.k8s.io/docs/start/?arch=%2Fmacos%2Farm64%2Fstable%2Fhomebrew)


#Run the following command to start Minikube:
```bash
minikube start 
```
#Build
```bash
docker build -t your-dockerhub-username/kubernetes-setup:latest . 
```
#Push to docker hub ( log in )
```bash
docker push your-dockerhub-username/kubernetes-setup:latest                        
```
### See deployment.yaml under /infrastructure 
          image: dockerhub-username/kubernetes-setup
          imagePullPolicy: Always

#Run
```bash
kubectl apply -f deployment.yaml
```
#See service.yaml under /infrastructure
```bash
kubectl apply -f service.yaml
```
#Check status
```bash
kubectl get services
```

#Run
```bash
minikube service message-api
```
Should show somthing like this: 
```bash
|-----------|-------------|-------------|---------------------------|
| NAMESPACE |    NAME     | TARGET PORT |            URL            |
|-----------|-------------|-------------|---------------------------|
| default   | message-api |        8080 | http://111.111.11.1:30000 |
|-----------|-------------|-------------|---------------------------|
```
###Forward port example
```bash
kubectl port-forward service/message-api 8080:8080
```

###install K9 
```bash
kubectl config use-context minikube
#Run
k9s
```





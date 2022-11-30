# Course kubernetes
Spring-cloud OpenFeign aws Docker Kubernetes
## Docker commands
* docker build -t users . .\msvc-usuarios\Dockerfile

## Minikube to Kubernetes

## AWS-EKS

## Connect dependency Minikube with apps spring-boot
* **Apply on minikube cmd**: **kubectl create clusterrolebinding admin --clusterrole=cluster-admin --serviceaccount=default:default**
  * **To add dependencies on pom.xml:** 
    - spring-cloud-starter-kubernetes-client
    - spring-cloud-starter-kubernetes-client-config
    - spring-cloud-starter-kubernetes-client-loadbalancer
  * **To add properties in application.yaml**
    - management.endpoints.web.exposure.include=*
    - management.endpoint.health.show-details=always
    - management.endpoint.health.probes.enabled=true
    - management.health.livenessstate.enabled=true
    - management.health.readinessstate.enabled=true





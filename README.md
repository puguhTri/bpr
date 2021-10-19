#BPR Microservices

## push to docker hub
### docker tag bpr-eureka-server 369693/ptw-eureka-server
### docker push 369693/ptw-eureka-server


## run container
### docker run -d -p 8010:8010  --name eureka-server bpr-eureka-server
FROM openjdk:11.0.11-jre-slim
COPY target/bpr-discovery-svc-0.0.1-SNAPSHOT.jar DiscoveryService.jar
ENTRYPOINT ["java", "-jar", "DiscoveryService.jar" ]
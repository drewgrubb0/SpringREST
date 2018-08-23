FROM java:8
WORKDIR /opt
ADD SpringAdvertise-0.0.7-SNAPSHOT.jar ./SpringAdvertise-0.0.7-SNAPSHOT.jar
EXPOSE 8080
EXPOSE 8090
CMD ["java", "-jar", "/opt/SpringAdvertise-0.0.7-SNAPSHOT.jar"]

#docker run -p 8080:8080 -p 8090:8090 [imageNum]
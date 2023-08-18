FROM ibm-semeru-runtimes:open-17.0.7_7-jdk
LABEL authors="Karol Sobolewski"

COPY ./target/dispatcher-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

#sudo docker build -t japp .
#sudo docker run -it --rm japp
# sudo docker images
# sudo docker rmi -f b4d003e422c4
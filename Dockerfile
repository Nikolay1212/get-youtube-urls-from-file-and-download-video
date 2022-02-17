FROM openjdk:11
ADD target/downloader.jar downloader.jar
ENTRYPOINT ["java","-jar","downloader.jar"]
EXPOSE 8080
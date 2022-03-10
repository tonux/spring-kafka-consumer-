# jdk
FROM adoptopenjdk/openjdk11:alpine-jre

#maven build -> fileName
ARG JAR_FILE=target/atos-kafka-consumer-0.0.1-SNAPSHOT.jar

# cd /opt/app
WORKDIR /opt/app

# copy file
COPY ${JAR_FILE} app.jar

# java -jar
ENTRYPOINT ["java", "-jar", "app.jar"]
## Dockerfile for Spring Boot microservice
#FROM openjdk:21-jdk
#
## Copy CA cert
#COPY ca.crt /usr/local/share/ca-certificates/ca.crt
#
## Import into Java cacerts truststore
#RUN keytool -import -noprompt -trustcacerts \
# -alias myCA \
# -file C:\Users\MedBo\Documents\S7-ASW\deployment/ca.crt \
# -keystore $JAVA_HOME/lib/security/cacerts \
# -storepass changeit
#
#VOLUME /tmp
#COPY target/*.jar app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]

# Dockerfile for Spring Boot microservice
FROM openjdk:21-jdk
VOLUME /tmp
# Copy your application JAR file
COPY target/*.jar /app.jar
# Run the application
ENTRYPOINT ["java", "-jar", "/app.jar"]

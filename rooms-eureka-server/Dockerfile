FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} rooms-eureka-server.jar
ENTRYPOINT ["java","-jar","/rooms-eureka-server.jar"]
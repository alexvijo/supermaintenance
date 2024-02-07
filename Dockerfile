FROM openjdk:11

COPY target/*.jar supermaintenance.jar

ENTRYPOINT ["java","-jar","/supermaintenance.jar"]
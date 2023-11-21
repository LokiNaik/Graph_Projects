FROM adoptopenjdk/openjdk11:alpine-jre
COPY target/DataSourceAgent-0.0.1-SNAPSHOT.jar DataSourceAgent.jar
ENTRYPOINT ["java","-jar","DataSourceAgent.jar","--spring.config.location=file:/config/application.properties"]
FROM adoptopenjdk/openjdk11:jre-11.0.9.1_1-alpine@sha256:b6ab039066382d39cfc843914ef1fc624aa60e2a16ede433509ccadd6d995b1f

COPY src/main/resources/application.yml /config/
ARG JAR_FILE=build/libs/flux-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
EXPOSE 8080
CMD java -Dspring.config.location=config/application.yml -jar app.jar
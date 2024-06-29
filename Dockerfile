FROM openjdk:21

WORKDIR /partner-film-chooser

ARG JAR_FILES="build/libs/*.jar"
ENV JAR=partner-film-chooser-server.jar
COPY ${JAR_FILES} $JAR

RUN useradd -r -U spring
RUN chown spring:spring -R .
RUN chmod ug+rw -R .
USER spring:spring

EXPOSE 8080

ENTRYPOINT ["java","-jar","/partner-film-chooser/partner-film-chooser-server.jar"]
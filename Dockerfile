FROM ubuntu:18.04
RUN apt update
RUN apt install -y openjdk-8-jre
RUN mkdir app
COPY build/libs/siqpik-0.0.1-SNAPSHOT.jar /opt/siqpik/
ENTRYPOINT ["/usr/bin/java"]
CMD ["-jar", "/opt/siqpik/siqpik-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080

# docker build --tag=siqpik --rm=true .
# docker run -d --name siqpik -hsiapik -p 80:8080 siqpik
# heroku container:push web -a=siqpik
# heroku container:release web -a=siqpik

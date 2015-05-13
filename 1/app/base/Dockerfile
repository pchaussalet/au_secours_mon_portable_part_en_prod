FROM ubuntu:14.04

RUN apt-get update && DEBIAN_FRONTEND=noninteractive apt-get install -y tomcat7
RUN rm -rf /var/lib/tomcat7/webapps/ROOT

ENV CATALINA_BASE /var/lib/tomcat7

ADD serf /usr/local/bin/serf
ADD run.sh /run.sh

ONBUILD ADD app.war /var/lib/tomcat7/webapps/ROOT.war

ENTRYPOINT ["/run.sh"]

FROM ubuntu:13.10

RUN apt-get update
RUN DEBIAN_FRONTEND=noninteractive apt-get install -y nginx-light && \
    apt-get clean && \
    rm /etc/nginx/sites-enabled/default

ADD reconfigure_nginx.sh /opt/reconfigure_nginx.sh
ADD serf /usr/local/bin/serf
ADD app.nginx /etc/nginx/sites-enabled/app.conf
ADD run.sh /run.sh

EXPOSE 80

CMD "/run.sh"

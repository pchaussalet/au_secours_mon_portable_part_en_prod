#!/bin/sh
echo "ensuring docker-compose environment is clean..."
cd 4
docker-compose kill
docker-compose rm --force
cd ..

STOPPED=`docker ps -a |grep Exit`
if [ -n "$STOPPED" ]; then
  echo "[WARNING] Stopped container remaining :"
  docker ps -a |grep Exit |cut -c158-
#  echo "Removing stopped containers: ${STOPPED}"
#  docker rm $STOPPED
fi

DEVOXX=`docker ps -a |grep bbl_pch/ |cut -c-12 |xargs`
if [ -n "$DEVOXX" ]; then
  echo "Removing remaining asmppp/* containers :"
  for service in nginx app mysql; do
    SERVICE=`docker ps -a |grep asmppp/ |grep $service |cut -c-12 |xargs`
    if [ -n "$SERVICE" ]; then
      echo "\t$service containers..."
      docker rm $SERVICE
    fi
  done
fi

export bbl=~/presentations/bbl_pch/au_secours_mon_portable_part_en_prod
for service in mysqld nginx; do cd $service; docker build -t bbl_pch/${service} .; cd -; done
serf agent -role=host -discover=bbl -iface docker0 -event-handler=$(pwd)/event-handler.sh -log-level=debug

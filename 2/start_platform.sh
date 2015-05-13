#!/bin/sh
CMD="docker run \
  -d \
  --expose 3306 \
  --name mysql \
  --volume /home/bbl_pch/data:/var/lib/db/mysql:rw 
  bbl_pch/mysqld"
echo $CMD |sed 's/ -/\n  -/g'
$CMD
sleep 2
echo

CMD="docker run 
  -d 
  --expose 8080 
  --name app-1.0.0 
  --link mysql:db 
  -v $(pwd)/application-uat.conf:/etc/application-uat.conf 
  -e "ASMPP_CONF=/etc/application-uat.conf" 
  bbl_pch/app:1.0.0"
echo $CMD |sed 's/ -/\n  -/g'
$CMD
sleep 2
echo

CMD="docker run 
  -d 
  -p 80:80 
  --name nginx 
  --link app-1.0.0:app1 
  bbl_pch/nginx"
echo $CMD |sed 's/ -/\n  -/g'
$CMD
sleep 2
echo


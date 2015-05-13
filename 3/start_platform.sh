#!/bin/sh
CMD="docker run 
  -d 
  --expose 3306 
  --name mysql 
  --volume /home/bbl_pch/data:/var/lib/db/mysql:rw 
  bbl_pch/mysqld"
echo $CMD |sed 's/ -/\n  -/g'
$CMD
sleep 2
echo

CMD="docker run 
  -d 
  --expose 8080 
  --name appA 
  --link mysql:db 
  -e "ASMPP_CONF=/etc/application-perf.conf" 
  -v $(pwd)/application-perf.conf:/etc/application-perf.conf 
  bbl_pch/app:1.0.0"
echo $CMD |sed 's/ -/\n  -/g'
$CMD
sleep 2
echo

CMD="docker run 
  -d 
  --expose 8080 
  --name appB 
  --link mysql:db 
  -e "ASMPP_CONF=/etc/application-perf.conf" 
  -v $(pwd)/application-perf.conf:/etc/application-perf.conf 
  bbl_pch/app:1.0.0"
echo $CMD |sed 's/ -/\n  -/g'
$CMD
sleep 2
echo

CMD="docker run 
  -d 
  -p 80:80 
  --name nginx 
  --link appA:app1 
  --link appB:app2 
  bbl_pch/nginx"
echo $CMD |sed 's/ -/\n  -/g'
$CMD
sleep 2
echo


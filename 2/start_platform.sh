#!/bin/sh
echo docker run -d --expose 3306 --name mysql --volume /home/bbl_pch/data:/var/lib/db/mysql:rw bbl_pch/mysqld
docker run -d --expose 3306 --name mysql --volume /home/bbl_pch/data:/var/lib/db/mysql:rw bbl_pch/mysqld
sleep 2
echo
echo docker run -d --expose 8080 --name app-1.0.0 --link mysql:db -e "ASMPP_CONF=/etc/application-uat.conf" -v $(pwd)/application-uat.conf:/etc/application-uat.conf bbl_pch/app:1.0.0
docker run -d --expose 8080 --name app-1.0.0 --link mysql:db -e "ASMPP_CONF=/etc/application-uat.conf" -v $(pwd)/application-uat.conf:/etc/application-uat.conf bbl_pch/app:1.0.0
echo
echo docker run -d -p 80:80 --name nginx --link app-1.0.0:app1 bbl_pch/nginx
docker run -d -p 80:80 --name nginx --link app-1.0.0:app1 bbl_pch/nginx


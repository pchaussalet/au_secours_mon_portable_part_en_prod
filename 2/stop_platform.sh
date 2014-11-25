#!/bin/sh
echo docker stop nginx
echo docker rm nginx
docker rm $(docker stop nginx)
echo docker stop app-1.0.0
echo docker rm app-1.0.0
docker rm $(docker stop app-1.0.0)
echo docker stop mysql
echo docker rm mysql
docker rm $(docker stop mysql)


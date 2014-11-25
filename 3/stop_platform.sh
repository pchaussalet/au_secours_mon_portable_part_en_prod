#!/bin/sh
echo docker kill nginx
echo docker rm nginx
docker rm $(docker kill nginx)
echo docker kill appA
echo docker rm appA
docker rm $(docker kill appA)
echo docker kill appB
echo docker rm appB
docker rm $(docker kill appB)
echo docker kill mysql
echo docker rm mysql
docker rm $(docker kill mysql)

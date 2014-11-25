#!/bin/sh
export JAVA_HOME=/usr/lib/jvm/java-8-oracle
cd app
rm -rf 1.0.0
cd my-great-app
mvn clean
cd ../..
docker rm $(docker kill app-dev mysql)

#!/bin/sh
export JAVA_HOME=/usr/lib/jvm/java-8-oracle
cd my-great-app
VERSION=`xmlstarlet sel -t -v '/_:project/_:version' pom.xml`
echo mvn clean install
mvn clean install
cd ..
mkdir $VERSION
echo cp my-great-app/target/my-great-app-$VERSION.war $VERSION/app.war
cp my-great-app/target/my-great-app-$VERSION.war $VERSION/app.war
echo cd $VERSION
cd $VERSION
echo FROM bbl_pch/app:base
echo FROM bbl_pch/app:base > Dockerfile
echo docker build -t bbl_pch/app:$VERSION .
docker build -t bbl_pch/app:$VERSION .
docker run -d --name mysql bbl_pch/mysqld
docker  run -d \
            --publish 8080:8080 \
            --name app-dev \
            --link mysql:db \
            -v $(pwd)/../application-dev.conf:/etc/application.conf \
            -e "ASMPP_CONF=/etc/application.conf" \
        bbl_pch/app:$VERSION

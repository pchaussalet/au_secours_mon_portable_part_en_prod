#!/bin/sh
CMD_LINE="$BACKGROUND_RED $TEXT_WHITE"
export JAVA_HOME=/usr/lib/jvm/java-8-oracle
cd my-great-app
VERSION=`xmlstarlet sel -t -v '/_:project/_:version' pom.xml`
echo $CMD_LINE mvn clean install $RESET_FORMATTING
mvn clean install
cd ..
mkdir $VERSION
echo $CMD_LINE cp my-great-app/target/my-great-app-$VERSION.war $VERSION/app.war $RESET_FORMATTING
cp my-great-app/target/my-great-app-$VERSION.war $VERSION/app.war
echo $CMD_LINE cd $VERSION $RESET_FORMATTING
cd $VERSION
echo $CMD_LINE FROM bbl_pch/app:base $RESET_FORMATTING
echo FROM bbl_pch/app:base > Dockerfile
echo $CMD_LINE docker build -t bbl_pch/app:$VERSION . $RESET_FORMATTING
docker build -t bbl_pch/app:$VERSION .
docker run -d --name mysql bbl_pch/mysqld
docker run -d \
            --publish 8080:8080 \
            --name app-dev \
            --link mysql:db \
            -v $(pwd)/../application-dev.conf:/etc/application.conf \
            -e "ASMPP_CONF=/etc/application.conf" \
        bbl_pch/app:$VERSION

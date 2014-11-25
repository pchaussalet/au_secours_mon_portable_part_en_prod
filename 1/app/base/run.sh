#!/bin/sh
/usr/local/bin/serf agent -role=web -discover=bbl 2>&1 &
/usr/share/tomcat7/bin/catalina.sh run 2>&1

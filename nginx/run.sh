#!/bin/bash
touch etc/nginx/upstreams.conf
/usr/local/bin/serf agent -role=front -discover=bbl -event-handler=/opt/reconfigure_nginx.sh -log-level=debug
#serf members
#sleep 30
#serf members
#/usr/sbin/nginx -g 'daemon off;'

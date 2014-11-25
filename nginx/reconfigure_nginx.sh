#!/bin/sh
echo 'members :'
serf members
echo 'end members'
if [ "${SERF_EVENT}" = "member-join" ]; then
  echo "upstream backend {" > /etc/nginx/upstreams.conf
  for upstream in $(serf members |grep alive |grep role=web |awk '{ print $2 }' | awk -F':' '{ print $1 }'); do
    echo "server ${upstream}:8080;"
  done >> /etc/nginx/upstreams.conf
  echo "}" >> /etc/nginx/upstreams.conf
  cat /etc/nginx/upstreams.conf
  if [ -n "$(pgrep nginx)" ]; then
    nginx -s reload
  else
    nginx
  fi
fi

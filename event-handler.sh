#!/bin/sh

items=$(cat)
echo $items
notify-send ${SERF_EVENT} "$items"
exit 0

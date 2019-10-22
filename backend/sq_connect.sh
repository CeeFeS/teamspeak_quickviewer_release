#!/bin/sh

  _instance="1"
  _user="{username}"
  _pass="{password}"

  _host="127.0.0.1"
  _port="10011"

  echo -e "use $_instance\nlogin $_user $_pass\nclientlist -ip\nquit" | \
  nc $_host $_port | tr -d "\r" | {
    while read -r line; do
      echo "$line"
    done
  } || return 1
exit 0

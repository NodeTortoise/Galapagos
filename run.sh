#!/bin/bash

IP_ADDRESS="$(wget -qO- http://ipecho.net/plain ; echo)"

echo "IP Address: $IP_ADDRESS"

./play start -Dhttp.port=80


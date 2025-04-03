#!/bin/sh
if [ -f "/logs/app.log" ]; then
  mv /logs/app.log "/logs/app_$(date +"%Y-%m-%d_%H-%M-%S").log"
fi
touch /logs/app.log
exec java -jar /app/app.jar

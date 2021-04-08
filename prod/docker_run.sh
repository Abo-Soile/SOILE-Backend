#!/bin/bash

export VERTX_OPTS=" -server -Xms512m -Xmx1g -XX:MaxPermSize=64m -XX:NewRatio=2 -XX:SurvivorRatio=10"

echo "Running vertx..."
/vert.x-2.1.6/bin/vertx runmod "fi.abo.kogni.soile~system-startup~1.0" -conf ./dev_docker.json


#!/bin/bash

cd http_server
npm run gulpsass
./gradlew assemble

cd ..
cp http_server/build/mods/fi.abo.kogni.soile~http-server~1.1 prod/mods -r

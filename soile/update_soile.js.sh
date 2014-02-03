#!/opt/local/bin/bash

TF=$RANDOM

touch $TF
cat $1 | java -jar "/Users/tuope/software/java/jars/yuicompressor-2.4.8.jar" --type js --charset "utf-8" -o $TF
cat $TF > "/Users/tuope/programming/soile/prod/mods/fi.abo.kogni.soile~http-server~1.0/static_files/javascript/soile2.js"
rm -v $TF


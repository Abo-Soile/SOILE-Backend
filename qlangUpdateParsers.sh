#!/bin/bash

# General command for copying something to the top of a file
#echo 'task goes here' | cat - todo.txt > temp && mv temp todo.txt

#package fi.abo.kogni.soile2.qmarkup.typespec;

echo "Generating language"
java -classpath prod/jars/antlr-4.0-complete.jar org.antlr.v4.Tool soile-qmarkup/spec/TypeSpec.g4 -o tempA/

echo "Moving files"
mv tempA/**/**/*.java soile-qmarkup/src/fi/abo/kogni/soile2/qmarkup/typespec

echo "Deleting temps"
rm -rf tempA/


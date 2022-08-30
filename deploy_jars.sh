#!/bin/bash

TO="prod/mods/fi.abo.kogni.soile~common-resources~1.0/lib"
ANT="ant"

mkdir soile-qmarkup/bin
mkdir soile-elang/bin
mkdir soile-utils/bin
mkdir soile-verticle/bin

$ANT -f soile2ant.xml

$ANT -f soile-qmarkup/build.xml makejar
cd soile-qmarkup 
mvn install 
cd ../soile-elang 
mvn install 
cd ../soile-utils
mvn install
cd ../soile-verticle
mvn install

cp -avf soile-qmarkup/target/qmarkup-1.0.jar prod/mods/fi.abo.kogni.soile~questionnaire-render~1.0/lib
cp -avf soile-elang/target/elang-1.0.jar  prod/mods/fi.abo.kogni.soile~experiment-lang~1.0/lib
cp -avf soile-utils/jar/utils-1.0.jar $TO/soile2-utils.jar
cp -avf soile-verticle/jar/verticles-1.0.jar $TO/soile2-verticle.jar

#!/bin/bash

TO="prod/mods/fi.abo.kogni.soile~common-resources~1.0/lib"
ANT="ant"

mkdir soile-qmarkup/bin
mkdir soile-elang/bin
mkdir soile-utils/bin
mkdir soile-verticle/bin

$ANT -f soile2ant.xml

$ANT -f soile-qmarkup/build.xml makejar
$ANT -f soile-elang/build.xml makejar
$ANT -f soile-utils/build.xml makejar
$ANT -f soile-verticle/build.xml makejar

cp -avf soile-qmarkup/jar/soile2-qmarkup.jar prod/mods/fi.abo.kogni.soile~questionnaire-render~1.0/lib
cp -avf soile-elang/jar/soile2-elang.jar  prod/mods/fi.abo.kogni.soile~experiment-lang~1.0/lib
cp -avf soile-utils/jar/soile2-utils.jar $TO
cp -avf soile-verticle/jar/soile2-verticle.jar $TO

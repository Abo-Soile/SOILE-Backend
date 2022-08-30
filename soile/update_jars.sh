#!/opt/local/bin/bash

TO="/Users/tuope/programming/soile/prod/mods/fi.abo.kogni.soile~common-resources~1.0/lib"
ANT="ant"

$ANT -f /Users/tuope/programming/workspace/eclipse/soile-qmarkup/build.xml makejar
$ANT -f /Users/tuope/programming/workspace/eclipse/soile-elang/build.xml makejar
$ANT -f /Users/tuope/programming/workspace/eclipse/soile-utils/build.xml makejar
$ANT -f /Users/tuope/programming/workspace/eclipse/soile-verticle/build.xml makejar

cp -avf /Users/tuope/programming/workspace/eclipse/soile-qmarkup/jar/soile2-qmarkup.jar $TO
cp -avf /Users/tuope/programming/workspace/eclipse/soile-elang/jar/soile2-elang.jar  $TO
cp -avf /Users/tuope/programming/workspace/eclipse/soile-utils/jar/soile2-utils.jar $TO
cp -avf /Users/tuope/programming/workspace/eclipse/soile-verticle/jar/soile2-verticle.jar $TO


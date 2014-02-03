#!/opt/local/bin/bash

# For adding certain parameters to the JVM.
# Consult the following web pages in order to fine-tune the JVM.
#   * http://www.oracle.com/technetwork/java/javase/tech/vmoptions-jsp-140102.html
#   * http://docs.oracle.com/javase/7/docs/technotes/tools/solaris/java.html
#   * http://stas-blogspot.blogspot.fi/2011/07/most-complete-list-of-xx-options-for.html
#   * http://www.curiousmentality.co.uk/2011/11/tuning-jvm-memory-settings/
#   * http://www.umbrant.com/blog/2012/twitter_jvm_tuning.html
export VERTX_OPTS=" -server -Xms1g -Xmx1g -XX:MaxPermSize=64m -XX:NewRatio=2 -XX:SurvivorRatio=10 "

echo "Running vertx..."
vertx runmod "fi.abo.kogni.soile~system-startup~1.0" -conf ./deployment.json 


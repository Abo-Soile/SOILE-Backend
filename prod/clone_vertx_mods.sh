#!/bin/bash

cd mods

domain="fi.abo.kogni.soile"
version="1.0"

echo "Fetching dependencies"

#Dust.js templates
if [ ! -d "fi.abo.kogni.soile~vertx-mod-template-engines~1.0" ]; then
	#git clone git@github.com:danielwarna/vertx-mod-template-engines.git
	echo "Cloning dust"
	git clone https://github.com/danielwarna/vertx-mod-template-engines.git
	echo "Renaming folder to $domain~vertx-mod-template-engines~$version"

	rm $domain~vertx-mod-template-engines~$version -rf
	# mv -vf vertx-mod-template-engines $domain~vertx-mod-template-engines~$version
	cp -r vertx-mod-template-engines $domain~vertx-mod-template-engines~$version
	rm vertx-mod-template-engines
fi

#Vertex mongo persistor
#git clone git@github.com:vert-x/mod-mongo-persistor.git

#!/bin/bash

cd mods

domain="fi.abo.kogni.soile"
version="1.0"

echo "Fetching dependencies"

#Dust.js templates
if [ ! -d "fi.abo.kogni.soile~vertx-mod-template-engines~1.0
" ]; then
	git clone git@github.com:carchrae/vertx-mod-template-engines.git

	echo "Renaming folder"
	mv "vertx-mod-template-engines" "$domain~vertx-mod-template-engines~$version"
fi

#Vertex mongo persistor
#git clone git@github.com:vert-x/mod-mongo-persistor.git
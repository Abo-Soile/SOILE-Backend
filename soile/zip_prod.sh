#!/opt/local/bin/bash

find . -type f -name "*~" -delete
if [ -f ./soile_prod.zip  ]; then
    srm -mzv ./soile_prod.zip 
    sleep 2
fi
zip -r soile_prod.zip prod


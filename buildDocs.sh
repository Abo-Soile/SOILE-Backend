#!/bin/bash


#Installing sphinx + theme
pip install sphinx
pip install sphinx_rtd_theme

#Building docs
sphinx-build -b html docs/. prod/mods/fi.abo.kogni.soile~http-server~1.0/static_files/docs


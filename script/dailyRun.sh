#!/bin/bash

cd c:\\workspace\\home-page-media-file || exit
git stash
git checkout master
git pull

cd c:\\workspace\\home-page-media-scraper || exit
git pull
./gradlew run

cd c:\\workspace\\home-page-media-file || exit
git add .
today=$(date +"%Y/%m/%d")
git commit -m "files from $today"
git push

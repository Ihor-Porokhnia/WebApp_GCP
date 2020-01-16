#!/bin/bash
str=uuidgen
echo $str >> README.md
git add .
git commit -m $str
git push origin HEAD:backend


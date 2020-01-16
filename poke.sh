#!/bin/bash

uuidgen  >> README.md
git add .
git commit -m  'tst'
git push origin HEAD:backend


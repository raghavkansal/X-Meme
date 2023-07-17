#!/bin/bash

curl --location --request POST 'http://localhost:8081/memes/' \
--header 'Content-Type: application/json' \
--data-raw '{
  "name": "ashok kumar",
  "url": "https://www.im.coma",
  "caption": "This is a meme"
}'

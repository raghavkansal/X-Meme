#!/bin/bash

pip3 install -r requirements.txt
pytest --pspec --disable-pytest-warnings assessment/main.py
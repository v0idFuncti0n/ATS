#! /bin/bash

export RESUME_PARSER_HOST=0.0.0.0
export RESUME_PARSER_PORT=8050

cd application
echo Parser Running at $RESUME_PARSER_HOST:$RESUME_PARSER_PORT
python server.py

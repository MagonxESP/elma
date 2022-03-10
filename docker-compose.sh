#!/usr/bin/env bash

entrypoint="docker-compose"

if [[ -f "docker-compose.override.yml" ]]
then
  entrypoint="docker-compose -f docker-compose.yml -f docker-compose.override.yml"
fi

command="$entrypoint ${*:1}"
echo $command
eval $command

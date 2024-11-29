#!/bin/bash


if [ "$1" == "start" ]; then
    docker-compose up --build
elif [ "$1" == "stop" ]; then
    docker-compose down -v
elif [ "$1" == "restart" ]; then
    docker-compose down
    docker-compose up --build
fi
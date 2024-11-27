#!/bin/bash

NAME_APP=app
NAME_SQL=respak_testing-db-1 

if [ $1 == "start" ]; then
    docker-compose up -d --build
elif [ $1 == "stop" ]; then
    docker-compose down
elif [ $1 == "allDelete" ]; then
    docker rm -f $(docker ps -a -q)
elif [ $1 == "inputApp" ]; then
    docker exec -it $NAME_APP /bin/bash
elif [ $1 == "inputDB" ]; then
    docker exec -it $NAME_SQL psql -U postgres -d mydb
elif [ $1 == "start mvn" ]; then
    docker exec -it $NAME_APP mvn spring-boot:run
fi

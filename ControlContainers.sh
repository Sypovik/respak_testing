#!/bin/bash

APP=app
DB=db

if [ $1 == "start" ]; then
    docker-compose up -d --build

elif [ $1 == "stop" ]; then
    docker-compose down

elif [ $1 == "rmExited" ]; then
    # Остановить и удалить APP и DB контейнеры
    docker stop $(docker ps -aq --filter "name=$APP"\
    --filter "name=$DB" --filter "status=exited"\
    2>/dev/null) 
    docker rm $(docker ps -aq --filter "name=$APP"\
    --filter "name=$DB" --filter "status=exited"\
    2>/dev/null)

elif [ $1 == "appShell" ]; then
    docker exec -it $APP /bin/bash

elif [ $1 == "dbShell" ]; then
    docker exec -it $DB psql -U postgres -d respakdb

elif [ $1 == "runMvn" ]; then
    docker exec -it $APP mvn clean spring-boot:run
fi

#!/bin/bash

APP_CONTAINER=app
DB_CONTAINER=db

DOCKER_COMPOSE_FILE="docker-compose.yml" # Имя файла docker-compose

# Функция для вывода меню
show_menu() {
    echo "Доступные действия:"
    echo "start, db, app, stop, clean, clean-images"
}

# Функция для вход в базу данных
connect_to_db() {
    echo "Подключаемся к базе данных..."
    docker exec -it $DB_CONTAINER psql -U postgres -d respakdb
}

# Функция для вход в приложение
connect_to_app() {
    echo "Подключаемся к приложению..."
    docker exec -it $APP_CONTAINER bash
}

# Функция для сборки контейнеров
build_containers() {
    echo "Сборка контейнеров..."
    docker-compose -f "$DOCKER_COMPOSE_FILE" up --build -d
}

# Функция для остановки контейнеров
stop_containers() {
    echo "Остановка контейнеров..."
    docker-compose -f "$DOCKER_COMPOSE_FILE" stop
}

# Функция для удаления контейнеров
remove_containers() {
    echo "Удаление контейнеров..."
    docker-compose -f "$DOCKER_COMPOSE_FILE" down
}

# Функция для удаления образов
remove_images() {
    echo "Удаление образов..."
    docker-compose -f "$DOCKER_COMPOSE_FILE" down --rmi all
}

# Проверка наличия аргумента
if [ -z "$1" ]; then
    show_menu
    exit 0
fi

# Выполнение действий в зависимости от параметра
case "$1" in
    start)
        build_containers
        ;;
    db)
        connect_to_db
        ;;
    app)
        connect_to_app
        ;;
    stop)
        stop_containers
        ;;
    clean)
        remove_containers
        ;;
    clean-images)
        stop_containers
        remove_containers
        remove_images
        ;;
    *)
        echo "Неизвестное действие: $1"
        show_menu
        exit 1
        ;;
esac

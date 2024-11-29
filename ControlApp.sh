#!/bin/bash

# Имя вспомогательного скрипта
CONTROL_CONTAINERS_SCRIPT="./ControlContainers.sh"

# Имя контейнера приложения
APP_CONTAINER="app"

# Функция для вывода меню
show_menu() {
    echo "Доступные действия:"
    echo "build, start, stop, clean, app-build, app-run, app-tests"
    echo ""
    echo "Описание действий:"
    echo "build       - Сборка и запуск контейнеров"
    echo "start       - Запуск приложения (Maven) внутри контейнера"
    echo "stop        - Остановка контейнеров"
    echo "clean       - Удаление контейнеров и образов"
    echo "app-build   - Maven build внутри контейнера"
    echo "app-run     - Maven spring-boot:run внутри контейнера"
}

# Проверка наличия вспомогательного скрипта
if [ ! -f "$CONTROL_CONTAINERS_SCRIPT" ]; then
    echo "Ошибка: Не найден вспомогательный скрипт $CONTROL_CONTAINERS_SCRIPT"
    exit 1
fi

# Проверка наличия аргумента
if [ -z "$1" ]; then
    show_menu
    exit 0
fi

# Выполнение действий в зависимости от параметра
if [ "$1" == "build" ]; then
    echo "Сборка и запуск контейнеров..."
    bash "$CONTROL_CONTAINERS_SCRIPT" start
    
elif [ "$1" == "start" ]; then
    echo "Запуск приложения (Maven) внутри контейнера..."
    bash "$CONTROL_CONTAINERS_SCRIPT" stop
    bash "$CONTROL_CONTAINERS_SCRIPT" start
    docker exec -it "$APP_CONTAINER" mvn spring-boot:run

elif [ "$1" == "stop" ]; then
    echo "Остановка контейнеров..."
    bash "$CONTROL_CONTAINERS_SCRIPT" stop

elif [ "$1" == "clean" ]; then
    echo "Удаление контейнеров и образов..."
    bash "$CONTROL_CONTAINERS_SCRIPT" clean-images

elif [ "$1" == "app-build" ]; then
    echo "Maven build внутри контейнера..."
    docker exec -it "$APP_CONTAINER" mvn clean install

elif [ "$1" == "app-run" ]; then
    echo "Запуск приложения (Maven spring-boot:run)..."
    docker exec -it "$APP_CONTAINER" mvn spring-boot:run
else
    echo "Неизвестное действие: $1"
    show_menu
    exit 1
fi

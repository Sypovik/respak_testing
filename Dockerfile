# Используем базовый образ Debian
FROM ubuntu
# Устанавливаем необходимые пакеты
RUN apt-get update && apt-get install -y \
    openjdk-8-jdk \
    maven \
    git \
    postgresql-client \
    && apt-get clean

# Устанавливаем переменную окружения для Java
ENV JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
ENV PATH=$JAVA_HOME/bin:$PATH

# Указываем рабочую директорию
WORKDIR /app

# Копируем содержимое проекта
COPY . .

# Скачиваем зависимости и собираем проект
# RUN mvn clean install -DskipTests

# Указываем команду для запуска приложения
# CMD ["java", "-jar", "target/your-application.jar"]

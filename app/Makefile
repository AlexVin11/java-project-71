# Makefile

setup:
	./gradlew wrapper --gradle-version 8.5

clean:
	./gradlew clean

build:
	./gradlew clean build

install:
	./gradlew clean install

run-dist:
	# Очистка от результатов предыдущей сборки
	./gradlew clean
	# Создание jar исполняемого файла
	./gradlew installDist
	# Запуск исполняемого файла
	./build/install/app/bin/app

run:
	./gradlew run

test:
	./gradlew test

report:
	./gradlew jacocoTestReport

lint:
	./gradlew checkstyleMain

check-deps:
	./gradlew dependencyUpdates -Drevision=release


build-run: build run

.PHONY: build

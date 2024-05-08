# Makefile

run-dist: 
	# Очистка от результатов предыдущей сборки
	./gradlew clean
	# Создание jar исполняемого файла
	./gradlew installDist
	# Запуск исполняемого файла
	./build/install/app/bin/app
report:
	./gradlew jacocoTestReport

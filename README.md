## Документация проекта
* [Исходники проекта](https://github.com/netology-code/qa-diploma)
* [План автоматизации тестирования](https://github.com/melamorymory/diploma/blob/master/Plan.md)
* [Отчёт о тестировании](https://github.com/melamorymory/diploma/blob/master/Report.md)
* [Отчёт о проведённой автоматизации тестирования](https://github.com/melamorymory/diploma/blob/master/Summary.md)

## Процедура запуска авто-тестов
1) Склонировать репозиторий на свой ПК командой `git clone https://github.com/melamorymory/diploma`
2) Открыть проект в IntelliJ IDEA
3) Запустить программу Docker Desktop
4) Запустить файл docker-compose-postgresql.yml или docker-compose-mysql.yml (требуется установленный node.js)
5) Запустить приложение aqa-shop.jar командой:
* для PostgreSQL `java -jar .\aqa-shop.jar -P:jdbc.url=postgresql://localhost:5432/app -P:jdbc.user=app -P:jdbc.password=pass`
* для MySQL `java -jar .\aqa-shop.jar -P:jdbc.url=mysql://localhost:3306/app -P:jdbc.user=app -P:jdbc.password=pass`
7) Запустить нужные авто-тесты
8) Запустить формирование отчёта командой `./gradlew allureServe`

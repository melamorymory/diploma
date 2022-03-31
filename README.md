## Процедура запуска авто-тестов
1) Склонировать репозиторий на свой ПК командой `git clone https://github.com/melamorymory/diploma`
2) Открыть проект в IntelliJ IDEA
3) Запустить программу Docker Desktop
4) Запустить файл docker-compose-mysql.yml (требуется установленный node.js)
5) Запустить приложение aqa-shop.jar командой `java -jar .\aqa-shop.jar -P:jdbc.url=mysql://localhost:3306/app -P:jdbc.user=app -P:jdbc.password=pass`
6) Запустить нужные авто-тесты

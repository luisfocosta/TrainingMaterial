@echo off
cd %~dp0
java -classpath build -Djava.util.logging.config.file=Cars.logging.properties cc.cars.Application %*

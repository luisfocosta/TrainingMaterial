@echo off
cd %~dp0
if exist build del /q build\*.*
if not exist build md build
javac -d build -classpath lib\junit-4.12.jar;lib\hamcrest-core-1.3.jar src\cc\sockets\*.java test\cc\sockets\*.java

@echo off
cd %~dp0
if exist build del /q build\*.*
if not exist build md build
javac -d build src\cc\language\*.java
cd build
jar cf ..\MyApps.jar cc\language\*.class
cd ..

@echo off
cd %~dp0
if exist build del /q build\*.*
if not exist build md build
javac -d build src\cc\tools\*.java

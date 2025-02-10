@echo off
cd %~dp0
if exist build rd /s /q build
md build
javac -d build src\cc\health\*.java

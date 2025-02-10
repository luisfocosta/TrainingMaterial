@echo off
cd %~dp0
if exist build rd /s /q build
md build
javac -d build src\cc\html\*.java src\cc\html\list\*.java src\cc\html\table\*.java src\cc\client\*.java

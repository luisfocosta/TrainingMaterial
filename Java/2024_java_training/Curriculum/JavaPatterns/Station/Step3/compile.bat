@echo off
cd %~dp0
if exist build del /q build\*.*
if not exist build md build
javac -d build src\cc\hvac\*.java src\cc\monitor\*.java  src\cc\security\*.java  src\cc\transport\*.java

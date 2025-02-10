@echo off
cd %~dp0
java -classpath build cc.threads.Lookup %*

@echo off
cd %~dp0
java -classpath lib\junit-4.12.jar;lib\hamcrest-core-1.3.jar;build org.junit.runner.JUnitCore cc.sockets.HttpRequestTest
java -classpath lib\junit-4.12.jar;lib\hamcrest-core-1.3.jar;build org.junit.runner.JUnitCore cc.sockets.HttpSocketClientTest

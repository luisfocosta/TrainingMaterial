rem This is a placeholder file that will eventually replace itself
rem with a binary file from the Master tree.
copy /y %~dp0..\..\..\Master\webroot\images\Logo.gif %~dp0
del %~f0

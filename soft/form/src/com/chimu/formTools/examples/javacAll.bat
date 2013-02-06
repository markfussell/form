
@echo off

REM this batch file will compile all files in all schemes directories

echo running Java Compiler........

@echo on

javac -d . scheme1\*.java
javac -d . scheme1b\*.java
javac -d . scheme2\*.java
javac -d . scheme3\*.java
javac -d . scheme3b\*.java
javac -d . scheme4\*.java
javac -d . scheme4b\*.java
javac -d . scheme4c\*.java
javac -d . scheme4d\*.java
javac -d . scheme5\*.java
javac -d . scheme5b\*.java


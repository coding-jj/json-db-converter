@echo off

@REM Change to Batch Directory
cd /d "%~dp0\"
echo %~dp0

@REM ##############################
@REM Parameters
@REM ##############################

@REM ##############################
@REM Parameters for Spring
@REM ##############################

echo .
echo ##############################
echo Maven run Spring
echo ##############################
echo .

call mvnw clean test

echo .
echo ##############################
echo Clean UP
echo ##############################
echo .

echo Press Key to continue
pause

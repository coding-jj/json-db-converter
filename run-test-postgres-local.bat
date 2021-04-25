@echo off

@REM Change to Batch Directory
cd /d "%~dp0\"
echo %~dp0

@REM ##############################
@REM Parameters
@REM ##############################

SET VAR_DATABASE_USER=postgres
SET VAR_DATABASE_NAME=%VAR_DATABASE_USER%
SET VAR_DATABASE_PASS=postgres

SET VAR_DOCKER_PG_PORT=15432
SET VAR_DOCKER=demo-jdbc-pg-junit
SET VAR_DOCKER_IMAGE=postgres:12-alpine

@REM ##############################
@REM Parameters for Spring
@REM ##############################

set SPRING_DATASOURCE_URL=jdbc:postgresql://127.0.0.1:%VAR_DOCKER_PG_PORT%/%VAR_DATABASE_NAME%
set SPRING_DATASOURCE_USERNAME=%VAR_DATABASE_USER%
set SPRING_DATASOURCE_PASSWORD=%VAR_DATABASE_PASS%

echo .
echo ##############################
echo Docker
echo ##############################
echo .

echo docker run -d --name %VAR_DOCKER% -e POSTGRES_USER=%VAR_DATABASE_USER% -e POSTGRES_PASSWORD=%VAR_DATABASE_PASS% -p %VAR_DOCKER_PG_PORT%:5432 %VAR_DOCKER_IMAGE%
docker run -d --name %VAR_DOCKER% -e POSTGRES_USER=%VAR_DATABASE_USER% -e POSTGRES_PASSWORD=%VAR_DATABASE_PASS% -p %VAR_DOCKER_PG_PORT%:5432 %VAR_DOCKER_IMAGE%

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

echo Press Key to clean-up docker container (Abort CTRL-C)
pause

docker stop %VAR_DOCKER%
docker rm --volumes %VAR_DOCKER%

#!/bin/sh

# DATABASE
export DB_PASSWORD=password
export DB_USERNAME=test
export DB_NAME=sample
export DB_SOFTWARE=postgresql
export DB_DRIVER_CLASS=org.postgresql.Driver
export DB_HOST=localhost
export DB_POOL_SIZE=10

#App Configuration
export APP_SERVICE_PORT=6001

/usr/bin/java -jar build/libs/sample-app.jar

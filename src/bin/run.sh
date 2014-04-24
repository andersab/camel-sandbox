#!/bin/bash

APP_HOME=/tmp/camel-sandbox

execJar=camel-sandbox-0.1.0-SNAPSHOT.jar
LOG4J_PROPS="file:///$APP_HOME/config/log4j.properties"

java -cp "$APP_HOME/lib/*" -DAPP_HOME="$APP_HOME" -Dlog4j.debug=true -Dlog4j.configuration="$LOG4J_PROPS" com.andersab.camel.Startup

echo
echo "Shutdown complete"
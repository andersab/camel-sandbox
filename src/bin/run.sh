#!/bin/bash

curwd=$(pwd)

execJar=camel-sandbox-0.1.0-SNAPSHOT.jar

java -cp "../lib/*" com.andersab.camel.Startup

echo "done"
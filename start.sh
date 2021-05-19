#!/bin/bash
echo "Running naming server"
java -jar tire-eureka-naming-server/build/libs/tire-eureka-naming-server-0.0.1-SNAPSHOT.jar &

echo "Running configure server"
java -jar configuration-cloud/build/libs/configuration-cloud-0.0.1-SNAPSHOT.jar &

echo "Running Chatting"
java -jar Chatting/build/libs/Chatting-0.0.1-SNAPSHOT.jar &


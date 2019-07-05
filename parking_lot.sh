#!/usr/bin/env bash

mvn clean package
if [ "$#" -eq  "0" ]
   then
     mvn exec:java -Dexec.mainClass="com.gojek.parkingLot.Main"

 else
     mvn exec:java -Dexec.mainClass="com.gojek.parkingLot.Main" -Dexec.args=$1

 fi


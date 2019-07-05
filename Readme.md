### mvn clean package

##to run commands in interactivily run like below
mvn exec:java -Dexec.mainClass="com.gojek.parkingLot.Main"

## to run commands from file run like below
mvn exec:java -Dexec.mainClass="com.gojek.parkingLot.Main" -Dexec.args="filename"
ex:- mvn exec:java -Dexec.mainClass="com.gojek.parkingLot.Main" -Dexec.args="commands.txt"



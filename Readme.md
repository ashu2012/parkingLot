[bash] sh parking_lot.sh
[bash] sh parking_lot.sh  commands.-txt
[bash] sh parking_lot.sh  full_path_path_of file.txt

############################### commands to run mannually #########################

## mvn clean package

##to run commands in interactivily run like below
mvn exec:java -Dexec.mainClass="com.gojek.parkingLot.Main"

## to run commands from file run like below
mvn exec:java -Dexec.mainClass="com.gojek.parkingLot.Main" -Dexec.args="filename"
ex:- mvn exec:java -Dexec.mainClass="com.gojek.parkingLot.Main" -Dexec.args="commands.txt"


###################  Documentation ##############

Vehicles and parking spot has been made of enum to handle new vehicle like electric car or electric car parking spot.

Commands from interactive shell can be read using interpreter design pattern.

Parking lot is singleton with builder design patter.

Composition has been used at lot of places instead of extending classes to have more flexibilty
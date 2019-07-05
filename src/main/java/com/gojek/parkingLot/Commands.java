package com.gojek.parkingLot;

// TOdo make it singleton
public class Commands implements Expression{

    AccessInterface executeCommands;
    ParkingLot parkingLotObj;

    public boolean interpret(String context) {

        if(context.startsWith("create_parking_lot")){
            executeCommands = new CreateParkingLot();
            parkingLotObj = ParkingLot.getInstance();
            executeCommands.execute(parkingLotObj,context);


        }

        else if(context.startsWith("park")){
            executeCommands = new Park();
            executeCommands.execute(parkingLotObj,context);

        }
        else if(context.startsWith("leave")){
            executeCommands = new Leave();
            executeCommands.execute(parkingLotObj,context);

        }
        else if(context.startsWith("status")){
            executeCommands = new Status();
            executeCommands.execute(parkingLotObj,context);

        }
        else if(context.startsWith("registration_numbers_for_cars_with_colour")){
            executeCommands = new RegistrationNumbersForCarsWithColour();
            executeCommands.execute(parkingLotObj,context);

        }
        else if(context.startsWith("slot_number_for_registration_number")){
            executeCommands = new SlotNumberForRegistrationNumber();
            executeCommands.execute(parkingLotObj,context);

        }
        else if(context.startsWith("slot_numbers_for_cars_with_colour")){
            executeCommands = new SlotNumbersForCarsWithColour();
            executeCommands.execute(parkingLotObj,context);

        }
        else if(context.startsWith("exit")){
            System.out.println("exit called , close shell") ;
        }
        else{
            System.out.println("unknown command") ;
        }

        return false;
    }
}

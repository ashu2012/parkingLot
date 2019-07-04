package com.gojek.parkingLot;

// TOdo make it singleton
public class Commands implements Expression{

    Strategy executeCommands;
    @Override
    public boolean interpret(String context) {

        if(context.startsWith("create_parking_lot")){
            executeCommands = new CreateParkingLot();
            System.out.println("create_parking_lot " +"called") ;

        }

        else if(context.startsWith("park")){
            executeCommands = new Park();
            System.out.println("Park " +"called") ;
        }
        else if(context.startsWith("leave")){
            executeCommands = new Leave();
            System.out.println("leave " +"called") ;
        }
        else if(context.startsWith("status")){
            executeCommands = new Status();
            System.out.println("status " +"called") ;
        }
        else if(context.startsWith("registration_numbers_for_cars_with_colour")){
            executeCommands = new RegistrationNumbersForCarsWithColour();
            System.out.println("registration_numbers_for_cars_with_colour " +"called") ;
        }
        else if(context.startsWith("slot_number_for_registration_number")){
            executeCommands = new SlotNumberForRegistrationNumber();
            System.out.println("slot_number_for_registration_number " +"called") ;
        }
        else if(context.startsWith("slot_numbers_for_cars_with_colour")){
            executeCommands = new SlotNumbersForCarsWithColour();
            System.out.println("slot_numbers_for_cars_with_colour " +"called") ;
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

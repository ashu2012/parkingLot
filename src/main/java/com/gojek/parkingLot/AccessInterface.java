package com.gojek.parkingLot;

public interface AccessInterface {

    void execute(ParkingLot parkingLot ,String commandStr);
}


/** Implements the algorithm using the strategy interface */
class CreateParkingLot implements AccessInterface {


    @Override
    public void execute(ParkingLot parkingLot , String commandStr) {
        int slots= Integer.parseInt(commandStr.split(" ")[1].trim());
        parkingLot.setParkingSpots(slots).build();
    }
};

class Park implements AccessInterface {


    @Override
    public void execute(ParkingLot parkingLot ,String commandStr) {
        String [] arr = commandStr.split(" ");
        String regNum= arr[1].trim();
        String colour= arr[2].trim();
        try {
            parkingLot.getNewParkingTicket(regNum,colour);
        } catch (ParkingFullException e) {
            e.printStackTrace();
        }
    }
};

class Leave implements AccessInterface {
    @Override
    public void execute(ParkingLot parkingLot ,String commandStr) {
        int slot= Integer.parseInt(commandStr.split(" ")[1].trim());
        parkingLot.leaveParkingSlot(slot);
    }
};

class Status implements AccessInterface {
    @Override
    public void execute(ParkingLot parkingLot ,String commandStr) {
        parkingLot.printStatus();
    }
};




class RegistrationNumbersForCarsWithColour  implements AccessInterface {
    @Override
    public void execute(ParkingLot parkingLot ,String commandStr) {
        String colour= commandStr.split(" ")[1].trim();
        parkingLot.getRegistrationNumberByColour(colour);
    }
};


class SlotNumbersForCarsWithColour  implements AccessInterface {
    @Override
    public void execute(ParkingLot parkingLot ,String commandStr) {
        String colour= commandStr.split(" ")[1].trim();
        parkingLot.getSlotNumberByColour(colour);
    }
};


class SlotNumberForRegistrationNumber  implements AccessInterface {
    @Override
    public void execute(ParkingLot parkingLot ,String commandStr) {
        String regNum = commandStr.split(" ")[1].trim();
        parkingLot.getSlotNumberByReg(regNum);
    }
};
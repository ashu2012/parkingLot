package com.gojek.parkingLot;

public interface Strategy {

    void execute(String commandStr);
}


/** Implements the algorithm using the strategy interface */
class CreateParkingLot implements Strategy {


    @Override
    public void execute(String commandStr) {

    }
};

class Park implements Strategy {


    @Override
    public void execute(String commandStr) {

    }
};

class Leave implements Strategy {
    @Override
    public void execute(String commandStr) {

    }
};

class Status implements Strategy {
    @Override
    public void execute(String commandStr) {

    }
};




class RegistrationNumbersForCarsWithColour  implements Strategy {
    @Override
    public void execute(String commandStr) {

    }
};


class SlotNumbersForCarsWithColour  implements Strategy {
    @Override
    public void execute(String commandStr) {

    }
};


class SlotNumberForRegistrationNumber  implements Strategy {
    @Override
    public void execute(String commandStr) {

    }
};
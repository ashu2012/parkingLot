package com.gojek.parkingLot;


public  class ParkingSpot implements  Comparable<ParkingSpot> {
    private int spotNumber;  // spot number from enterance
    private boolean free;
    private Vehicle vehicle;
    private final ParkingSpotType type;

    public boolean IsFree(){
        return this.free;
    }

    public ParkingSpot(ParkingSpotType type, int slotNumber) {

        this.type = type;
        this.spotNumber= slotNumber;
    }

    public void assignVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
        free = false;

    }

    public void removeVehicle() {
        this.vehicle = null;
        free = true;

    }

    @Override
    public int compareTo(ParkingSpot o) {
        return this.spotNumber- o.spotNumber;
    }

    public int getSpotNumber(){
       return  this.spotNumber;
    }

    public  Vehicle getVehicle(){
        return  this.vehicle;
    }
    @Override
    public boolean equals(Object obj)
    {

        // checking if both the object references are
        // referring to the same object.
        if(this == obj)
            return true;

        // it checks if the argument is of the
        // type Geek by comparing the classes
        // of the passed argument and this object.
        // if(!(obj instanceof Geek)) return false; ---> avoid.
        if(obj == null || obj.getClass()!= this.getClass())
            return false;

        // type casting of the argument.
        ParkingSpot ps = (ParkingSpot) obj;

        // comparing the state of argument with
        // the state of 'this' Object.
        return (ps.spotNumber == this.spotNumber );
    }

    @Override
    public int hashCode()
    {

        // We are returning the Geek_id
        // as a hashcode value.
        // we can also return some
        // other calculated value or may
        // be memory address of the
        // Object on which it is invoked.
        // it depends on how you implement
        // hashCode() method.
        return this.spotNumber;
    }
}


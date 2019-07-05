package com.gojek.parkingLot;

public abstract class Vehicle {
    private String registration;
    private final VehicleType type;
    private ParkingTicket ticket;
    private String colour;

    public Vehicle(VehicleType type) {
        this.type = type;
    }

    public void assignTicket(ParkingTicket ticket) {
        this.ticket = ticket;
    }

    public VehicleType getType(){
        return  this.type;
    }

    public  String getRegistration(){
        return  this.registration;
    }

    public  String getColour(){
        return  this.colour;
    }


    public  void setRegistration(String regNum){
          this.registration= regNum;
    }

    public  void setColour(String colour){
         this.colour = colour;
    }

    public  int getSlotNumber(){
        return  this.ticket.parkingSlotNumber;
    }

    public void leaveParkingLot(){
        this.ticket.payParkingTicket();
        this.ticket = null;

    }
}


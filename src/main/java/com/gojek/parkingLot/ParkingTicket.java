package com.gojek.parkingLot;

import java.time.LocalDateTime;
import java.util.Date;

public class ParkingTicket {
    String ticketNumber;
    LocalDateTime issuedAt;
    LocalDateTime payedAt;
    double payedAmount;
    ParkingTicketStatus status;
    int parkingSlotNumber;

    public ParkingTicket(int slotNumber){
        this.setParkingSlotNumber(slotNumber);
        this.setParkingStatus(ParkingTicketStatus.ACTIVE);
    }
    public  void setParkingSlotNumber(int slotNumber){
       this.parkingSlotNumber = slotNumber;
   }

    public  void setParkingStatus(ParkingTicketStatus st){
        this.status = ParkingTicketStatus.ACTIVE;
    }

    public void payParkingTicket(){
        this.status= ParkingTicketStatus.PAID;
    }
}

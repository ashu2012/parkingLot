package com.gojek.parkingLot;

import java.util.*;

public class ParkingLot {
    private String name;


    private int currentCarCount;
    private int electricCarCount;
    private  int maxCarCount;



    // all available parking slots, identified by their location number
    // heap will give us nearest Slot in O(1)
    // Also after vehicle removal slot will again become available at
    // log(N) complexity
    private PriorityQueue<ParkingSpot> availableSlotsHeap;

    // to implement leave function in O(1)
    private HashMap<Integer, ParkingSpot> usedParkingSpot;

    // all active parking vehicles, identified by their registration
    //Slot number in which a car with a given registration number is parked.
    //RegNo.->vehicles -> ParkingTicket -> SLot number
    //Query --> Slot number in which a car with a given registration number is parked
    // above query handel in O(1) ;
    private HashMap<String, Vehicle> parkedVehicleByRegHashMap;

    //Query -->all active parking vehicles, identified by their color
    //slot numbers of all slots where a car of a particular colour is parked.
    // Color-> Set of vehicles -> ParkingTicket -> SLot number O(1)
    // Also answers below query in O(1)
    //Query-->Registration numbers of all cars of a particular colour.
    private HashMap<String, HashSet<Vehicle>> parkedVehicleByColorHashMap;

    // singleton ParkingLot to ensure only one object of ParkingLot in the system,
    // all entrance panels will use this object to create new parking ticket: getNewParkingTicket(),
    // similarly exit panels will also use this object to close parking tickets
    private static ParkingLot parkingLot = null;

    // private constructor to restrict for singleton
    private ParkingLot() {

    }

    // static method to get the singleton instance of StockExchange
    public static ParkingLot getInstance() {
        if (parkingLot == null) {
            parkingLot = new ParkingLot();
        }
        return parkingLot;
    }

    public  ParkingLot setParkingSpots(int count) {
        this.maxCarCount=count;
        return this;
    }


    public  void build() {
       // do all initialization
        this.currentCarCount =0;

        availableSlotsHeap = new PriorityQueue<ParkingSpot>(maxCarCount);
        for(int slotId =0 ; slotId< maxCarCount;slotId++){
            ParkingSpot ps = new CarSpot( slotId);
            availableSlotsHeap.add(ps);
        }

        usedParkingSpot= new HashMap<Integer, ParkingSpot>();
        parkedVehicleByRegHashMap = new HashMap<String, Vehicle>();
        parkedVehicleByColorHashMap = new HashMap<String, HashSet<Vehicle>>();

        System.out.println(String.format("Created a parking lot with %d slots",maxCarCount));
    }

    // note that the following method is not 'synchronized' as we are executing in single enterance and
    // exist and not supporting multithreaded application as per requirement.
    public  void getNewParkingTicket(String regNum, String colour) throws ParkingFullException {
        Vehicle vehicle = new Car() ;
        vehicle.setRegistration(regNum);
        vehicle.setColour(colour);
        if (this.isFull(vehicle.getType())) {
            System.out.println(String.format("Sorry, parking lot is full"));
            throw new ParkingFullException("Sorry, parking lot is full");
        }
        //get latest slot available from Min heap
        ParkingSpot slot = availableSlotsHeap.peek();
        ParkingTicket ticket = new ParkingTicket(slot.getSpotNumber());
        slot.assignVehicle(vehicle);
        vehicle.assignTicket(ticket);
        // save in db for persistance
        //ticket.saveInDB();
        // if the ticket is successfully saved in the database, we can increment the parking spot count
        this.incrementSpotCount(vehicle.getType());

        //update data structures
        usedParkingSpot.put(slot.getSpotNumber(),slot);
        parkedVehicleByRegHashMap.put(vehicle.getRegistration(),vehicle);
        HashSet<Vehicle> vehicleList =  parkedVehicleByColorHashMap.getOrDefault(vehicle.getColour(), new HashSet<Vehicle>());
        vehicleList.add(vehicle);
        parkedVehicleByColorHashMap.put(vehicle.getColour(),vehicleList );
        System.out.println(String.format("Allocated slot number: %d",slot.getSpotNumber()));
    }

    public  void leaveParkingSlot(int slotNUmber){
        if(slotNUmber >= maxCarCount ){
            return;
        }
        this.currentCarCount--;
        // get vehicle from slot number
        ParkingSpot spot = usedParkingSpot.get(slotNUmber);
        usedParkingSpot.remove(slotNUmber);
        Vehicle vh = spot.getVehicle();
        spot.removeVehicle();
        // make parking slot again available
        availableSlotsHeap.offer(spot);

        //pay ticket amount for spot
        vh.leaveParkingLot();

        //update data structures
        parkedVehicleByRegHashMap.remove(vh.getRegistration());
        HashSet<Vehicle> vehicleList =  parkedVehicleByColorHashMap.getOrDefault(vh.getColour(), new HashSet<Vehicle>());
        //update vehicle list
        if(vehicleList.isEmpty()){
            parkedVehicleByColorHashMap.remove(vh.getColour());
        }
        else{
            vehicleList.remove(vh);
            parkedVehicleByColorHashMap.put(vh.getColour(),vehicleList );
        }
        System.out.println(String.format("Slot number %d is free",slotNUmber));
    }


    // print Status
    public void printStatus(){
        System.out.println("Slot No.           Registration No          Colour ");
        for(Map.Entry mapElement : usedParkingSpot.entrySet()){
            int slotNum= (int)mapElement.getKey();
            ParkingSpot ps = (ParkingSpot)mapElement.getValue();
            Vehicle vh = ps.getVehicle();
            System.out.println(String.format("%d        %s          %s", slotNum, vh.getRegistration()  ,vh.getColour()));
        }

    }

    //get registration_numbers_for_car by colour
    public  void getRegistrationNumberByColour(String colour){
        HashSet<Vehicle> vehicleList =  parkedVehicleByColorHashMap.getOrDefault(colour, new HashSet<Vehicle>());
        if(!vehicleList.isEmpty()){
            int size= vehicleList.size();
            int count =0;
            for(Vehicle vh : vehicleList){
                count++;
                if (count == size){
                    System.out.print(String.format("%s",  vh.getRegistration())  );
                }
                else{
                    System.out.print(String.format("%s,",  vh.getRegistration())  );
                }

            }
        }else{
            System.out.print(String.format("Not found"));

        }

    }


    //get slot_numbers_for_car by colour
    public  void getSlotNumberByColour(String colour){
        HashSet<Vehicle> vehicleList =  parkedVehicleByColorHashMap.getOrDefault(colour, new HashSet<Vehicle>());
        if(!vehicleList.isEmpty()){
            int size= vehicleList.size();
            int count =0;
            for(Vehicle vh : vehicleList){
                count++;
                if (count == size){
                    System.out.print(String.format("%s",  vh.getSlotNumber())  );
                }
                else{
                    System.out.print(String.format("%s,",  vh.getSlotNumber())  );
                }

            }
        }else{
            System.out.print(String.format("Not found"));

        }


    }

    public void getSlotNumberByReg(String regNum){
        Vehicle vh =parkedVehicleByRegHashMap.getOrDefault(regNum, null);
        if (vh == null){
            System.out.println("Not found");
        }
        else{
            System.out.println(String.format("%d", vh.getSlotNumber()));
        }
    }

    public boolean isFull(VehicleType type) {

        //currently only single vehicle type supported
        // i.e. car . May be in future electric has some other spots
        // we can only provide slot as per car/vehicle type
            if(currentCarCount < maxCarCount){
                return false;
            }
            else {
                return true;
            }
    }

    // increment the parking spot count based on the vehicle type
    private void incrementSpotCount(VehicleType type) {
        if (type == VehicleType.CAR ) {
            currentCarCount++;
        } else if (type == VehicleType.ELECTRIC_CAR) {
            electricCarCount++;
        }
        else{
            //do nothing
            System.out.println("Unknown vehicle type");
        }

    }




}

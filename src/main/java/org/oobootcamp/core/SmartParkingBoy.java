package org.oobootcamp.core;

import java.util.List;

public class SmartParkingBoy {
  private List<ParkingLot> parkingLots;

  public SmartParkingBoy(List<ParkingLot> parkingLots) {
    this.parkingLots = parkingLots;
  }

  public Ticket park(Car car) throws Exception {
    int empty = 0;
    ParkingLot parkingLotMostEmpty = null;
    for (ParkingLot parkingLot : parkingLots) {
      int emptySpaceNumber = parkingLot.getCapacity()-parkingLot.getCarList().size();
      if (emptySpaceNumber>empty){
        empty = emptySpaceNumber;
        parkingLotMostEmpty = parkingLot;
      }
    }
    if (parkingLotMostEmpty!=null){
      return parkingLotMostEmpty.park(car);
    }
    throw new Exception("车位已满");
  }

  public Car pick(Ticket ticket) throws Exception {
    for (ParkingLot parkingLot : parkingLots) {
      String identify = parkingLot.getIdentify();
      if (ticket.getIdentify().equals(identify)) {
        Car car = parkingLot.pick(ticket);
        return car;
      }
    }
    throw new Exception("无效票");  }
}

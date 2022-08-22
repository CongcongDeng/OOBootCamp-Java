package org.oobootcamp.core;

import java.util.List;

public class ParkingBoy {

  public ParkingBoy(List<ParkingLot> parkingLots) {
    this.parkingLots = parkingLots;
  }

  private List<ParkingLot> parkingLots;

  public Ticket park(Car car) throws Exception {
    for (ParkingLot parkingLot : parkingLots) {
      if (parkingLot.getCapacity() > parkingLot.getCarList().size()) {
        Ticket ticket = parkingLot.park(car);
        return ticket;
      }
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
    throw new Exception("无效票");
  }
}

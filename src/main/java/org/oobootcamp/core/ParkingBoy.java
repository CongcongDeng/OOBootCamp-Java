package org.oobootcamp.core;

import java.util.List;

public abstract class ParkingBoy {

  public abstract Ticket park(Car car) throws Exception;

  public ParkingBoy(List<ParkingLot> parkingLots) {
    this.parkingLots = parkingLots;
  }

  protected List<ParkingLot> parkingLots;

  public Car pick(Ticket ticket) throws Exception {
    for (ParkingLot parkingLot : parkingLots) {
      if (parkingLot.hasTheCar(ticket)) {
        return parkingLot.pick(ticket);
      }
    }
    throw new Exception("无效票");
  }

  public boolean hasSpareSpaces() {
    for (ParkingLot parkingLot : parkingLots) {
      if (parkingLot.hasSpareParkingSpace()) {
        return true;
      }
    }
    return false;
  }
}

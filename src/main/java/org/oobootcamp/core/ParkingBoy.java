package org.oobootcamp.core;

import java.util.List;

public abstract class ParkingBoy implements ParkingAble {

  public ParkingBoy(List<ParkingLot> parkingLots) {
    this.parkingLots = parkingLots;
  }

  protected List<ParkingLot> parkingLots;

  public List<ParkingLot> getParkingLots() {
    return parkingLots;
  }

  @Override
  public Car pick(Ticket ticket) throws Exception {
    for (ParkingLot parkingLot : parkingLots) {
      if (parkingLot.hasTheCar(ticket)) {
        return parkingLot.pick(ticket);
      }
    }
    throw new Exception("无效票");
  }

  @Override
  public boolean hasSpareParkingSpace() {
    for (ParkingLot parkingLot : parkingLots) {
      if (parkingLot.hasSpareParkingSpace()) {
        return true;
      }
    }
    return false;
  }

}

package org.oobootcamp.core;

import java.util.List;

public abstract class ParkingBoy {

  public abstract Ticket park(Car car) throws Exception;

  public ParkingBoy(List<ParkingLot> parkingLots) {
    this.parkingLots = parkingLots;
  }

  private List<ParkingLot> parkingLots;
  public Car pick(Ticket ticket) throws Exception {
    for (ParkingLot parkingLot : parkingLots) {
      if (ticket.getIdentify().equals(parkingLot.getIdentify()) && parkingLot.hasTheCar(ticket)) {
        return parkingLot.pick(ticket);
      }
    }
    throw new Exception("无效票");
  }

  public List<ParkingLot> getParkingLots() {
    return parkingLots;
  }

  public void setParkingLots(List<ParkingLot> parkingLots) {
    this.parkingLots = parkingLots;
  }

}

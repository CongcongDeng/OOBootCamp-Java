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
    return parkingLots.stream()
            .filter(parkingLot -> parkingLot.hasTheCar(ticket)).findFirst()
            .orElseThrow(()->new Exception("无效票"))
            .pick(ticket);
  }

  @Override
  public boolean hasSpareParkingSpace() {
    return parkingLots.stream()
            .filter(parkingLot -> parkingLot.hasSpareParkingSpace()).findFirst()
            .isPresent();
  }

}

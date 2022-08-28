package org.oobootcamp.core;

import java.util.Comparator;
import java.util.List;

public class SmartParkingBoy extends ParkingBoy {

  public SmartParkingBoy(List<ParkingLot> parkingLots) {
    super(parkingLots);
  }

  public Ticket park(Car car) throws Exception {
    return  parkingLots.stream()
            .filter(parkingLot -> parkingLot.hasSpareParkingSpace())
            .max(Comparator.comparingInt(ParkingLot::getSpareParkingSpace))
            .orElseThrow(()->new Exception("车位已满")).park(car);
  }

  @Override
  public boolean hasTheCar(Ticket ticket) {
    return parkingLots.stream()
            .filter(parkingLot -> parkingLot.hasTheCar(ticket))
            .findFirst()
            .isPresent();
  }
}

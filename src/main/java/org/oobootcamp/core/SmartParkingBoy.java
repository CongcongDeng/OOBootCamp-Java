package org.oobootcamp.core;

import java.util.Comparator;
import java.util.List;

public class SmartParkingBoy extends ParkingBoy {

  public SmartParkingBoy(List<ParkingLot> parkingLots) {
    super(parkingLots);
  }

  public Ticket park(Car car) throws Exception {
//    how to throw exception
    return  parkingLots.stream()
            .filter(parkingLot -> parkingLot.hasSpareParkingSpace())
            .max(Comparator.comparingInt(ParkingLot::getSpareParkingSpace))
            .orElseThrow().park(car);
  }
}

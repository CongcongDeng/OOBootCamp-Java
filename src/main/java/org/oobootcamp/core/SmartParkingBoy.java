package org.oobootcamp.core;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class SmartParkingBoy extends ParkingBoy {

  public SmartParkingBoy(List<ParkingLot> parkingLots) {
    super(parkingLots);
  }

  public Ticket park(Car car) throws Exception {
    Optional<ParkingLot> max = super.getParkingLots().stream().max(Comparator.comparing(ParkingLot::getSpareParkingSpace));
    ParkingLot parkingLotMostEmpty = max.get();
    if (parkingLotMostEmpty != null || parkingLotMostEmpty.getSpareParkingSpace() != 0) {
      return parkingLotMostEmpty.park(car);
    }
    throw new Exception("车位已满");
  }
}

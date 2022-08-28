package org.oobootcamp.core;

import java.util.List;

public class GraduateParkingBoy extends ParkingBoy{

  public GraduateParkingBoy(List<ParkingLot> parkingLots) {
    super(parkingLots);
  }

  public Ticket park(Car car) throws Exception {
    return parkingLots.stream()
            .filter(parkingLot -> parkingLot.hasSpareParkingSpace()).findFirst()
            .orElseThrow(()->new Exception("车位已满"))
            .park(car);
  }

  @Override
  public boolean hasTheCar(Ticket ticket) {
    return parkingLots.stream()
            .filter(parkingLot -> parkingLot.hasTheCar(ticket))
            .findFirst()
            .isPresent();
  }


}


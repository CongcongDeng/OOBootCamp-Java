package org.oobootcamp.core;

import java.util.List;

public class GraduateParkingBoy extends ParkingBoy{

  public GraduateParkingBoy(List<ParkingLot> parkingLots) {
    super(parkingLots);
  }

  public Ticket park(Car car) throws Exception {
    for (ParkingLot parkingLot : super.getParkingLots()) {
      if (parkingLot.hasSpareParkingSpace()) {
        Ticket ticket = parkingLot.park(car);
        return ticket;
      }
    }
    throw new Exception("车位已满");
  }


}


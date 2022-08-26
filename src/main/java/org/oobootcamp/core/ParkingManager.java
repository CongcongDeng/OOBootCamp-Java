package org.oobootcamp.core;

import java.util.List;

public class ParkingManager {

  private List<ParkingLot> parkingLots;
  private List<ParkingBoy> parkingBoys;

  public Ticket park(Car car) throws Exception {
    ParkingBoy parkingBoy1 = parkingBoys.stream()
            .filter(parkingBoy -> parkingBoy.hasSpareParkingLot())
            .findFirst()
            .orElse(null);
    if(parkingBoy1 != null){
      return parkingBoy1.park(car);
    }
    return parkingLots.stream()
            .filter(parkingLot -> parkingLot.hasSpareParkingSpace())
            .findFirst()
            .orElseThrow(()-> new Exception("车位已满"))
            .park(car);
  }

  ;

  public Car pick(Ticket ticket) throws Exception {
    ParkingBoy parkingBoy1 = parkingBoys.stream()
            .filter(parkingBoy -> parkingBoy.hasTheParkingLot(ticket))
            .findFirst()
            .orElse(null);
    if(parkingBoy1 != null){
      return parkingBoy1.pick(ticket);
    }
    return parkingLots.stream()
            .filter(parkingLot -> parkingLot.hasTheCar(ticket))
            .findFirst()
            .orElseThrow(()->new Exception("无效票")).pick(ticket);
  }

  public ParkingManager(List<ParkingLot> parkingLots, List<ParkingBoy> parkingBoys) {
    this.parkingLots = parkingLots;
    this.parkingBoys = parkingBoys;
  }

}

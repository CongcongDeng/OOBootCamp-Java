package org.oobootcamp.core;

import java.util.List;

public class ParkingManager {

  private List<ParkingAble> parkingAbles;
  public ParkingManager(List<ParkingAble> parkingAbles) {
    this.parkingAbles = parkingAbles;
  }

  public Ticket park(Car car) throws Exception {
    return parkingAbles.stream()
            .filter(parkingAble -> parkingAble.hasSpareParkingSpace())
            .findFirst()
            .orElseThrow(()-> new Exception("车位已满"))
            .park(car);
  }

  ;

  public Car pick(Ticket ticket) throws Exception {
    return parkingAbles.stream()
            .filter(parkingAble -> parkingAble.hasTheCar(ticket))
            .findFirst()
            .orElseThrow(()->new Exception("无效票"))
            .pick(ticket);
  }

}

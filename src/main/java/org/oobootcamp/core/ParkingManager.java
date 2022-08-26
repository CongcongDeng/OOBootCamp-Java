package org.oobootcamp.core;

import java.util.List;
import java.util.Optional;

public class ParkingManager {

  private List<ParkingLot> parkingLots;
  private List<ParkingBoy> parkingBoys;

  public Ticket park(Car car) throws Exception {
    ParkingBoy parkingBoy1 = parkingBoys.stream().filter(parkingBoy -> parkingBoy.hasSpareSpaces()).findFirst().get();
    if(parkingBoy1 != null){
      return parkingBoy1.park(car);
    }
    return parkingLots.stream().filter(parkingLot -> parkingLot.hasSpareParkingSpace()).findFirst().orElseThrow(()-> new Exception("车位已满")).park(car);
  }

  ;

  public Car pick(Ticket ticket) {
    return null;
  }

  public ParkingManager(List<ParkingLot> parkingLots, List<ParkingBoy> parkingBoys) {
    this.parkingLots = parkingLots;
    this.parkingBoys = parkingBoys;
  }

  public List<ParkingLot> getParkingLots() {
    return parkingLots;
  }

  public void setParkingLots(List<ParkingLot> parkingLots) {
    this.parkingLots = parkingLots;
  }

  public List<ParkingBoy> getParkingBoys() {
    return parkingBoys;
  }

  public void setParkingBoys(List<ParkingBoy> parkingBoys) {
    this.parkingBoys = parkingBoys;
  }
}

package org.oobootcamp.core;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import org.junit.jupiter.api.Test;

class ParkingManagerTest {

  @Test
  void shouldReturnTicketGivenParkingBoyParkingLotHasEmptySpaceAndParkingManagerParkingLotHasEmptySpace()
      throws Exception {
    List<ParkingBoy> parkingBoys = generateParkingBoy(true);
    ParkingLot parkingLot = new ParkingLot(10);
    ParkingManager parkingManager = new ParkingManager(Collections.singletonList(parkingLot), parkingBoys);
    Car car = new Car("111");
    Ticket ticket = parkingManager.park(car);
    assertTrue(parkingBoys.get(0).parkingLots.get(0).hasTheCar(ticket));
  }

  private List<ParkingBoy> generateParkingBoy(boolean hasEmptySpace) throws Exception {
    GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(Collections.singletonList(new ParkingLot(1)));
    SmartParkingBoy smartParkingBoy = new SmartParkingBoy(Collections.singletonList(new ParkingLot(1)));
    if (!hasEmptySpace){
      graduateParkingBoy.park(new Car("AAA"));
      smartParkingBoy.park(new Car("DDD"));
    }
    List<ParkingBoy> parkingBoys = new LinkedList<>();
    parkingBoys.add(graduateParkingBoy);
    parkingBoys.add(smartParkingBoy);
    return parkingBoys;
  }
}
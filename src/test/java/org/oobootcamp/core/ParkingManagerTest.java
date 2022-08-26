package org.oobootcamp.core;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

class ParkingManagerTest {

    private List<ParkingBoy> generateParkingBoy(boolean hasEmptySpace) throws Exception {
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(Collections.singletonList(new ParkingLot(1)));
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(Collections.singletonList(new ParkingLot(1)));
        if (!hasEmptySpace) {
            graduateParkingBoy.park(new Car("AAA"));
            smartParkingBoy.park(new Car("DDD"));
        }
        List<ParkingBoy> parkingBoys = new LinkedList<>();
        parkingBoys.add(graduateParkingBoy);
        parkingBoys.add(smartParkingBoy);
        return parkingBoys;
    }

    @Test
    void should_park_successful_and_return_ticket_when_park_given_parkingBoy_has_spareParkingLot_and_parkingLot_has_spareSpaces_in_parkingManage() throws Exception {
//    given
        List<ParkingBoy> parkingBoys = generateParkingBoy(true);
        ParkingLot parkingLot = new ParkingLot(10);
        ParkingManager parkingManager = new ParkingManager(Collections.singletonList(parkingLot), parkingBoys);
        Car car = new Car("111");
//    when
        Ticket ticket = parkingManager.park(car);
//    then
        assertTrue(parkingBoys.get(0).getParkingLots().get(0).hasTheCar(ticket));
    }

    @Test
    void should_park_successful_return_ticket_when_park_given_parkingBoy_is_no_spareParkingLot_and_parkingLot_has_spareSpaces_in_parkingManage() throws Exception {
//    given
        List<ParkingBoy> parkingBoys = generateParkingBoy(false);
        ParkingLot parkingLot = new ParkingLot(10);
        ParkingManager parkingManager = new ParkingManager(Collections.singletonList(parkingLot), parkingBoys);
        Car car = new Car("111");
//    when
        Ticket ticket = parkingManager.park(car);
//    then
        assertTrue(parkingLot.hasTheCar(ticket));
    }

    @Test
    void should_park_failed_when_park_given_parkingBoy_is_no_spareParkingLot_and_parkingLot_is_no_spareSpaces_in_parkingManage() throws Exception {
//    given
        List<ParkingBoy> parkingBoys = generateParkingBoy(false);
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLot.park(new Car("111"));
        ParkingManager parkingManager = new ParkingManager(Collections.singletonList(parkingLot), parkingBoys);
        Car car = new Car("222");
//    when
        Exception exception = assertThrows(Exception.class, () -> {
            parkingManager.park(car);
        });
//    then
        assertEquals("车位已满", exception.getMessage());
    }

    @Test
    void should_pick_successful__when_park_given_a_valid_ticket() throws Exception {
//    given
        List<ParkingBoy> parkingBoys = generateParkingBoy(true);
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingManager parkingManager = new ParkingManager(Collections.singletonList(parkingLot), parkingBoys);
        Car car = new Car("111");
        Ticket ticket = parkingManager.park(car);
//    when
        Car pickedCar = parkingManager.pick(ticket);
//    then
        assertEquals(car, pickedCar);
    }

    @Test
    void should_pick_failed_when_park_given_a_used_ticket() throws Exception {
//    given
        List<ParkingBoy> parkingBoys = generateParkingBoy(true);
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingManager parkingManager = new ParkingManager(Collections.singletonList(parkingLot), parkingBoys);
        Car car = new Car("111");
        Ticket ticket = parkingManager.park(car);
        parkingManager.pick(ticket);
//    when
        Exception exception = assertThrows(Exception.class, () -> {
            parkingManager.pick(ticket);
        });
//    then
        assertEquals("无效票", exception.getMessage());
    }

    @Test
    void should_pick_failed_when_park_given_another_parkingLot_ticket() throws Exception {
//    given
        List<ParkingBoy> parkingBoys = generateParkingBoy(true);
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingManager parkingManager = new ParkingManager(Collections.singletonList(parkingLot), parkingBoys);
        Car car = new Car("111");
        parkingManager.park(car);
        ParkingLot otherParkingLot = new ParkingLot(1);
        Ticket otherParkingTicket = otherParkingLot.park(car);
//    when
        Exception exception = assertThrows(Exception.class, () -> {
            parkingManager.pick(otherParkingTicket);
        });
//    then
        assertEquals("无效票", exception.getMessage());
    }


}
package org.oobootcamp.core;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

class ParkingManagerTest {

    @Test
    void should_park_successful_and_return_ticket_when_park_given_parkingBoy_and_parkingLot_has_spareSpaces_in_parkingManage() throws Exception {
//    given
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(Collections.singletonList(new ParkingLot(1)));
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(Collections.singletonList(new ParkingLot(1)));
        ParkingLot parkingLot = new ParkingLot(10);
        List<ParkingAble> parkingAble = new LinkedList<>();
        parkingAble.add(graduateParkingBoy);
        parkingAble.add(smartParkingBoy);
        parkingAble.add(parkingLot);
        ParkingManager parkingManager = new ParkingManager(parkingAble);
        Car car = new Car("111");
//    when
        Ticket ticket = parkingManager.park(car);
//    then
        assertTrue(graduateParkingBoy.getParkingLots().get(0).hasTheCar(ticket));
    }

    @Test
    void should_park_successful_return_ticket_when_park_given_parkingBoy_is_no_spareParkingLot_and_parkingLot_has_spareSpaces_in_parkingManage() throws Exception {
//    given
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(Collections.singletonList(new ParkingLot(1)));
        graduateParkingBoy.park(new Car("111"));
        ParkingLot parkingLot = new ParkingLot(10);
        LinkedList<ParkingAble> parkingAble = new LinkedList<>();
        parkingAble.add(graduateParkingBoy);
        parkingAble.add(parkingLot);
        ParkingManager parkingManager = new ParkingManager(parkingAble);
        Car car = new Car("222");
//    when
        Ticket ticket = parkingManager.park(car);
//    then
        assertTrue(parkingLot.hasTheCar(ticket));
    }

    @Test
    void should_park_failed_when_park_given_parkingBoy_is_no_spareParkingLot_and_parkingLot_is_no_spareSpaces_in_parkingManage() throws Exception {
//    given
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(Collections.singletonList(new ParkingLot(1)));
        graduateParkingBoy.park(new Car("111"));
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLot.park(new Car("222"));
        LinkedList<ParkingAble> parkingAble = new LinkedList<>();
        parkingAble.add(graduateParkingBoy);
        parkingAble.add(parkingLot);
        ParkingManager parkingManager = new ParkingManager(parkingAble);
        Car car = new Car("333");
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
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(Collections.singletonList(new ParkingLot(1)));
        ParkingLot parkingLot = new ParkingLot(10);
        List<ParkingAble> parkingAble = new LinkedList<>();
        parkingAble.add(graduateParkingBoy);
        parkingAble.add(parkingLot);
        ParkingManager parkingManager = new ParkingManager(parkingAble);
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
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(Collections.singletonList(new ParkingLot(1)));
        ParkingLot parkingLot = new ParkingLot(10);
        List<ParkingAble> parkingAble = new LinkedList<>();
        parkingAble.add(graduateParkingBoy);
        parkingAble.add(parkingLot);
        ParkingManager parkingManager = new ParkingManager(parkingAble);
        Ticket ticket = parkingManager.park(new Car("111"));
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
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(Collections.singletonList(new ParkingLot(1)));
        ParkingLot parkingLot = new ParkingLot(10);
        List<ParkingAble> parkingAble = new LinkedList<>();
        parkingAble.add(graduateParkingBoy);
        parkingAble.add(parkingLot);
        ParkingManager parkingManager = new ParkingManager(parkingAble);
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
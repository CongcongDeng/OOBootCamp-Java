package org.oobootcamp.core;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;

import org.junit.jupiter.api.Test;

class GraduateParkingBoyTest {

    @Test
    void should_park_successful_to_A_when_parking_given_parkingLotA_and_parkingLotB_have_spare_spaces() throws Exception {
//    given
        ParkingLot parkingLotA = new ParkingLot(10);
        ParkingLot parkingLotB = new ParkingLot(10);
        Car car = new Car("鄂A1111");
        LinkedList<ParkingLot> parkingLots = new LinkedList<>();
        parkingLots.add(parkingLotA);
        parkingLots.add(parkingLotB);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLots);
//    when
        Ticket ticket = graduateParkingBoy.park(car);
//   then
        assertTrue(parkingLotA.hasTheCar(ticket));
    }

    @Test
    void should_park_successful_to_B_when_parking_given_parkingLotA_is_no_spaces_and_parkingLotB_have_remain_spaces() throws Exception {
//    given
        ParkingLot parkingLotA = new ParkingLot(1);
        parkingLotA.park(new Car("鄂A1111"));
        ParkingLot parkingLotB = new ParkingLot(10);
        Car car = new Car("鄂A2222");
        LinkedList<ParkingLot> parkingLots = new LinkedList<>();
        parkingLots.add(parkingLotA);
        parkingLots.add(parkingLotB);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLots);
//    when
        Ticket ticket = graduateParkingBoy.park(car);
//        then
        assertTrue(parkingLotB.hasTheCar(ticket));
    }

    @Test
    void should_park_failed_when_parking_given_parkingLotA_and_parkingLotB_is_no_spaces() throws Exception {
//    given
        ParkingLot parkingLotA = new ParkingLot(1);
        parkingLotA.park(new Car("鄂A1111"));
        ParkingLot parkingLotB = new ParkingLot(1);
        parkingLotB.park(new Car("鄂A2222"));
        LinkedList<ParkingLot> parkingLots = new LinkedList<>();
        parkingLots.add(parkingLotA);
        parkingLots.add(parkingLotB);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLots);
//    when
        Exception exception = assertThrows(Exception.class, () -> {
            graduateParkingBoy.park(new Car("鄂A3333"));
        });
//   then
        assertEquals("车位已满", exception.getMessage());
    }

    @Test
    void should_pick_successful_when_pickup_given_a_valid_ticket() throws Exception {
//    given
        ParkingLot parkingLotA = new ParkingLot(10);
        ParkingLot parkingLotB = new ParkingLot(10);
        LinkedList<ParkingLot> parkingLots = new LinkedList<>();
        parkingLots.add(parkingLotA);
        parkingLots.add(parkingLotB);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLots);
        Car car = new Car("鄂A1111");
        Ticket ticket = graduateParkingBoy.park(car);
//    when
        Car pickedCar = graduateParkingBoy.pick(ticket);
//   then
        assertEquals(car, pickedCar);
    }

    @Test
    void should_pick_failed_when_pickup_given_a_used_ticket() throws Exception {
        //    given
        ParkingLot parkingLotA = new ParkingLot(10);
        ParkingLot parkingLotB = new ParkingLot(10);
        LinkedList<ParkingLot> parkingLots = new LinkedList<>();
        parkingLots.add(parkingLotA);
        parkingLots.add(parkingLotB);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLots);
        Ticket ticket = graduateParkingBoy.park(new Car("鄂A1111"));
        graduateParkingBoy.pick(ticket);
        //    when
        Exception exception = assertThrows(Exception.class, () -> {
            graduateParkingBoy.pick(ticket);
        });
        //   then
        assertEquals("无效票", exception.getMessage());
    }

    @Test
    void should_pick_failed_when_pickup_given_another_parkingLot_ticket() throws Exception {
        //    given
        ParkingLot parkingLotA = new ParkingLot(10);
        LinkedList<ParkingLot> parkingLots = new LinkedList<>();
        parkingLots.add(parkingLotA);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLots);
        Car car = new Car("鄂A1111");
        graduateParkingBoy.park(car);
        ParkingLot parkingLotB = new ParkingLot(10);
        Ticket otherParkingLotTicket = parkingLotB.park(car);
        //    when
        Exception exception = assertThrows(Exception.class, () -> {
            graduateParkingBoy.pick(otherParkingLotTicket);
        });
        //   then
        assertEquals("无效票", exception.getMessage());
    }
}
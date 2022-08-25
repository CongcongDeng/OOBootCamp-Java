package org.oobootcamp.core;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class smartParkingBoyTest {
    @Test
    void should_park_successful_to_parkingLotB_when_parking_given_parkingLotA_spare_spaces_less_than_parkingLotB() throws Exception {
//        given
        ParkingLot parkingLotA = new ParkingLot(10);
        ParkingLot parkingLotB = new ParkingLot(10);
        parkingLotA.park(new Car("鄂A1111"));
        LinkedList<ParkingLot> parkingLots = new LinkedList<>();
        parkingLots.add(parkingLotA);
        parkingLots.add(parkingLotB);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        Car car = new Car("鄂A2222");
//        when
        Ticket ticket = smartParkingBoy.park(car);
//        then
        assertTrue(parkingLotB.hasTheCar(ticket));
    }

    @Test
    void should_park_successful_to_parkingLotA_when_parking_given_parkingLotB_remain_spaces_less_than_parkingLotA() throws Exception {
//        given
        ParkingLot parkingLotA = new ParkingLot(10);
        ParkingLot parkingLotB = new ParkingLot(10);
        parkingLotB.park(new Car("鄂A1111"));
        LinkedList<ParkingLot> parkingLots = new LinkedList<>();
        parkingLots.add(parkingLotA);
        parkingLots.add(parkingLotB);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        Car car = new Car("鄂A2222");
//        when
        Ticket ticket = smartParkingBoy.park(car);
//        then
        assertTrue(parkingLotA.hasTheCar(ticket));
    }

    @Test
    void should_park_failed_when_parking_given_parkingLotA_and_parkingLotB_is_no_spaces() throws Exception {
//        given
        ParkingLot parkingLotA = new ParkingLot(1);
        ParkingLot parkingLotB = new ParkingLot(1);
        parkingLotA.park(new Car("鄂A1111"));
        parkingLotB.park(new Car("鄂A2222"));
        LinkedList<ParkingLot> parkingLots = new LinkedList<>();
        parkingLots.add(parkingLotA);
        parkingLots.add(parkingLotB);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        //        when
        Exception exception = assertThrows(Exception.class, () -> {
            smartParkingBoy.park(new Car("鄂A3333"));
        });
//        then
        assertEquals("车位已满", exception.getMessage());
    }

    @Test
    void should_pick_successful_when_pickup_given_a_correct_ticket() throws Exception {
//        given
        ParkingLot parkingLotA = new ParkingLot(10);
        ParkingLot parkingLotB = new ParkingLot(10);
        LinkedList<ParkingLot> parkingLots = new LinkedList<>();
        parkingLots.add(parkingLotA);
        parkingLots.add(parkingLotB);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        Car car = new Car("鄂A1111");
        Ticket ticket = smartParkingBoy.park(car);
//        when
        Car pickedCar = smartParkingBoy.pick(ticket);
//        then
        assertEquals(car, pickedCar);
    }

    @Test
    void should_pick_failed_when_pickup_given_a_used_ticket() throws Exception {
//        given
        ParkingLot parkingLotA = new ParkingLot(10);
        ParkingLot parkingLotB = new ParkingLot(10);
        LinkedList<ParkingLot> parkingLots = new LinkedList<>();
        parkingLots.add(parkingLotA);
        parkingLots.add(parkingLotB);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        Ticket ticket = smartParkingBoy.park(new Car("鄂A1111"));
        smartParkingBoy.pick(ticket);
//        when
        Exception exception = assertThrows(Exception.class, () -> {
            smartParkingBoy.pick(ticket);
        });
//        then
        assertEquals("无效票", exception.getMessage());
    }

    @Test
    void should_pick_failed_when_pickup_given_another_parkingLot_ticket() throws Exception {
//        given
        ParkingLot parkingLotA = new ParkingLot(10);
        LinkedList<ParkingLot> parkingLots = new LinkedList<>();
        parkingLots.add(parkingLotA);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        Car car = new Car("鄂A1111");
        smartParkingBoy.park(car);
        ParkingLot parkingLotB = new ParkingLot(10);
        Ticket otherParkingLotTicket = parkingLotB.park(car);
//        when
        Exception exception = assertThrows(Exception.class, () -> {
            smartParkingBoy.pick(otherParkingLotTicket);
        });
//        then
        assertEquals("无效票", exception.getMessage());
    }
}
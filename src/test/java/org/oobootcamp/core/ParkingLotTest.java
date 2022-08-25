package org.oobootcamp.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class ParkingLotTest {
    @Test
    void should_parking_successful_and_get_a_ticket_when_parking_given_spare_parking_spaces() throws Exception {
//        given
        ParkingLot parkingLot = new ParkingLot(10);
        Car car = new Car("鄂A1111");
//        when
        Ticket ticket = parkingLot.park(car);
//        then
        assertTrue(parkingLot.hasTheCar(ticket));
    }

    @Test
    void should_parking_failed_when_parking_given_not_spare_parking_spaces() throws Exception {
//        given
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLot.park(new Car("鄂A1111"));
//        when
        Car car = new Car("鄂A2222");
        Exception exception = assertThrows(Exception.class, () -> {
            parkingLot.park(car);
        });
//        then
        assertEquals("车位已满", exception.getMessage());
    }

    @Test
    void should_pickup_successful_when_pickup_car_given_valid_and_not_used_ticket() throws Exception {
//        given
        ParkingLot parkingLot = new ParkingLot(10);
        Car car = new Car("鄂A1111");
        Ticket ticket = parkingLot.park(car);
//        when
        Car pickedCar = parkingLot.pick(ticket);
//        then
        assertEquals(car, pickedCar);
    }

    @Test
    void should_pickup_failed_when_pickup_car_given_other_parking_ticket() throws Exception {
//        given
        ParkingLot parkingLotA = new ParkingLot(10);
        Car car = new Car("鄂A1111");
        Ticket ticket = parkingLotA.park(car);
        ParkingLot parkingLotB = new ParkingLot(10);
        parkingLotB.park(car);
//        when
        Exception exception = assertThrows(Exception.class, () -> {
            parkingLotB.pick(ticket);
        });
//        then
        assertEquals("无效票", exception.getMessage());
    }

    @Test
    void should_pickup_failed_when_pickup_given_used_ticket() throws Exception {
//        given
        ParkingLot parkingLot = new ParkingLot(10);
        Car car = new Car("鄂A1111");
        Ticket ticket = parkingLot.park(car);
        parkingLot.pick(ticket);
        parkingLot.park(car);
//        when
        Exception exception = assertThrows(Exception.class, () -> {
            parkingLot.pick(ticket);
        });
//        then
        assertEquals("无效票", exception.getMessage());
    }

}
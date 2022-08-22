package org.oobootcamp.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class ParkingLotTest {
    @Test
    void should_parking_successful_and_get_a_ticket_when_parking_given_remain_parking_spaces() throws Exception {
//        given
        ParkingLot parkingLot = new ParkingLot(10,"p1");
        Car car = new Car("鄂A1111");
//        when
        Ticket ticket = parkingLot.park(car);
//        then
        assertEquals(car.getCarPlateLicense(),ticket.getCarPlateLicense());
    }

    @Test
    void should_parking_failure_when_parking_given_not_remain_parking_spaces() throws Exception {
//        given
        ParkingLot parkingLot = new ParkingLot(1,"p1");
        parkingLot.park(new Car("鄂A1111"));
//        when
        Car car = new Car("鄂A2222");
        Exception exception = assertThrows(Exception.class, () -> {
            parkingLot.park(car);
        });
//        then
        assertEquals("车位已满",exception.getMessage());
    }

    @Test
    void should_pick_successful_when_pick_car_given_correct_and_not_used_ticket() throws Exception {
//        given
        ParkingLot parkingLot = new ParkingLot(10,"p1");
        Ticket ticket = parkingLot.park(new Car("鄂A1111"));
//        when
        Car car = parkingLot.pick(ticket);
//        then
        assertEquals(ticket.getCarPlateLicense(),car.getCarPlateLicense());
    }

    @Test
    void should_pickup_failure_when_pickup_given_other_parking_ticket() throws Exception {
//        given
        ParkingLot parkingLot = new ParkingLot(10,"p1");
        parkingLot.park(new Car("鄂A1111"));
//        when
        Exception exception = assertThrows(Exception.class, () -> {
            parkingLot.pick(new Ticket("鄂A1111","xxxxxxx"));
        });
//        then
        assertEquals("无效票",exception.getMessage());
    }

    @Test
    void should_pickup_failure_when_pickup_given_used_ticket() throws Exception {
//        given
        ParkingLot parkingLot = new ParkingLot(10,"p1");
        Ticket ticket = parkingLot.park(new Car("鄂A1111"));
        parkingLot.pick(ticket);
//        when
        Exception exception = assertThrows(Exception.class, () -> {
            parkingLot.pick(ticket);
        });
//        then
        assertEquals("无效票",exception.getMessage());
    }
}
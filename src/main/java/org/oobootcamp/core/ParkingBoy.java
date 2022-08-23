package org.oobootcamp.core;

import java.util.List;

public class ParkingBoy {

    public ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    private List<ParkingLot> parkingLots;

    public Ticket park(Car car) throws Exception {
        for (ParkingLot parkingLot : parkingLots) {
            if (parkingLot.hasSpareParkingSpace()) {
                Ticket ticket = parkingLot.park(car);
                return ticket;
            }
        }
        throw new Exception("车位已满");
    }

    public Car pick(Ticket ticket) throws Exception {
        for (ParkingLot parkingLot : parkingLots) {
            Car car = parkingLot.hasTheCar(ticket);
            String identify = parkingLot.getIdentify();
            if (car != null && ticket.getIdentify().equals(identify)) {
                return parkingLot.pick(ticket);
            }
        }
        throw new Exception("无效票");
    }
}

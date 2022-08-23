package org.oobootcamp.core;

import java.util.List;

public class SmartParkingBoy {
    private List<ParkingLot> parkingLots;

    public SmartParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public Ticket park(Car car) throws Exception {
//         TODO delete loop maybe better
        int empty = 0;
        ParkingLot parkingLotMostEmpty = null;
        for (ParkingLot parkingLot : parkingLots) {
            int emptySpaceNumber = parkingLot.getSpareParkingSpace();
            if (emptySpaceNumber > empty) {
                empty = emptySpaceNumber;
                parkingLotMostEmpty = parkingLot;
            }
        }
        if (parkingLotMostEmpty != null) {
            return parkingLotMostEmpty.park(car);
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

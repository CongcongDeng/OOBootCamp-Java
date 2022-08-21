package org.oobootcamp.core;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private final int capacity;
    private final String identify = "xxx";
    private List<Car> carList = new ArrayList();

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public Ticket park(Car car) throws Exception {
        if(this.capacity - this.carList.size() > 0){
            this.carList.add(car);
            Ticket ticket = new Ticket(car.getCarPlateLicense(), this.identify);
            return ticket;
        }
        throw new Exception("车位已满");
    }

    public Car pick(Ticket ticket) throws Exception {
        Car car  = this.check(ticket);
        if(car != null && ticket.identify == this.identify){
            this.carList.remove(car);
            return car;
        }
        throw new Exception("无效票");
    }

    private Car check(Ticket ticket){
        return this.carList.stream().filter(
                carItem -> ticket.getCarPlateLicense().equals(carItem.getCarPlateLicense())
        ).findFirst().orElse(null);
    }
}



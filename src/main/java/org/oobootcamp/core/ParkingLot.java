package org.oobootcamp.core;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private int capacity;
    private String identify = "xxx";
    private List<Car> parkedCars = new ArrayList();

    public String getIdentify() {
        return identify;
    }

    public ParkingLot(int capacity, String identify) {
        this.capacity = capacity;
        this.identify = identify;
    }

    public Ticket park(Car car) throws Exception {
        if (this.hasSpareParkingSpace()) {
            this.parkedCars.add(car);
            Ticket ticket = new Ticket(car.getCarPlateLicense(), this.identify);
            return ticket;
        }
        throw new Exception("车位已满");
    }

    public Car pick(Ticket ticket) throws Exception {
        Car car = this.hasTheCar(ticket);
        if (car != null && ticket.identify == this.identify) {
            this.parkedCars.remove(car);
            return car;
        }
        throw new Exception("无效票");
    }

    protected Car hasTheCar(Ticket ticket) {
//        TODO return boolean value maybe better
        return this.parkedCars.stream().filter(
                carItem -> ticket.getCarPlateLicense().equals(carItem.getCarPlateLicense())
        ).findFirst().orElse(null);
    }

    protected boolean hasSpareParkingSpace() {
        return this.capacity > this.parkedCars.size();
    }

    protected int getSpareParkingSpace() {
        return this.capacity - this.parkedCars.size();
    }
}



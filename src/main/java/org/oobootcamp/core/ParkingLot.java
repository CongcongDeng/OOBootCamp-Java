package org.oobootcamp.core;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot implements ParkingAble {

    private int capacity;

    private Map<Ticket, Car> parkedCars = new HashMap<>();

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }
    @Override
    public Ticket park(Car car) throws Exception {
        if (capacity > parkedCars.size()) {
            Ticket ticket = new Ticket();
            this.parkedCars.put(ticket, car);
            return ticket;
        }
        throw new Exception("车位已满");
    }

    @Override
    public Car pick(Ticket ticket) throws Exception {
        if (parkedCars.containsKey(ticket)) {
            return parkedCars.remove(ticket);
        }
        throw new Exception("无效票");
    }

    @Override
    public boolean hasTheCar(Ticket ticket) {
        return parkedCars.containsKey(ticket);
    }

    @Override
    public boolean hasSpareParkingSpace() {
        return this.capacity > this.parkedCars.size();
    }

    protected int getSpareParkingSpace() {
        return this.capacity - this.parkedCars.size();
    }

}



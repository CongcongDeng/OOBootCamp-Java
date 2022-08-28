package org.oobootcamp.core;

public interface ParkingAble {
    Ticket park(Car car) throws Exception;

    boolean hasSpareParkingSpace();

    boolean hasTheCar(Ticket ticket);

    Car pick(Ticket ticket) throws Exception;
}

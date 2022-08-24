package org.oobootcamp.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLot {

  private int capacity;
  private String identify = "xxx";
  private Map<String, Car> parkedCars = new HashMap<>();

  public String getIdentify() {
    return identify;
  }

  public ParkingLot(int capacity, String identify) {
    this.capacity = capacity;
    this.identify = identify;
  }

  public Ticket park(Car car) throws Exception {
    if (this.hasSpareParkingSpace()) {
      this.parkedCars.put(car.getCarPlateLicense(),car);
      Ticket ticket = new Ticket(car.getCarPlateLicense(), this.identify);
      return ticket;
    }
    throw new Exception("车位已满");
  }

  public Car pick(Ticket ticket) throws Exception {
    if (hasTheCar(ticket) && ticket.identify == this.identify) {
      Car car = parkedCars.get(ticket.getCarPlateLicense());
      this.parkedCars.remove(car.getCarPlateLicense());
      return car;
    }
    throw new Exception("无效票");
  }

  protected boolean hasTheCar(Ticket ticket) {
    return parkedCars.containsKey(ticket.getCarPlateLicense());
  }

  protected boolean hasSpareParkingSpace() {
    return this.capacity > this.parkedCars.size();
  }

  protected int getSpareParkingSpace() {
    return this.capacity - this.parkedCars.size();
  }
}



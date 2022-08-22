package org.oobootcamp.core;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private int capacity;

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getIdentify() {
        return identify;
    }

    public void setIdentify(String identify) {
        this.identify = identify;
    }

    public List<Car> getCarList() {
        return carList;
    }

    public void setCarList(List<Car> carList) {
        this.carList = carList;
    }

    private String identify = "xxx";
    private List<Car> carList = new ArrayList();

    public ParkingLot(int capacity,String identify) {
        this.capacity = capacity;
        this.identify = identify;
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



package org.oobootcamp.core;

public class Ticket {
    protected String carPlateLicense;

    protected String identify;

    public Ticket(String carPlateLicense, String identify) {
        this.carPlateLicense = carPlateLicense;
        this.identify = identify;
    }

    public String getCarPlateLicense() {
        return carPlateLicense;
    }

    public String getIdentify() {
        return identify;
    }

}

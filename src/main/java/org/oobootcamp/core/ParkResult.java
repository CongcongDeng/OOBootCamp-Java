package org.oobootcamp.core;

public class ParkResult {
    protected Ticket ticket;

    protected String errorMessage;
    public ParkResult(Ticket ticket, String errorMessage) {
        this.ticket = ticket;
        this.errorMessage = errorMessage;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}

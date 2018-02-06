package com.montealegreluis.ticketbeast.concerts;

public class UnknownConcert extends Exception {
    private UnknownConcert(String message) {
        super(message);
    }

    public static UnknownConcert withId(int id) {
        return new UnknownConcert(String.format("Unknown concert with id %s cannot be found", id));
    }
}

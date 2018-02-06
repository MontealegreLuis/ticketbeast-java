package com.montealegreluis.ticketbeast.store;

import com.montealegreluis.ticketbeast.concerts.Concert;
import com.montealegreluis.ticketbeast.concerts.Concerts;

public class ViewPublishedConcert {
    private Concerts concerts;

    public ViewPublishedConcert(Concerts concerts) {
        this.concerts = concerts;
    }

    public Concert view(int id) {
        return concerts.publishedWithId(id);
    }
}

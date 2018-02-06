package com.montealegreluis.ticketbeast.fakes;

import com.montealegreluis.ticketbeast.concerts.Concert;
import com.montealegreluis.ticketbeast.concerts.Concerts;
import com.montealegreluis.ticketbeast.concerts.UnknownConcert;

import java.util.ArrayList;
import java.util.List;

public class InMemoryConcerts implements Concerts {
    private List<Concert> concerts = new ArrayList<>();

    @Override
    public Concert publishedWithId(int id) throws UnknownConcert {
        int index = id - 1;
        if (index < 0 || index > concerts.size()) {
            throw UnknownConcert.withId(id);
        }
        Concert concert = concerts.get(index);
        if (!concert.isPublished() || concert.isPast()) {
            throw UnknownConcert.withId(id);
        }
        return concert;
    }

    @Override
    public void add(Concert concert) {
        concerts.add(concert);
    }
}

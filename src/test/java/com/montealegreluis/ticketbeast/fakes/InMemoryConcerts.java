package com.montealegreluis.ticketbeast.fakes;

import com.montealegreluis.ticketbeast.concerts.Concert;
import com.montealegreluis.ticketbeast.concerts.Concerts;

import java.util.ArrayList;
import java.util.List;

public class InMemoryConcerts implements Concerts {
    private List<Concert> concerts = new ArrayList<>();

    @Override
    public Concert publishedWithId(int id) {
        return concerts.get(id - 1);
    }

    @Override
    public void add(Concert concert) {
        concerts.add(concert);
    }
}

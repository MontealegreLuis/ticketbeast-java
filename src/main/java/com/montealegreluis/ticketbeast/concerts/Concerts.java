package com.montealegreluis.ticketbeast.concerts;

public interface Concerts {
    Concert publishedWithId(int id);

    void add(Concert concert);
}

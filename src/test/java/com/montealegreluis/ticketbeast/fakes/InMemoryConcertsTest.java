package com.montealegreluis.ticketbeast.fakes;

import com.montealegreluis.ticketbeast.concerts.Concerts;
import com.montealegreluis.ticketbeast.contracts.ConcertsContractTest;

public class InMemoryConcertsTest extends ConcertsContractTest {
    @Override
    protected Concerts concerts() {
        return new InMemoryConcerts();
    }
}

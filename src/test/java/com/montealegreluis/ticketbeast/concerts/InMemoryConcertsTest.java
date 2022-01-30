package com.montealegreluis.ticketbeast.concerts;

import com.montealegreluis.tickebeast.contracttests.ConcertsContractTest;
import com.montealegreluis.tickebeast.fakes.InMemoryConcerts;

final class InMemoryConcertsTest extends ConcertsContractTest {
    @Override
    protected Concerts concerts() {
        return new InMemoryConcerts();
    }
}

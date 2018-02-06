package com.montealegreluis.ticketbeast.ports.hibernate;

import com.montealegreluis.ticketbeast.concerts.Concerts;
import com.montealegreluis.ticketbeast.contracts.ConcertsContractTest;
import org.junit.Rule;

public class ConcertsRepositoryTest extends ConcertsContractTest {

    @Override
    protected Concerts concerts() {
        return new ConcertsRepository(rule.getSession());
    }

    @Rule
    public final SessionFactoryRule rule = new SessionFactoryRule();
}
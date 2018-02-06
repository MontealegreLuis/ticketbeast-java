package com.montealegreluis.ticketbeast.contracts;

import com.montealegreluis.ticketbeast.builders.A;
import com.montealegreluis.ticketbeast.concerts.Concert;
import com.montealegreluis.ticketbeast.concerts.Concerts;
import com.montealegreluis.ticketbeast.concerts.UnknownConcert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

abstract public class ConcertsContractTest {
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void it_does_not_find_an_unknown_concert() throws UnknownConcert {
        exception.expect(UnknownConcert.class);
        concerts().publishedWithId(-1);
    }

    @Test
    public void it_does_not_find_an_unpublished_concert() throws UnknownConcert {
        Concerts concerts = concerts();
        concerts.add(A.unpublishedConcert());

        exception.expect(UnknownConcert.class);
        concerts.publishedWithId(1);
    }

    @Test
    public void it_does_not_find_a_concert_from_the_past() throws UnknownConcert {
        Concerts concerts = concerts();
        concerts.add(A.pastConcert());

        exception.expect(UnknownConcert.class);
        concerts.publishedWithId(1);
    }

    @Test
    public void it_finds_an_existing_concert() throws UnknownConcert {
        Concerts concerts = concerts();
        Concert concert = A.publishedConcert();
        concerts.add(concert);

        assertEquals(concert, concerts.publishedWithId(1));
    }

    abstract protected Concerts concerts();
}

package com.montealegreluis.ticketbeast.contracts;

import com.montealegreluis.ticketbeast.builders.A;
import com.montealegreluis.ticketbeast.concerts.Concert;
import com.montealegreluis.ticketbeast.concerts.Concerts;
import com.montealegreluis.ticketbeast.concerts.UnknownConcert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

abstract public class ConcertsContractTest {
    @Test
    public void it_does_not_find_an_unknown_concert() {
        assertThrows(UnknownConcert.class, () -> concerts().publishedWithId(-1));
    }

    @Test
    public void it_does_not_find_an_unpublished_concert() {
        Concerts concerts = concerts();
        concerts.add(A.unpublishedConcert());

        assertThrows(UnknownConcert.class, () -> concerts.publishedWithId(1));
    }

    @Test
    public void it_does_not_find_a_concert_from_the_past() {
        Concerts concerts = concerts();
        concerts.add(A.pastConcert());

        assertThrows(UnknownConcert.class, () -> concerts.publishedWithId(1));
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

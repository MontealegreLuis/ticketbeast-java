package com.montealegreluis.ticketbeast.ports.hibernate;

import com.montealegreluis.ticketbeast.concerts.Concert;
import com.montealegreluis.ticketbeast.concerts.Concerts;
import com.montealegreluis.ticketbeast.concerts.UnknownConcert;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class ConcertsRepository implements Concerts {
    private final Session session;

    public ConcertsRepository(Session session) {
        this.session = session;
    }

    @Override
    public Concert publishedWithId(int id) throws UnknownConcert {
        Query query = session.createQuery("FROM Concert WHERE id = :id AND publishedAt <> null AND date > NOW()");
        query.setParameter("id", id);

        Concert concert = (Concert) query.uniqueResult();

        if (concert == null) throw UnknownConcert.withId(id);

        return concert;
    }

    @Override
    public void add(Concert concert) {
        session.save(concert);
    }
}

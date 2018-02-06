package com.montealegreluis.ticketbeast.builders;

import com.montealegreluis.ticketbeast.concerts.Concert;

import java.sql.Date;
import java.time.LocalDate;

public class A {
    public static Concert publishedConcert() {
        return Concert.published(
            "The red chord",
            "with Animosity and the Lethargy",
            Date.valueOf(LocalDate.parse("2017-12-13")),
            3250,
            "The Mosh Pit",
            "123 Example lane",
            "Laraville",
            "ON",
            "17196",
            "For tickets call (555) 222-2222."
        );
    }
}

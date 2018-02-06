package com.montealegreluis.ticketbeast.builders;

import com.github.javafaker.Faker;
import com.montealegreluis.ticketbeast.concerts.Concert;

import java.sql.Date;
import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

public class A {
    private static Faker faker = new Faker();

    public static Concert publishedConcert() {
        return Concert.published(
            "The red chord",
            "with Animosity and the Lethargy",
            faker.date().future(10, TimeUnit.DAYS),
            3250,
            "The Mosh Pit",
            "123 Example lane",
            "Laraville",
            "ON",
            "17196",
            "For tickets call (555) 222-2222."
        );
    }

    public static Concert unpublishedConcert() {
        return Concert.unpublished(
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

    public static Concert pastConcert() {
        return Concert.published(
            "The red chord",
            "with Animosity and the Lethargy",
            faker.date().past(10, TimeUnit.DAYS),
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

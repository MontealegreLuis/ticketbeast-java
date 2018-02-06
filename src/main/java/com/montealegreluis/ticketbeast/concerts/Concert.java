package com.montealegreluis.ticketbeast.concerts;

import java.util.Date;

public class Concert {
    private int id;
    private String title;
    private String subtitle;
    private Date date;
    private int ticketPrice;
    private String venue;
    private String venueAddress;
    private String city;
    private String state;
    private String zip;
    private String additionalInformation;

    private Concert(
        String title,
        String subtitle,
        Date date,
        int ticketPrice,
        String venue,
        String venueAddress,
        String city,
        String state,
        String zip,
        String additionalInformation
    ) {
        this.title = title;
        this.subtitle = subtitle;
        this.date = date;
        this.ticketPrice = ticketPrice;
        this.venue = venue;
        this.venueAddress = venueAddress;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.additionalInformation = additionalInformation;
    }

    public static Concert published(
        String title,
        String subtitle,
        Date date,
        int ticketPrice,
        String venue,
        String venueAddress,
        String city,
        String state,
        String zip,
        String additionalInformation
    ) {
        return new Concert(title, subtitle, date, ticketPrice, venue, venueAddress, city, state, zip, additionalInformation);
    }
}

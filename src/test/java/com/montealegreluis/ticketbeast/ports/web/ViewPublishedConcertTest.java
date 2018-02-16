package com.montealegreluis.ticketbeast.ports.web;

import com.github.mjeanroy.junit.servers.rules.TomcatServerRule;
import com.github.mjeanroy.junit.servers.tomcat.EmbeddedTomcatConfiguration;
import com.montealegreluis.ticketbeast.builders.A;
import com.montealegreluis.ticketbeast.concerts.Concert;
import com.montealegreluis.ticketbeast.ports.hibernate.ConcertsRepository;
import com.montealegreluis.ticketbeast.ports.hibernate.DatabaseSession;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class ViewPublishedConcertTest {

    @Test
    public void it_shows_the_view_published_concert_page() throws IOException {
        Concert publishedConcert = A.publishedConcert();
        concerts.add(publishedConcert);
        Request request = forPath("/concerts/view?id=" + publishedConcert.id());

        Response response = client.newCall(request).execute();

        assertEquals(200, response.code());
    }

    private Request forPath(String path) {
        return new Request.Builder().url(tomcat.getUrl() + path).build();
    }

    @Before
    public void configureConcerts() {
        concerts = new ConcertsRepository(DatabaseSession.openSession());
    }

    private ConcertsRepository concerts;
    private OkHttpClient client = new OkHttpClient();

    private static final EmbeddedTomcatConfiguration configuration = EmbeddedTomcatConfiguration.builder()
        .withClasspath(ViewPublishedConcertTest.class.getName())
        .withParentClasspath(ViewPublishedConcertTest.class)
        .build();

    @ClassRule
    public static final TomcatServerRule tomcat = new TomcatServerRule(configuration);
}

package com.montealegreluis.ticketbeast;

import com.github.mjeanroy.junit.servers.annotations.TestServer;
import com.github.mjeanroy.junit.servers.runner.JunitServerRunner;
import com.github.mjeanroy.junit.servers.tomcat.EmbeddedTomcat;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(JunitServerRunner.class)
public class HelloWorldTest {
    @TestServer
    private static EmbeddedTomcat tomcat;

    @Test
    public void it_shows_the_home_page() throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(tomcat.getUrl())
                .build();

        Response response = client.newCall(request).execute();

        assertEquals(200, response.code());
    }
}

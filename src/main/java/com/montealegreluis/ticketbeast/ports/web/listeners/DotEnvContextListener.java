package com.montealegreluis.ticketbeast.ports.web.listeners;

import io.github.cdimascio.dotenv.Dotenv;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class DotEnvContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent context) {
        Dotenv.configure().load();
    }

    public void contextDestroyed(ServletContextEvent context) {  }
}

package com.montealegreluis.ticketbeast.ports.hibernate;

import com.montealegreluis.ticketbeast.concerts.Concert;
import io.github.cdimascio.dotenv.Dotenv;
import org.hibernate.Session;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class DatabaseSession {
    public static Session openSession() {
        Dotenv dotenv = Dotenv.load();
        Configuration configuration = new Configuration();
        configuration.configure();
        configuration.setProperty("hibernate.dialect", dotenv.get("hibernate.dialect"));
        configuration.setProperty("hibernate.connection.driver_class", dotenv.get("hibernate.driver_class"));
        configuration.setProperty("hibernate.connection.url", dotenv.get("mysql.connection.url"));
        configuration.setProperty("hibernate.connection.username", dotenv.get("mysql.connection.username"));
        configuration.setProperty("hibernate.connection.password", dotenv.get("mysql.connection.password"));

        configuration.addAnnotatedClass(Concert.class);

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .applySettings(configuration.getProperties())
            .build()
        ;

        return configuration.buildSessionFactory(registry).openSession();
    }
}

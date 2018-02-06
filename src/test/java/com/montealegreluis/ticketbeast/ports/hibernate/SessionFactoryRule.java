/*
 * This source file is subject to the license that is bundled with this package in the file LICENSE.
 */
package com.montealegreluis.ticketbeast.ports.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

import java.io.File;

public class SessionFactoryRule implements MethodRule {
    private Transaction transaction;
    private Session session;

    private void shutdown() {
        try {
            try {
                try {
                    if (transaction.isActive()) transaction.rollback();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                session.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Statement apply(final Statement statement, FrameworkMethod method, Object test) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
            Configuration configuration = new Configuration();
            configuration.configure(new File("src/test/resources/hibernate.cfg.xml"));
            configuration.addFile("src/main/resources/com/montealegreluis/ticketbeast/concerts/Concert.hbm.xml");

            StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build()
            ;

            session = configuration.buildSessionFactory(registry).openSession();
            transaction = session.beginTransaction();

            try {
                statement.evaluate();
            } finally {
                shutdown();
            }
            }

        };
    }

    public Session getSession() {
        return session;
    }
}

package com.project.management.database;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateDataBaseConnector {
    private static final Logger LOGGER = LogManager.getLogger(HibernateDataBaseConnector.class);
    private static StandardServiceRegistry registry;
    private static SessionFactory sessionFactory;

    private static HibernateDataBaseConnector instance = new HibernateDataBaseConnector();

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static HibernateDataBaseConnector getInstance() {
        return instance;
    }

    private HibernateDataBaseConnector() {
        try {
            registry = new StandardServiceRegistryBuilder().configure().build();
            MetadataSources sources = new MetadataSources(registry);
            final Metadata metadata = sources.getMetadataBuilder().build();
            sessionFactory = metadata.getSessionFactoryBuilder().build();

        } catch (Exception e) {
            LOGGER.error("launch Hibernate", e);
        }
    }

    public static synchronized void endWork() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

}
